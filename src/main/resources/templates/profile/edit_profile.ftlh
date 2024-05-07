<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        .file_form {
            margin-left: 9.4rem;
        }

        .password_margin {
            margin-top: 7.45rem;
        }

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
                    <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/vacancies">Vacancies</a>
                </li>

                <#if type = "EMPLOYER">
                    <li class="nav-item">
                        <a class="nav-link" href="/resumes">Resumes</a>
                    </li>
                </#if>

                <#if type = "APPLICANT">
                    <li class="nav-item">
                        <a class="nav-link" href="/users/employers">Employers</a>
                    </li>
                </#if>

                <#if type = "EMPLOYER">
                    <li class="nav-item">
                        <a class="nav-link" href="/users/applicants">Applicants</a>
                    </li>
                </#if>
            </ul>
            <div class="right-buttons">
                <a class="btn btn-primary btn-sign-in" href="/logout" id="logout">Log Out</a>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <h1 class="text-center mt-2 mb-5">Edit Profile</h1>
    <div class="row">
        <div class="col-md-4">
            <div class="card-body bg-white ms-5" style="width: 20rem; height: 25rem">
                <div class="text-center">
                    <h3 class="pt-2">Avatar</h3>
                    <img class="avatar rounded-circle mt-4" src="/profile/avatars" alt="photo">
                </div>
                <form class="text-center my-3" action="/profile/avatars" method="post"
                      enctype="multipart/form-data">
                    <input type="file" class="form-control file_form mb-5" id="inputGroupFile04"
                           aria-describedby="inputGroupFileAddon04" aria-label="Upload" name="file"
                           style="width: 16rem; margin-left: 2.1rem;">
                    <button class="btn btn-warning btn-lg px-5 rounded-0 mt-3" type="submit">Upload</button>
                </form>
            </div>
        </div>

        <div class="col-md-4 personal-info">
            <div class="card-body bg-white ms-5" style="width: 20rem; height: 25rem">
                <h3 class="pt-2 text-center">Personal info</h3>
                <form method="post" action="/profile/edit" id="edit_profile" class="form-horizontal ms-4">
                    <div class="form-group mt-3">
                        <label class="col-md-3 control-label mt-2">Name</label>
                        <div class="col-md-8">
                            <input class="form-control rounded-0" type="text" value="${user.name}" id="name"
                                   name="name" style="width: 17rem">
                        </div>
                        <#if user.accountType == "APPLICANT">
                            <div class="form-group mt-3">
                                <label class="col-md-3 control-label">Surname</label>
                                <div class="col-md-8">
                                    <input class="form-control rounded-0" type="text"
                                           value="<#if user.surname?has_content>${user.surname}<#else> </#if>"
                                           id="surname" name="surname" style="width: 17rem">
                                </div>
                            </div>
                        </#if>
                    </div>
                    <#if user.accountType == "APPLICANT">
                        <div class="form-group mt-3">
                            <label class="col-md-3 control-label">Age</label>
                            <div class="col-md-8">
                                <input class="form-control rounded-0" type="number"
                                       value="<#if user.age?has_content>${user.age}<#else> отсутствует</#if>"
                                       id="age" name="age" style="width: 17rem">
                            </div>
                        </div>
                    </#if>
                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-8">
                            <div class="row">
                                <div class="col-5">
                                    <input type="submit" class="btn btn-primary btn-lg  rounded-0 mt-4"
                                           value="Save" style="width: 8.5rem">
                                </div>
                                <div class="col-5">
                                    <input type="reset" class="btn btn-dark btn-lg ms-5 rounded-0 ps-2 mt-4"
                                           value="Cancel" style="width: 8.5rem;">
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card-body bg-white ms-5" style="width: 20rem; height: 25rem">
                <h3 class="text-center pt-2">Password</h3>
                <form method="post" action="/profile/edit/password" id="edit_profile_password"
                      class="form-horizontal">
                    <div class="form-group mt-3">
                        <label class="control-label ms-3 mt-2">New password:</label>
                        <div class="col-md-8">
                            <input class="form-control rounded-0 ms-3" type="password" style="width: 17.7rem;"
                                   id="password"
                                   name="password">
                        </div>
                    </div>
                    <div class="form-group mt-3">
                        <label class="control-label ms-3 mt-2">Confirm Password:</label>
                        <div class="col-md-8">
                            <input class="form-control rounded-0 ms-3" type="password" style="width: 17.7rem;"
                                   id="confirmPassword"
                                   name="confirmPassword">
                        </div>
                    </div>
                    <button class="ms-5 btn btn-primary btn-lg px-5 rounded-0 password_margin" type="submit"
                            style="width: 14.5rem">Change
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
