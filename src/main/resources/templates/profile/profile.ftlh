<head>
    <style>
        .avatar {
            width: 140px;
            height: 140px;
        }

        .navbar-links a {
            margin-right: 10px;
        }

        .right-buttons {
            margin-left: auto;
        }
    </style>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
                <li class="nav-item">
                    <a class="nav-link" href="/resumes">Resumes</a>
                </li>
            </ul>
            <div class="right-buttons">
                <a class="btn btn-primary btn-sign-in" href="/logout" id="logout">Log Out</a>
            </div>
        </div>
    </div>
</nav>

<div>
    <div class="container">
        <div class="elem-container">
            <h1 class="header-top d-flex justify-content-center pt-3 text-dark-emphasis">Личный кабинет
                <#if user.accountType=="EMPLOYER">
                    Работодателя
                <#else>
                    Соискателя
                </#if>
            </h1>
        </div>

        <div class="row mt-5 ms-1 mb-5">
            <div class="col-4 bg-white text-dark-emphasis" style="width: 27rem; height: 10rem">
                <div class="row ms-2">
                    <div class="col-5 mt-2 ">
                        <img class="avatar rounded-circle" src="/profile/avatars" alt="photo">
                    </div>
                    <div class="col">
                        <div class="card-body mt-3">
                            <h5 class="card-title">
                                <div class="fs-5">
                                    <#if user.accountType=="EMPLOYER">
                                    <#assign str = user.name>
                                    <span>Компания:</span>
                                    ${str}
                                    <#else>

                                    <#assign variable1 = user.name>
                                    <#assign variable2 = user.surname>
                                    <#assign combinedValue = variable1 + " " + variable2>
                                    <#assign result = combinedValue>
                                    ${result}
                                </div>

                                <p class="card-text mt-2">
                                    <small class="fs-6">Возраст:
                                        <#if user.age?has_content>${user.age}<#else> отсутствует</#if>
                                    </small>
                                </p> </#if>
                            </h5>
                            <a class="btn btn-dark rounded-3 bg-body-secondary border border-primary border-1 text-dark-emphasis mt-4 border-opacity-75"
                               style="height: 2.5rem; width: 12rem" href="/profile/edit">Редактировать</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex align-items-center col-md-2 ms-auto me-2">
                <#if user.accountType=="EMPLOYER">
                    <a class="btn btn-primary btn-lg py-3 px-2 rounded-4" href="/vacancies/create">+<br>Создать<br>вакансию</a>
                    <a class="btn btn-dark btn-lg py-5 px-2 rounded-4 text-white ms-2 pe-2"
                       href="/profile/getRespondsToVacancies">Отклики</a>
                <#else>
                    <a class="btn btn-primary btn-lg py-3 px-2 rounded-4 me-3" href="resumes/create">+<br>Создать<br>резюме</a>
                    <a class="btn btn-dark btn-lg py-5 px-2 rounded-4 text-white ms-1 pe-2"
                       href="/profile/repliedToVacancies">Отклики</a>
                </#if>
            </div>
        </div>

        <div class="">
            <h2 class="">
                <#if user.accountType=="EMPLOYER">
                    Вакансии:
                <#else>
                    Резюме:
                </#if>
            </h2>
            <#list items as item>
                <div class="card mb-3">
                    <div class="card-body rounded-0">
                        <h5 class="card-title">${item.name}</h5>
                        <p class="card-text">Дата обновления: ${item.updateTime}</p>
                        <div class="d-flex justify-content-center">
                            <#if user.accountType == "EMPLOYER">
                                <a href="/vacancies/updateTime/${item.id}" class="btn btn-primary me-2">Обновить</a>
                                <a href="/vacancies/edit/${item.id}" class="btn btn-secondary">Редактировать</a>
                                <form action="/vacancies/delete/${item.id}" method="post" style="height: 0.5rem"
                                      class="ms-2 delete-form">
                                    <input type="hidden" name="_method" value="delete">
                                    <button class="btn btn-danger" type="submit">Удалить</button>
                                </form>
                            <#else>
                                <a href="/resumes/updateTime/${item.id}" class="btn btn-primary me-2">Обновить</a>
                                <a href="/resumes/edit/${item.id}" class="btn btn-secondary">Редактировать</a>
                                <form action="/resumes/delete/${item.id}" method="post" style="height: 0.5rem"
                                      class="ms-2 delete-form">
                                    <input type="hidden" name="_method" value="delete">
                                    <button class="btn btn-danger" type="submit">Удалить</button>
                                </form>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>

        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const deleteForms = document.querySelectorAll('.delete-form');

        deleteForms.forEach(form => {
            form.addEventListener('submit', function (event) {
                event.preventDefault();

                const confirmation = confirm('Вы уверены, что хотите удалить?');

                if (confirmation) {
                    form.submit();
                }
            });
        });
    });
</script>
</body>