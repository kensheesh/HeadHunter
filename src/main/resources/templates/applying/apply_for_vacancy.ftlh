<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/vacancies">Vacancies</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/resumes">Resumes</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h2 class="text-black fs-2 text-center mt-2"> Откликнуться на вакансию:</h2>

    <div class="row gutters-sm mt-5">
        <div class="col-md-4 mb-4">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-8">
                            <h6 class="text-secondary mt-1">Название компании:</h6>
                        </div>
                        <div class="col-4">
                            <h4>${vacancy.user.name}</h4>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-5">
                            <h6 class="text-secondary mt-1">E-mail компании:</h6>
                        </div>
                        <div class="col-7">
                            <h4>${vacancy.user.email}</h4>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-5">
                            <h6 class="text-secondary mt-1">Номер компании:</h6>
                        </div>
                        <div class="col-7">
                            <h4>${vacancy.user.phoneNumber}</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <div class="card mb-3">
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0 pt-3">Название вакансии:</h6>
                        </div>
                        <div class="col-sm-9 text-black fs-5 pt-2">
                            <p> ${vacancy.name}</p>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Оклад вакансии:</h6>
                        </div>
                        <div class="col-sm-9 text-black fs-5 ">
                            <p> ${vacancy.salary}</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card mb-3">
                <div class="card-body">
                    <div class="card-title mb-3">
                        <h3>Ваш отклик:</h3>
                    </div>
                    <form action="/apply/vacancy/${vacancy.id}" method="post">
                        <input type="hidden" name="vacancyId" value="${vacancy.id}">
                        <small>Текст сообщения:</small>
                        <label>
                            <input class="form-control mb-2" type="text" placeholder="Текст сообщения работодателю"
                                   name="messageText" style="width: 17rem">
                        </label>

                        <p class="mb-1">Создайте резюме, или выберите существующую ниже:</p>
                        <a href="/resumes/create" class="btn btn-secondary my-3">Создать резюме</a>
                        <#if resumes??>
                            <select class="form-select border-dark"
                                    aria-label="Default select example" name="resumeId" id="resumeId">
                                <#list resumes as resume>
                                    <small>Резюме</small>
                                    <option value="${resume.id}" class="form-brd-radius col-6">
                                        ${resume.name}</option>
                                </#list>
                            </select>
                        </#if>
                        <button type="submit" class="btn btn-primary my-3"> Отправить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
