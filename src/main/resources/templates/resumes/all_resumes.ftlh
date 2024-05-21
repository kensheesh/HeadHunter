<#import "../spring.ftl" as spring>
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
    <h1 class="text-center mb-4"><@spring.message 'applying.resume'/></h1>
    <h3><@spring.message 'resumes.sortByCategory'/></h3>
    <form class="row mb-4" id="categoryForm">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
        </#if>
        <div class="col-4">
            <select class="form-select border-dark" aria-label="<@spring.message 'resumes.select'/>"
                    name="category" id="category">
                <option selected><@spring.message 'resumes.selectCategory'/></option>
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
        <button type="submit" class="btn btn-primary col-2 ms-3"> OK</button>
    </form>

    <#if resumesPage.content?size == 0>
        <p><@spring.message 'resumes.notFound'/></p>
    <#else>
        <div class="row">
            <#list resumesPage.content as resume>
                <div class="col-md-12">
                    <a href="/resumes/${resume.id}" class="text-decoration-none text-dark">
                        <div class="card vacancy-card rounded-0">
                            <div class="card-body rounded-0">
                                <h5 class="card-title">${resume.name}</h5>
                                <p class="card-text"><@spring.message 'applying.salary'/>: ${resume.salary}</p>
                                <p class="card-text"><@spring.message 'applying.publishedDate'/>: ${resume.updateTime}</p>
                            </div>
                        </div>
                    </a>
                </div>
            </#list>
        </div>
    </#if>


    <div class="row mt-4">
        <div class="col text-center">
            <#if resumesPage.hasPrevious()>
                <a href="/resumes?page=${resumesPage.previousPageable().pageNumber}&category=${category}"
                   class="btn btn-primary"><@spring.message 'resumes.last'/></a>
            </#if>
            <#list 0..resumesPage.totalPages - 1 as page>
                <a href="/resumes?page=${page}&category=${category}" class="btn btn-primary">${page + 1}</a>
            </#list>

            <#if resumesPage.hasNext()>
                <a href="/resumes?page=${resumesPage.nextPageable().pageNumber}&category=${category}"
                   class="btn btn-primary"><@spring.message 'resumes.next'/></a>
            </#if>
        </div>
    </div>
</div>
</body>
</html>
