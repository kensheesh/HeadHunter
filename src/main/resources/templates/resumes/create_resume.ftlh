<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Resume</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group label {
            font-weight: bold;
        }

        .form-group input,
        .form-group select {
            width: 100%;
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
        }

        .btn-toggle-fields {
            margin-bottom: 1rem;
        }

        .btn-toggle-fields .btn {
            margin-right: 0.5rem;
        }

        .hidden-fields {
            display: none;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Resume Submission Form</h2>
    <form action="/resumes/create" method="post">

        <div class="form-group">
            <label for="name">Name of the resume:</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="categoryName">Category:</label>
            <select id="categoryName" name="categoryName" class="form-select" required>
                <option value="Медицина" class="form-brd-radius col-6">Медицина</option>
                <option value="Информационные технологии">Информационные технологии</option>
                <option value="Маркетинг">Маркетинг</option>
                <option value="HrРекрутинг">HrРекрутинг</option>
                <option value="Бухгалтерия">Бухгалтерия</option>
                <option value="Образование">Образование</option>
                <option value="Производство">Производство</option>
                <option value="Строительство">Строительство</option>
                <option value="Искусство и дизайн">Искусство и дизайн</option>
                <option value="Путешествия и туризм">Путешествия и туризм</option>
                <option value="Искусственный интеллект">Искусственный интеллект</option>
                <option value="Экология и устойчивое развитие">Экология и устойчивое развитие</option>
                <option value="Кибербезопасность">Кибербезопасность</option>
                <option value="Финансовые технологии (FinTech)">Финансовые технологии (FinTech)</option>
                <option value="Электромобили и зеленая энергия">Электромобили и зеленая энергия</option>
            </select>
        </div>

        <div class="form-group">
            <label for="salary">Expected Salary (USD):</label>
            <input type="number" id="salary" name="salary" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-check-label" for="isActive">Is Active:</label>
            <input class="form-check-input" type="checkbox" id="isActive" name="isActive">
        </div>

        <div class="form-group">
            <label for="telegram">Telegram:</label>
            <input type="text" id="telegram" name="contactInfos[0].contactType" value="Telegram" readonly>
            <input type="text" id="telegramValue" name="contactInfos[0].value" class="form-control" placeholder="Telegram Username">
        </div>

        <div class="form-group">
            <label for="linkedin">LinkedIn:</label>
            <input type="text" id="linkedin" name="contactInfos[1].contactType" value="LinkedIn" readonly>
            <input type="text" id="linkedinValue" name="contactInfos[1].value" class="form-control" placeholder="LinkedIn Profile">
        </div>

        <div class="form-group">
            <label for="facebook">Facebook:</label>
            <input type="text" id="facebook" name="contactInfos[2].contactType" value="Facebook" readonly>
            <input type="text" id="facebookValue" name="contactInfos[2].value" class="form-control" placeholder="Facebook Profile">
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" id="email" name="contactInfos[3].contactType" value="Email" readonly>
            <input type="email" id="emailValue" name="contactInfos[3].value" class="form-control" placeholder="Email Address">
        </div>

        <div class="form-group">
            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="contactInfos[4].contactType" value="PhoneNumber" readonly>
            <input type="tel" id="phoneValue" name="contactInfos[4].value" class="form-control" placeholder="Phone Number">
        </div>



        <button type="button" class="btn btn-primary mt-2" id="educationAddButton">Добавить информацию об образовании
        </button>
        <div id="addEducation">
        </div>

        <button type="button" class="btn btn-primary mt-2" id="experienceAddButton">Добавить информацию об опыте
            работы
        </button>
        <div id="addExperience">
        </div>

        <button class="btn btn-primary" type="submit">Создать резюме</button>
    </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>


<script>
    let showExperienceButton = document.getElementById('experienceAddButton');
    let experienceFormContainer = document.getElementById('addExperience');

    let showEduButton = document.getElementById('educationAddButton');
    let eduFormContainer = document.getElementById('addEducation');

    let eduCounter = -1;
    let expCounter = -1

    showExperienceButton.addEventListener('click', () => {
        expCounter++;
        let newExperienceForm = document.createElement('div');
        newExperienceForm.className = 'border mt-3';
        newExperienceForm.innerHTML = '<label>' +
            '<input name="workExpInfos[' + expCounter + '].years" class="form-control" type=number placeholder="Лет опыта"> ' +
            '</label>' +
            '<label>' +
            '<input name="workExpInfos[' + expCounter + '].companyName" class="form-control" type=text placeholder="Название компании"> ' +
            '</label>' +
            '<label>' +
            '<input name="workExpInfos[' + expCounter + '].position" class="form-control" type=text placeholder="Должность"> ' +
            '</label>' +
            '<label>' +
            '<input name="workExpInfos[' + expCounter + '].responsibilities" class="form-control" type=text placeholder="Обязанности"> ' +
            '</label>' +

            '<button type="button" class="btn btn-danger mt-2 remove-button">Удалить</i></button>'
        experienceFormContainer.appendChild(newExperienceForm);

        newExperienceForm.querySelector('.remove-button').addEventListener('click', () => {
            experienceFormContainer.removeChild(newExperienceForm);
        });
    });




    showEduButton.addEventListener('click', () => {
        eduCounter++;
        console.log(eduCounter)
        let newEduForm = document.createElement('div');
        newEduForm.className = 'border mt-3';
        newEduForm.innerHTML = '<label>' +
            '<input name="educationInfos[' + eduCounter + '].institution" class="form-control" type=text placeholder="institution"> ' +
            '</label>' +
            '<label>' +
            '<input name="educationInfos[' + eduCounter + '].program" class="form-control" type=text placeholder="program"> ' +
            '</label>' +
            '<label>' +
            '<input name="educationInfos[' + eduCounter + '].startDate" class="form-control" type=date placeholder="startDate"> ' +
            '</label>' +
            '<label>' +
            '<input name="educationInfos[' + eduCounter + '].endDate" class="form-control" type=date placeholder="endDate"> ' +
            '</label>' +
            '<label>' +
            '<input name="educationInfos[' + eduCounter + '].degree" class="form-control" type=text placeholder="degree"> ' +
            '</label>' +

            '<button type="button" class="btn btn-danger mt-2 remove-button">Удалить</i></button>'
        eduFormContainer.appendChild(newEduForm);

        newEduForm.querySelector('.remove-button').addEventListener('click', () => {
            eduFormContainer.removeChild(newEduForm);
        });
    });

    // document.getElementById('resumeForm').addEventListener('submit', async (e) => {
    //     e.preventDefault(); // Prevent default form submission
    //     const form = e.target;
    //     const formData = new FormData(form); // Create FormData object to collect form data
    //     try {
    //         const response = await fetch('/resumes/create', {
    //             method: 'POST',
    //             body: formData
    //         });
    //         if (!response.ok) {
    //             throw new Error('Network response was not ok');
    //         }
    //         const data = await response.json();
    //         console.log(data); // Output data received from the server
    //         // Add your logic here to handle the response data
    //     } catch (error) {
    //         console.error('There was a problem with the fetch operation:', error);
    //     }
    // });

</script>
</body>
</html>
