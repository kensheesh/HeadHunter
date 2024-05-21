<#import "spring.ftl" as spring>
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
                    <a class="nav-link active" aria-current="page" href="/"><@spring.message 'navbar.home'/></a>
                </li>
                <#if username??>
                    <li class="nav-item">
                        <a class="nav-link" href="/profile"><@spring.message 'navbar.profile' /></a>
                    </li>
                </#if>
                <li class="nav-item">
                    <a class="nav-link" href="/vacancies"><@spring.message 'navbar.vacancies' /></a>
                </li>
                <#if username??>

                    <#if type == "EMPLOYER">
                        <li class="nav-item">
                            <a class="nav-link" href="/resumes"><@spring.message 'navbar.resumes' /></a>
                        </li>
                    </#if>

                    <#if type = "APPLICANT">
                        <li class="nav-item">
                            <a class="nav-link" href="/users/employers"><@spring.message 'navbar.employers' /></a>
                        </li>
                    </#if>

                    <#if type = "EMPLOYER">
                        <li class="nav-item">
                            <a class="nav-link" href="/users/applicants"><@spring.message 'navbar.applicants' /></a>
                        </li>
                    </#if>
                </#if>
            </ul>
            <div class="right-buttons">
                <#if username??>
                    <#if _csrf??>
                        <form action="/logout" method="post">
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                   value="${(_csrf.token)!'--no-token--'}"/>
                            <button class="btn btn-primary btn-sign-in" id="logout"><@spring.message 'navbar.logout' /></button>
                        </form>
                    <#else>
                        <a class="btn btn-primary btn-sign-in" href="/logout" id="logout"><@spring.message 'navbar.logout' /></a>
                    </#if>
                <#else>
                    <a class="btn btn-primary btn-sign-in" href="/login"><@spring.message 'navbar.signIn' /></a>
                    <a class="btn btn-primary btn-sign-up" href="/register" style="width: 8rem"><@spring.message 'navbar.signUp' /></a>
                </#if>
            </div>
        </div>
    </div>
</nav>


<div>
    <div class="container">
        <div class="elem-container">
            <h1 class="header-top d-flex justify-content-center pt-3 text-dark-emphasis"><@spring.message 'profile.personalArea' />
                <#if user.accountType=="EMPLOYER">
                    <@spring.message 'profile.applicant' />
                <#else>
                    <@spring.message 'profile.employer' />
                </#if>
            </h1>
        </div>

        <div class="row mt-5 ms-1 mb-5">
            <div class="col-4 bg-white text-dark-emphasis" style="width: 27rem; height: 10rem">
                <div class="row ms-2">
                    <div class="col-5 mt-2 ">
                        <img class="avatar rounded-circle" src="/profile/avatars" alt="<@spring.message 'chat.avatar' />">
                    </div>
                    <div class="col">
                        <div class="card-body mt-3">
                            <h5 class="card-title">
                                <div class="fs-5">
                                    <#if user.accountType=="EMPLOYER">
                                    <#assign str = user.name>
                                    <span><@spring.message 'profile.company' /></span>
                                    ${str}
                                    <p class="card-text mt-2">
                                        <small class="fs-6">
                                            <p>Email: ${user.email}</p>
                                            <p><@spring.message 'authentication.phoneNumber' />: ${user.phoneNumber}</p>
                                        </small>
                                    </p>
                                    <#else>

                                    <#assign variable1 = user.name>
                                    <#assign variable2 = user.surname>
                                    <#assign combinedValue = variable1 + " " + variable2>
                                    <#assign result = combinedValue>
                                    ${result}
                                </div>

                                <p class="card-text mt-2">
                                    <small class="fs-6"><@spring.message 'profile.age' />:
                                        <#if user.age?has_content>${user.age}<#else> отсутствует</#if>
                                        <p>Email: ${user.email}</p>
                                        <p><@spring.message 'authentication.phoneNumber' />: ${user.phoneNumber}</p>
                                    </small>
                                </p> </#if>
                            </h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="">
            <h2 class="">
                <#if user.accountType=="EMPLOYER">
                    <@spring.message 'navbar.vacancies' />:
                <#else>
                    <@spring.message 'navbar.resumes' />:
                </#if>
            </h2>
            <#if items.content?size == 0>
                <p><@spring.message 'error.notFound' /></p>
            <#else>
                <#list items.content as item>
                    <#if user.accountType = "APPLICANT">
                        <#assign link = "/resumes/" + "${item.id}">
                    <#else>
                        <#assign link = "/vacancies/" +"${item.id}">
                    </#if>

                    <a href="${link}" class="text-decoration-none text-dark">
                        <div class="card mb-3">
                            <div class="card-body rounded-0">
                                <h5 class="card-title">${item.name}</h5>
                                <p class="card-text"><@spring.message 'applying.publishedDate' />:${item.updateTime}</p>
                            </div>
                        </div>
                    </a>
                </#list>
            </#if>
        </div>
    </div>
    <#if items.content?size != 0>
        <div class="row mt-4">
            <div class="col text-center">
                <#if items.hasPrevious()>
                    <a href="?page=${items.previousPageable().pageNumber}"
                       class="btn btn-primary"><@spring.message 'resumes.last' />
                    </a>
                </#if>
                <#list 0..items.totalPages - 1 as page>
                    <a href="?page=${page}" class="btn btn-primary">${page + 1}</a>
                </#list>

                <#if items.hasNext()>
                    <a href="?page=${items.nextPageable().pageNumber}"
                       class="btn btn-primary"><@spring.message 'resumes.next' /></a>
                </#if>
            </div>
        </div>
    </#if>
</div>
</body>