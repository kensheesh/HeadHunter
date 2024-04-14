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
                    <li class="nav-item">
                        <a class="nav-link" href="/resumes">Resumes</a>
                    </li>
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
    <h1 class="text-center mb-4">Резюме</h1>

    <div class="row">
        <#list resumesPage.content as resume>
            <div class="col-md-12">
                <a href="/resumes/${resume.id}" class="text-decoration-none text-dark">
                    <div class="card vacancy-card rounded-0">
                        <div class="card-body rounded-0">
                            <h5 class="card-title">${resume.name}</h5>
                            <p class="card-text">Зарплата: ${resume.salary}</p>
                            <p class="card-text">Опубликовано: ${resume.updateTime}</p>
                        </div>
                    </div>
                </a>
            </div>
        </#list>
    </div>

    <div class="row mt-4">
        <div class="col text-center">
            <#if resumesPage.hasPrevious()>
                <a href="/resumes?page=${resumesPage.previousPageable().pageNumber}" class="btn btn-primary">Предыдущая</a>
            </#if>
            <#if resumesPage.hasNext()>
                <a href="/resumes?page=${resumesPage.nextPageable().pageNumber}"
                   class="btn btn-primary">Следующая</a>
            </#if>
        </div>
    </div>
</div>
</body>
</html>
