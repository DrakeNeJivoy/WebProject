document.addEventListener("DOMContentLoaded", function () {
    const surveyForm = document.getElementById("survey-form");
    const questionsContainer = document.getElementById("questions-container");
    const addQuestionButton = document.getElementById("add-question");

    addQuestionButton.addEventListener("click", function () {
        addQuestion();
    });

    surveyForm.addEventListener("submit", function (event) {
        event.preventDefault();
        submitSurvey();
    });

    function addQuestion() {
        const questionIndex = document.querySelectorAll(".question").length;
        const questionDiv = document.createElement("div");
        questionDiv.classList.add("question");

        questionDiv.innerHTML = `
            <input type="text" placeholder="Введите вопрос" class="question-text" required>
            <div class="answers"></div>
            <button type="button" class="add-answer">Добавить ответ</button>
            <button type="button" class="remove-question">Удалить вопрос</button>
        `;

        questionsContainer.appendChild(questionDiv);

        questionDiv.querySelector(".add-answer").addEventListener("click", function () {
            addAnswer(questionDiv);
        });

        questionDiv.querySelector(".remove-question").addEventListener("click", function () {
            questionDiv.remove();
        });
    }

    function addAnswer(questionDiv) {
        const answerDiv = document.createElement("div");
        answerDiv.classList.add("answer");

        answerDiv.innerHTML = `
            <input type="text" placeholder="Введите вариант ответа" class="answer-text" required>
            <select class="answer-status">
                <option value="0" selected>Нет правильного ответа</option>
                <option value="1">Неправильный</option>
                <option value="2">Правильный</option>
            </select>
            <button type="button" class="remove-answer">Удалить</button>
        `;

        questionDiv.querySelector(".answers").appendChild(answerDiv);

        answerDiv.querySelector(".remove-answer").addEventListener("click", function () {
            answerDiv.remove();
        });
    }

    function submitSurvey() {
        const title = document.getElementById("survey-title").value;
        const questions = [];

        document.querySelectorAll(".question").forEach(questionDiv => {
            const text = questionDiv.querySelector(".question-text").value;
            const answerOptions = [];

            questionDiv.querySelectorAll(".answer").forEach(answerDiv => {
                const answerText = answerDiv.querySelector(".answer-text").value;
                const status = parseInt(answerDiv.querySelector(".answer-status").value, 10);

                answerOptions.push({
                    text: answerText,
                    status: status
                });
            });

            questions.push({
                text: text,
                answerOptions: answerOptions
            });
        });

        const surveyData = {
            title: title,
            questions: questions
        };

        fetch("/surveys/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(surveyData)
        })
            .then(response => response.text())
            .then(data => {
                alert(data);
            })
            .catch(error => {
                console.error("Ошибка:", error);
            });
    }
});
