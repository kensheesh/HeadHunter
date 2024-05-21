<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
            integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        .container {
            margin-top: 40px;
        }

        .right-buttons .btn {
            margin-left: 10px;
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
                            <button class="btn btn-primary btn-sign-in"
                                    id="logout"><@spring.message 'navbar.logout' /></button>
                        </form>
                    <#else>
                        <a class="btn btn-primary btn-sign-in" href="/logout"
                           id="logout"><@spring.message 'navbar.logout' /></a>
                    </#if>
                <#else>
                    <a class="btn btn-primary btn-sign-in" href="/login"><@spring.message 'navbar.signIn' /></a>
                    <a class="btn btn-primary btn-sign-up" href="/register"
                       style="width: 8rem"><@spring.message 'navbar.signUp' /></a>
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