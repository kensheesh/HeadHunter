<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
                    <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/vacancies">Vacancies</a>
                </li>

                <#if type == "EMPLOYER">
                    <li class="nav-item">
                        <a class="nav-link" href="/resumes">Resumes</a>
                    </li>
                </#if>

                <#if type == "APPLICANT">
                    <li class="nav-item">
                        <a class="nav-link" href="/users/employers">Employers</a>
                    </li>
                </#if>

                <#if type == "EMPLOYER">
                    <li class="nav-item">
                        <a class="nav-link" href="/users/applicants">Applicants</a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</nav>


<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h2 class="text-center">Create Vacancy</h2>
                </div>
                <div class="card-body">
                    <form action="/vacancies/create" method="post" id="vacancyForm">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        </#if>
                        <div class="mb-3">
                            <label for="name" class="form-label">Vacancy Name:</label>
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="Enter vacancy name">
                        </div>
                        <div class="mb-3">
                            <label for="categoryName" class="form-label">Category:</label>
                            <select class="form-select" id="categoryName" name="categoryName">
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
                        <div class="mb-3">
                            <label for="salary" class="form-label">Salary:</label>
                            <input type="number" class="form-control" id="salary" name="salary"
                                   placeholder="Enter salary">
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label">Description:</label>
                            <textarea class="form-control" id="description" name="description" rows="3"
                                      placeholder="Enter description"></textarea>
                        </div>
                        <div class="mb-3 row">
                            <div class="col">
                                <label for="experienceFrom" class="form-label">Experience From:</label>
                                <input type="text" class="form-control" id="experienceFrom" name="experienceFrom"
                                       placeholder="From">
                            </div>
                            <div class="col">
                                <label for="experienceTo" class="form-label">Experience To:</label>
                                <input type="text" class="form-control" id="experienceTo" name="experienceTo"
                                       placeholder="To">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-check-label" for="isActive">Is Active:</label>
                            <input class="form-check-input" type="checkbox" id="isActive" name="isActive">
                        </div>
                        <div class="d-grid gap-2">
                            <button class="btn btn-primary" type="submit">Create Vacancy</button>
                            <button class="btn btn-secondary" type="reset">Clear</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
