<!DOCTYPE html>
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 40px;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 0;
        }

        .navbar-brand {
            margin-left: 10px;
        }

        .navbar-links {
            display: flex;
            align-items: center;
        }

        .navbar-links a {
            margin-left: 10px;
            color: #212529;
            text-decoration: none;
            padding: 14px 16px;
            transition: background-color 0.3s ease;
        }

        .navbar-links a:hover {
            background-color: #ddd;
        }

        .navbar-links a.active {
            background-color: greenyellow;
            color: white;
            border-radius: 4px;
        }

        .navbar-links a.disabled {
            color: #6c757d;
            pointer-events: none;
        }

        /* Style for the "Sign In" and "Sign Up" buttons */
        .right-buttons {
            display: flex;
            align-items: center;
        }

        .right-buttons .btn {
            margin-left: 10px;
        }

        .right-buttons .btn-sign-in,
        .right-buttons .btn-sign-up {
            background-color: greenyellow;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 5px 10px;
            cursor: pointer;
        }

        .right-buttons .btn-sign-in:hover,
        .right-buttons .btn-sign-up:hover {
            background-color: #0056b3;
        }
    </style>
    <title>Home</title>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <div class="navbar-links">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
            <#if username??>
                <a class="nav-link" href="/profile">Profile</a>
            </#if>
            <a class="nav-link" href="/vacancies">Vacancies</a>
            <#if username?? && type == "EMPLOYER">
                <a class="nav-link" href="/resumes">Resumes</a>
            </#if>
            <#if username?? && type == "APPLICANT">
                <a class="nav-link" href="/vacancies">Vacancies</a>
            </#if>
        </div>
        <div class="right-buttons">
            <#if username??>
                <a class="btn btn-primary btn-sign-in" href="/logout" id="logout">Log Out</a>
            <#else>
                <a class="btn btn-primary btn-sign-in" href="/login">Sign In</a>
                <a class="btn btn-success btn-sign-up" href="/register">Sign Up</a>

            </#if>
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
    document.getElementById('logout').addEventListener('click',()=>{
        localStorage.removeItem('user')
    })
</script>

</div>
</body>
</html>
