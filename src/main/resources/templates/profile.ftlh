<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="../static/css/profile.css">

</head>
<body class="bg-secondary">
<div>
    <div class="container">
        <div class="elem-container">
            <h1 class="header-top d-flex justify-content-center pt-3">Личный кабинет
                <#if user.accountType=="EMPLOYER">
                    Работодателя
                <#else>
                    Соискателя
                </#if>
            </h1>
        </div>

        <div class="row mt-5 ms-1 mb-5">
            <div class="col-md-4 card bg-dark text-white" style="max-width: 540px;">
                <div class="row ms-2">
                    <div class="col-md-5 d-flex align-items-center">
                        <#--                        <img alt="profile" src="../../../../data/avatars/base_avatar.jpeg"/>-->
                        <p class="fs-6 text-white-50 ms-2 my-1 py-0">Профильное фото</p>
                    </div>
                    <div class="col">
                        <div class="card-body">
                            <h5 class="card-title text-white">
                                <div class="fs-5 text-white">
                                    <#if user.accountType=="EMPLOYER">
                                    <#assign str = profile.companyName!"Наименование компании не указано">
                                    <span class="company-label">Компания:</span>
                                    ${str}

                                    <#else>
                                    <#assign variable1 = profile.name>
                                    <#assign variable2 = profile.surname>

                                    <#assign combinedValue = variable1! + variable2!>
                                    <#assign result = combinedValue!"Пользователь не указал свое имя и фамилию">

                                    Имя: ${result}
                                </div>
                                <p class="card-text mt-2">
                                    <small class="fs-6 text-white-50">Возраст:
                                        <#if user.age?has_content>${user.age}<#else> отсутствует</#if>
                                    </small>
                                </p> </#if>
                            </h5>
                            <a class="btn btn btn-secondary mt-4" href="/users/profile/edit${user.id}">Редактировать</a>
                        </div>
                    </div>
                </div>
            </div>


            <div class="d-flex align-items-center col-md-2 ms-auto">
                <#if user.accountType=="EMPLOYER">
                    <a class="btn btn-success btn-lg py-3 px-2 rounded-4" href="vacancies/add_vacancy/${user.id}">+<br>Создать<br>вакансию</a>
                <#else>
                    <a class="btn btn-success btn-lg py-3 px-2 rounded-4 me-3" href="resumes/add_resume${user.id}">+<br>Создать<br>резюме</a>
                    <a class="btn btn-dark btn-lg py-5 px-2 rounded-4 text-white" href="resumes/responded${user.id}">Отклики</a>
                </#if>
            </div>
        </div>


        <div class="mt-5 pb-5">
            <h2 class="section-header mb-3">
                <div class=""><#if user.accountType=="EMPLOYER">
                        Вакансии:
                    <#else>
                        Резюме:
                    </#if>
                </div>
            </h2>
            <div class="d-flex justify-content mx-3 ms-0 ps-0">
                <#if items??>
                    <#list items as item>
                        <div class="card bg-body-secondary text-black rounded-4 p-2 me-3">
                            <div class="card-body card-add-border fw-bold fs-4 pb-0 ps-1 ms-0 mb-2">
                                <#if item.name??>
                                    ${item.name}
                                <#else>
                                    ${item.name}
                                </#if>
                            </div>
                            <p class="p-2 py-0 pb-0 mb-0">Дата обновления:</p>
                            <div class="p-2 pt-1"><#if item.updateTime?has_content>${item.updateTime}<#else></#if> </div>
                            <div class="d-flex justify-content-center ">
                                <button class="btn btn-primary p-2 ms-0">Обновить</button>
                                <button class="btn btn btn-secondary p-2 ms-2">Редактировать</button>
                            </div>
                        </div>

                    </#list>
                <#else>
                    Пользователь ещё не создал
                    <#if user.accountType=="EMPLOYER">
                        ни одной вакансии
                    <#else>
                        ни одного резюме
                    </#if>
                </#if>
            </div>
        </div>
    </div>
</div>
</body>