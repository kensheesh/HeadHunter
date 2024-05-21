<#import "../spring.ftl" as spring>
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
<div class="container">
    <h2 class="text-black fs-2 text-center mt-2"> <@spring.message 'applying.respondToVacancy'/></h2>

    <div class="row gutters-sm mt-5">
        <div class="col-md-4 mb-4">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-8">
                            <h6 class="text-secondary mt-1"><@spring.message 'applying.companyName'/></h6>
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
                            <h6 class="text-secondary mt-1"><@spring.message 'applying.companiesEmail'/></h6>
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
                            <h6 class="text-secondary mt-1"><@spring.message 'applying.companiesNumber'/></h6>
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
                            <h6 class="mb-0 pt-3"><@spring.message 'applying.nameOfVacancy'/>:</h6>
                        </div>
                        <div class="col-sm-9 text-black fs-5 pt-2">
                            <p> ${vacancy.name}</p>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0"><@spring.message 'applying.casingOfVacancy'/></h6>
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
                        <h3><@spring.message 'applying.yourResponse'/></h3>
                    </div>
                    <form action="/apply/vacancy/${vacancy.id}" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <input type="hidden" name="vacancyId" value="${vacancy.id}">
                        <small><@spring.message 'applying.textOfMessage'/></small>
                        <label>
                            <input class="form-control mb-2" type="text" placeholder="<@spring.message 'applying.messageToEmployer'/>"
                                   name="message" style="width: 17rem" id="message">
                        </label>

                        <p class="mb-1"><@spring.message 'applying.createResumeOrPickBelow'/></p>
                        <a href="/resumes/create" class="btn btn-secondary my-3"><@spring.message 'applying.createResume'/></a>
                        <#if resumes??>
                            <select class="form-select border-dark"
                                    aria-label="Default select example" name="resumeId" id="resumeId">
                                <#list resumes as resume>
                                    <small><@spring.message 'applying.resume'/></small>
                                    <option value="${resume.id}" class="form-brd-radius col-6">
                                        ${resume.name}</option>
                                </#list>
                            </select>
                        </#if>
                        <button type="submit" class="btn btn-primary my-3"> <@spring.message 'applying.send'/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>