<#import "../spring.ftl" as spring>
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

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/"><@spring.message 'navbar.home'/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile"><@spring.message 'navbar.profile'/></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/vacancies"><@spring.message 'navbar.vacancies'/></a>
                </li>

                <#if type = "EMPLOYER">
                    <li class="nav-item">
                        <a class="nav-link" href="/resumes"><@spring.message 'navbar.resumes'/></a>
                    </li>
                </#if>

                <#if type = "APPLICANT">
                    <li class="nav-item">
                        <a class="nav-link" href="/users/employers"><@spring.message 'navbar.employers'/></a>
                    </li>
                </#if>

                <#if type = "EMPLOYER">
                    <li class="nav-item">
                        <a class="nav-link" href="/users/applicants"><@spring.message 'navbar.applicants'/></a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</nav>


<div class="container mt-5">
    <h2 class="mb-4"><@spring.message 'resumes.resumeSubmissionForm'/></h2>
    <form action="/resumes/create" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
        </#if>
        <div class="form-group">
            <label for="name"><@spring.message 'applying.nameOfResume'/></label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="categoryName"><@spring.message 'resumes.category'/></label>
            <select id="categoryName" name="categoryName" class="form-select" required>
                <option value="Медицина" class="form-brd-radius col-6"><@spring.message 'resumes.medicine'/></option>
                <option value="Информационные технологии"><@spring.message 'resumes.it'/></option>
                <option value="Маркетинг"><@spring.message 'resumes.marketing'/></option>
                <option value="HrРекрутинг"><@spring.message 'resumes.hrRecruiting'/></option>
                <option value="Бухгалтерия"><@spring.message 'resumes.accounting'/></option>
                <option value="Образование"><@spring.message 'resumes.education'/></option>
                <option value="Производство"><@spring.message 'resumes.manufacturing'/></option>
                <option value="Строительство"><@spring.message 'resumes.construction'/></option>
                <option value="Искусство и дизайн"><@spring.message 'resumes.artDesign'/></option>
                <option value="Путешествия и туризм"><@spring.message 'resumes.travelTourism'/></option>
                <option value="Искусственный интеллект"><@spring.message 'resumes.ai'/></option>
                <option value="Экология и устойчивое развитие"><@spring.message 'resumes.ecologySustainability'/></option>
                <option value="Кибербезопасность"><@spring.message 'resumes.cybersecurity'/></option>
                <option value="Финансовые технологии (FinTech)"><@spring.message 'resumes.fintech'/></option>
                <option value="Электромобили и зеленая энергия"><@spring.message 'resumes.electricVehiclesGreenEnergy'/></option>
            </select>
        </div>

        <div class="form-group">
            <label for="salary"><@spring.message 'applying.salary'/>:</label>
            <input type="number" id="salary" name="salary" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-check-label" for="isActive"><@spring.message 'resumes.isActive'/>:</label>
            <input class="form-check-input" type="checkbox" id="isActive" name="isActive">
        </div>

        <div class="form-group">
            <label for="telegram"><@spring.message 'resumes.telegram'/>:</label>
            <input type="text" id="telegram" name="contactInfos[0].contactType" value="Telegram" readonly>
            <input type="text" id="telegramValue" name="contactInfos[0].value" class="form-control" placeholder="<@spring.message 'resumes.telegram'/>">
        </div>

        <div class="form-group">
            <label for="linkedin"><@spring.message 'resumes.linkedIn'/>:</label>
            <input type="text" id="linkedin" name="contactInfos[1].contactType" value="LinkedIn" readonly>
            <input type="text" id="linkedinValue" name="contactInfos[1].value" class="form-control" placeholder="<@spring.message 'resumes.linkedIn'/>">
        </div>

        <div class="form-group">
            <label for="facebook"><@spring.message 'resumes.facebook'/>:</label>
            <input type="text" id="facebook" name="contactInfos[2].contactType" value="Facebook" readonly>
            <input type="text" id="facebookValue" name="contactInfos[2].value" class="form-control" placeholder="<@spring.message 'resumes.facebook'/>">
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" id="email" name="contactInfos[3].contactType" value="Email" readonly>
            <input type="email" id="emailValue" name="contactInfos[3].value" class="form-control" placeholder="Email">
        </div>

        <div class="form-group">
            <label for="phone"><@spring.message 'authentication.phoneNumber'/>:</label>
            <input type="text" id="phone" name="contactInfos[4].contactType" value="PhoneNumber" readonly>
            <input type="tel" id="phoneValue" name="contactInfos[4].value" class="form-control" placeholder="<@spring.message 'authentication.phoneNumber'/>">
        </div>



        <button type="button" class="btn btn-primary mt-3" id="educationAddButton"><@spring.message 'resumes.addEducationInfo'/>
        </button>
        <div id="addEducation">
        </div>

        <button type="button" class="btn btn-primary mt-3" id="experienceAddButton"><@spring.message 'resumes.addExperienceInfo'/>
        </button>
        <div id="addExperience">
        </div>

        <button class="btn btn-primary mt-3 mb-5" type="submit"><@spring.message 'applying.createResume'/></button>
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
            '<input name="workExpInfos[' + expCounter + '].years" class="form-control" type=number placeholder="<@spring.message 'resumes.workYears'/>"> ' +
            '</label>' +
            '<label>' +
            '<input name="workExpInfos[' + expCounter + '].companyName" class="form-control" type=text placeholder="<@spring.message 'applying.companyName'/>"> ' +
            '</label>' +
            '<label>' +
            '<input name="workExpInfos[' + expCounter + '].position" class="form-control" type=text placeholder="<@spring.message 'resumes.years'/>"> ' +
            '</label>' +
            '<label>' +
            '<input name="workExpInfos[' + expCounter + '].responsibilities" class="form-control" type=text placeholder="<@spring.message 'resumes.responsibilities'/>"> ' +
            '</label>' +

            '<button type="button" class="btn btn-danger ms-1 mb-1 remove-button"><@spring.message 'profile.delete'/></i></button>'
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
            '<input name="educationInfos[' + eduCounter + '].institution" class="form-control" type=text placeholder="<@spring.message 'resumes.institution'/>"> ' +
            '</label>' +
            '<label>' +
            '<input name="educationInfos[' + eduCounter + '].program" class="form-control" type=text placeholder="<@spring.message 'resumes.program'/>"> ' +
            '</label>' +
            '<label>' +
            '<input name="educationInfos[' + eduCounter + '].startDate" class="form-control" type=date placeholder="<@spring.message 'resumes.startDate'/>" style="padding-top: 0" > ' +
            '</label>' +
            '<label>' +
            '<input name="educationInfos[' + eduCounter + '].endDate" class="form-control" type=date placeholder="<@spring.message 'resumes.endDate'/>" style="padding-top: 0"> ' +
            '</label>' +
            '<label>' +
            '<input name="educationInfos[' + eduCounter + '].degree" class="form-control" type=text placeholder="<@spring.message 'resumes.degree'/>"> ' +
            '</label>' +

            '<button type="button" class="btn btn-danger ms-1 mb-1 remove-button"><@spring.message 'profile.delete'/></i></button>'
        eduFormContainer.appendChild(newEduForm);

        newEduForm.querySelector('.remove-button').addEventListener('click', () => {
            eduFormContainer.removeChild(newEduForm);
        });
    });
</script>
</body>
</html>
