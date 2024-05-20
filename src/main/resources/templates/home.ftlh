<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 40px;
        }

        .right-buttons .btn {
            margin-left: 10px;
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
<div class="container">
    <#if username??>
        <h1><@spring.message 'home.hi'/> ${username}</h1>
    <#else>
        <h1><@spring.message 'home.welcome'/></h1>
    </#if>
    <script>
        document.getElementById('logout').addEventListener('click', () => {
            localStorage.removeItem('user')
        })
    </script>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"></script>
</body>
</html>