<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
        }

        .container {
            margin-top: 40px;
        }

        .navbar-brand {
            margin-left: 10px;
        }

        .right-buttons .btn {
            margin-left: 10px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <#if username??>
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">Profile</a>
                    </li>
                </#if>
                <li class="nav-item">
                    <a class="nav-link" href="/vacancies">Vacancies</a>
                </li>
                <#if username?? && type == "EMPLOYER">
                    <li class="nav-item">
                        <a class="nav-link" href="/resumes">Resumes</a>
                    </li>
                </#if>
                <#if username?? && type == "APPLICANT">
                    <li class="nav-item">
                        <a class="nav-link" href="/vacancies">Vacancies</a>
                    </li>
                </#if>
            </ul>
            <div class="right-buttons">
                <#if username??>
                    <a class="btn btn-primary btn-sign-in" href="/logout" id="logout">Log Out</a>
                <#else>
                    <a class="btn btn-primary btn-sign-in" href="/login">Sign In</a>
                    <a class="btn btn-success btn-sign-up" href="/register">Sign Up</a>
                </#if>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <#if username??>
        <h1>Hi ${username}</h1>
    <#else>
        <h1>WELCOME TO HOMEPAGE</h1>
    </#if>
    <script>
        document.getElementById('logout').addEventListener('click', () => {
            localStorage.removeItem('user')
        })
    </script>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.12.0/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"></script>
</body>
</html>
