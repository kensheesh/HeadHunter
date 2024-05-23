<#import "/spring.ftl" as spring>
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
        .dropdown {
            display: inline-block;
            position: relative;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: white;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
            min-width: 160px;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown-content a:hover {
            background-color: #f1f1f1;
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


<div class="container mt-5">
    <h1 class="text-center mb-4"><@spring.message 'navbar.vacancies'/></h1>
    <h3><@spring.message 'resumes.sortByCategory'/></h3>
    <form action="/vacancies" method="GET" class="mb-4">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
        </#if>
        <div class="row">
            <div class="col-md-4">
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
            <div class="col-md-4">
                <label>
                    <select class="form-select" name="sort" id="sort">
                        <option value="date" selected><@spring.message 'vacancies.sortByDate'/></option>
                        <option value="responses"><@spring.message 'vacancies.sortByResponses'/></option>
                    </select>
                </label>
            </div>
            <div class="col-md-2">
                <label>
                    <select class="form-select" name="order" id="order">
                        <option value="asc" selected><@spring.message 'vacancies.ascending'/></option>
                        <option value="desc"><@spring.message 'vacancies.descending'/></option>
                    </select>
                </label>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary"><@spring.message 'vacancies.applyFilter'/></button>
            </div>
        </div>
    </form>


    <div class="row">
        <#if vacanciesPage.content?size == 0>
            <p><@spring.message 'error.notFound'/></p>
        <#else>
            <#list vacanciesPage.content as vacancy>
                <div class="col-md-12">
                    <a href="/vacancies/${vacancy.id}" class="text-decoration-none text-dark">

                        <div class="card vacancy-card rounded-0">

                            <div class="card-body rounded-0">
                                <h5 class="card-title">${vacancy.name}</h5>
                                <p class="card-text"><@spring.message 'applying.salary'/>: ${vacancy.salary}</p>
                                <p class="card-text"><@spring.message 'vacancies.uploaded'/>: ${vacancy.updateTime}</p>
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
                   class="btn btn-primary"><@spring.message 'resumes.last'/></a>
            </#if>
            <#list 0..vacanciesPage.totalPages - 1 as page>
                <a href="/vacancies?page=${page}&category=${category}&sort=${sort}&order=${order}"
                   class="btn btn-primary">${page + 1}</a>
            </#list>
            <#if vacanciesPage.hasNext()>
                <a href="/vacancies?page=${vacanciesPage.nextPageable().pageNumber}&category=${category}&sort=${sort}&order=${order}"
                   class="btn btn-primary"><@spring.message 'resumes.next'/></a>
            </#if>
        </div>
    </div>
</div>

<script>
</script>
</body>
</html>
