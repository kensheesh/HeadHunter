<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>All Vacancies</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .vacancy-card {
            margin-bottom: 20px;
        }
    </style>
</head>
<body class="bg-secondary-subtle">

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
                <#if username??>
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">Profile</a>
                    </li>
                </#if>
                <li class="nav-item">
                    <a class="nav-link" href="/vacancies">Vacancies</a>
                </li>
                <#if username??>

                    <#if type == "EMPLOYER">
                        <li class="nav-item">
                            <a class="nav-link" href="/resumes">Resumes</a>
                        </li>
                    </#if>

                    <#if type = "APPLICANT">
                        <li class="nav-item">
                            <a class="nav-link" href="/users/employers">Employers</a>
                        </li>
                    </#if>

                    <#if type = "EMPLOYER">
                        <li class="nav-item">
                            <a class="nav-link" href="/users/applicants">Applicants</a>
                        </li>
                    </#if>
                </#if>

            </ul>
            <div class="right-buttons">
                <#if username??>
                    <a class="btn btn-primary btn-sign-in" href="/logout" id="logout">Log Out</a>
                <#else>
                    <a class="btn btn-primary btn-sign-in" href="/login">Sign In</a>
                    <a class="btn btn-primary btn-sign-up" href="/register" style="width: 8rem">Sign Up</a>
                </#if>
            </div>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="text-center mb-4">Вакансии</h1>
    <h3>Сортировать по категориям</h3>
    <form action="/vacancies" method="GET" class="mb-4">
        <div class="row">
            <div class="col-md-4">
                <select class="form-select border-dark" aria-label="выбрать категорию"
                        name="category" id="category">
                    <option selected>выбрать категорию (все)</option>
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
            <div class="col-md-4">
                <label>
                    <select class="form-select" name="sort" id="sort">
                        <option value="date" selected>Sort by Date</option>
                        <option value="responses">Sort by Responses</option>
                    </select>
                </label>
            </div>
            <div class="col-md-2">
                <label>
                    <select class="form-select" name="order" id="order">
                        <option value="asc" selected>Ascending</option>
                        <option value="desc">Descending</option>
                    </select>
                </label>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary">Apply Filters</button>
            </div>
        </div>
    </form>


    <div class="row">
        <#if vacanciesPage.content?size == 0>
            <p>No vacancies found.</p>
        <#else>
            <#list vacanciesPage.content as vacancy>
                <div class="col-md-12">
                    <a href="/vacancies/${vacancy.id}" class="text-decoration-none text-dark">

                        <div class="card vacancy-card rounded-0">

                            <div class="card-body rounded-0">
                                <h5 class="card-title">${vacancy.name}</h5>
                                <p class="card-text">Зарплата: ${vacancy.salary}</p>
                                <p class="card-text">Опубликовано: ${vacancy.updateTime}</p>
                            </div>
                        </div>
                    </a>
                </div>
            </#list>
        </#if>
    </div>

    <div class="row mt-4">
        <div class="col text-center">
            <#if vacanciesPage.hasPrevious()>
                <a href="/vacancies?page=${vacanciesPage.previousPageable().pageNumber}&category=${category}&sort=${sort}&order=${sort}"
                   class="btn btn-primary">Previous</a>
            </#if>
            <#list 0..vacanciesPage.totalPages - 1 as page>
                <a href="/vacancies?page=${page}&category=${category}&sort=${sort}&order=${order}"
                   class="btn btn-primary">${page + 1}</a>
            </#list>
            <#if vacanciesPage.hasNext()>
                <a href="/vacancies?page=${vacanciesPage.nextPageable().pageNumber}&category=${category}&sort=${sort}&order=${order}"
                   class="btn btn-primary">Next</a>
            </#if>
        </div>
    </div>
</div>

<script>
</script>
</body>
</html>
