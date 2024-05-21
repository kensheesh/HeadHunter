<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>All Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .user-card {
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
            </ul>
        </div>
    </div>
</nav>


<div class="container mt-5">
    <h1 class="text-center mb-4">
        <#if userType.name() == "APPLICANT">
            <@spring.message 'navbar.applicants'/>
        <#else>
            <@spring.message 'navbar.employers'/>
        </#if>

    </h1>

    <div class="row">
        <#if users.content?size == 0>
            <p><@spring.message 'error.notFound'/></p>
        <#else>
            <#list users.content as user>
                <div class="col-md-12">
                    <a href="/users/${user.id}" class="text-decoration-none text-dark">
                        <div class="card user-card rounded-0">
                            <div class="card-body rounded-0">
                                <h5 class="card-title">${user.name}</h5>
                                <p class="card-text">Email: ${user.email}</p>
                                <p class="card-text"><@spring.message 'authentication.phoneNumber'/>: ${user.phoneNumber}</p>
                            </div>
                        </div>
                    </a>
                </div>
            </#list>
        </#if>
    </div>


    <div class="row mt-4">
        <div class="col text-center">
            <#if users.hasPrevious()>
                <a href="?page=${users.previousPageable().pageNumber}"
                   class="btn btn-primary"><@spring.message 'resumes.last'/></a>
            </#if>
            <#list 0..users.totalPages - 1 as page>
                <a href="?page=${page}" class="btn btn-primary">${page + 1}</a>
            </#list>

            <#if users.hasNext()>
                <a href="?page=${users.nextPageable().pageNumber}"
                   class="btn btn-primary"><@spring.message 'resumes.next'/></a>
            </#if>
        </div>
    </div>
</div>

</body>
</html>
