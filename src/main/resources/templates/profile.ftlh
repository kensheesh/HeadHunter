<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"></script>
</head>

<div class="container">
    <div class="elem-container">
        <h1 class="header-top d-flex justify-content-center mt-3">Личный кабинет
            <#if user.accountType=="EMPLOYER">
                Работодателя
            <#else>
                Соискателя
            </#if>
        </h1>
    </div>
    <div class="row mt-5 ms-4">
        <div class="col-8 card mb-3" style="max-width: 540px;">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="${user.avatar}" class="img-fluid rounded-start" alt="avatar">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">
                            <#if user.accountType=="EMPLOYER">
                                <#assign str = profile.companyName!"Наименование компании не указано">
                                ${str}

                            <#else>
                                <#assign variable1 = profile.name>
                                <#assign variable2 = profile.surname>

                                <#assign combinedValue = variable1! + variable2!>
                                <#assign result = combinedValue!"Пользователь не указал свое имя и фамилию">

                                ${result}
                                <p class="card-text">
                                    <small class="text-body-secondary">Возраст:
                                        <#if user.age?has_content>${user.age}<#else> отсутствует</#if>
                                    </small>
                                </p>                    </#if>
                        </h5>
                        <a class="btn btn-primary mt-4" href="/users/profile/edit/${user.id}">Редактировать</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-end col-4  lined-red align-self-start mb-3 mx-0 px-0 align-items-end">
            <#if user.accountType=="EMPLOYER">
                <a class="btn btn-secondary" href="vacancies/add_vacancy/${user.id}">+ Создать вакансию</a>
            <#else>
                <a class="btn btn-secondary" href="resumes/add_resume/${user.id}">+ Создать резюме</a>
            </#if>
        </div>
    </div>

    <div class="elem-container mt-5">
        <h2 class="section-header mb-3">
            <div class="ms-4"><#if user.accountType=="EMPLOYER">
                Вакансии:
            <#else>
                Резюме:

            </#if>
                </div>
        </h2>
        <div class="d-flex justify-content mx-3">
            <#if items??>
                <#list items as item>
                    <div class="card mx-2">
                        <div class="card-body card-add-border fw-bold">
                            <#if item.name??>
                                ${item.name}
                            <#else>
                                ${item.name}
                            </#if>
                        </div>
                        <div class="p-2"><#if item.updateTime?has_content>${item.updateTime}<#else></#if> </div>
                        <div class="d-flex justify-content-center p-2">
                            <button class="btn btn-primary mx-2">Обновить</button>
                            <button class="btn btn-outline-danger">Редактировать</button>
                        </div>
                    </div>

                </#list>
            <#else>
                Пользователь ещё не создал <#if user.accountType=="EMPLOYER">
                ни одной вакансии            <#else>
                ни одного резюме
            </#if>
            </#if>

        </div>
    </div>
</div>
