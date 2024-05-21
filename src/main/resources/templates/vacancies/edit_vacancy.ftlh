<#import "../spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Vacancy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .dropdown {
            display: inline-block;
            position: relative;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: white;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
            min-width: 160px;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body class="bg-body-secondary">
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
                <li class="nav-item">
                    <div class="dropdown">
                        <button class="btn" style="margin-top: 1.4px"><@spring.message 'dropdown' /></button>
                        <div class="dropdown-content">
                            <a href="?lang=ru"><@spring.message 'ru' /></a>
                            <a href="?lang=en"><@spring.message 'en' /></a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h2 class="text-center"><@spring.message 'vacancies.editingVacancy'/></h2>
                </div>
                <div class="card-body">
                    <form action="/vacancies/edit/${vacancy.id}" method="post" id="vacancyForm">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        </#if>
                        <input type="hidden" name="id" value="${vacancy.id}">
                        <div class="mb-3">
                            <label for="name" class="form-label"><@spring.message 'applying.nameOfVacancy'/>:</label>
                            <input type="text" class="form-control" id="name" name="name"
                                   value="${vacancy.name}">
                        </div>
                        <div class="mb-3">
                            <label for="categoryName" class="form-label"><@spring.message 'resumes.category'/></label>
                            <select class="form-select" id="categoryName" name="categoryName">
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
                        <div class="mb-3">
                            <label for="salary" class="form-label"><@spring.message 'applying.salary'/>:</label>
                            <input type="text" class="form-control" id="salary" name="salary"
                                   value="${vacancy.salary}">
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label"><@spring.message 'vacancies.description'/>:</label>
                            <textarea class="form-control" id="description" name="description"
                                      rows="3">${vacancy.description}</textarea>
                        </div>
                        <label for="isActiveCheckbox" class="form-label"><@spring.message 'resumes.isActive'/>:</label>
                        <input type="checkbox" name="isActive" value="true" class="form-check-input"
                               id="isActiveCheckbox">

                        <div class="d-grid gap-2">
                            <button class="btn btn-primary" type="submit"><@spring.message 'profile.save'/></button>
                            <a class="btn btn-secondary" href="/profile"><@spring.message 'profile.cancel'/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    document.getElementById('vacancyForm').addEventListener('submit', function(event) {
        var salaryInput = document.getElementById('salary');
        var salaryValue = salaryInput.value.trim();

        var salaryNumber = parseFloat(salaryValue);

        if (!isNaN(salaryNumber)) {
            salaryInput.value = salaryNumber;
        } else {
            event.preventDefault();
            alert('Please enter a valid number for salary.');
        }
    });
</script>
</body>
</html>
