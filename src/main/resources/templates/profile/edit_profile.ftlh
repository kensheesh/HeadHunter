<#import "../spring.ftl" as spring>
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

<div class="container">
    <h1 class="text-center mt-2 mb-5"><@spring.message 'profile.editProfile' /></h1>
    <div class="row">
        <div class="col-md-4">
            <div class="card-body bg-white ms-5" style="width: 20rem; height: 25rem">
                <div class="text-center">
                    <h3 class="pt-2"><@spring.message 'chat.avatar' /></h3>
                    <img class="avatar rounded-circle mt-4" src="/profile/avatars" alt="<@spring.message 'chat.avatar' />">
                </div>
                <form class="text-center my-3" action="/profile/avatars" method="post"
                      enctype="multipart/form-data">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                    </#if>
                    <input type="file" class="form-control file_form mb-5" id="inputGroupFile04"
                           aria-describedby="inputGroupFileAddon04" aria-label="Upload" name="file"
                           style="width: 16rem; margin-left: 2.1rem;">
                    <button class="btn btn-warning btn-lg px-5 rounded-0 mt-3" type="submit"><@spring.message 'profile.upload' /></button>
                </form>
            </div>
        </div>

        <div class="col-md-4 personal-info">
            <div class="card-body bg-white ms-5" style="width: 20rem; height: 25rem">
                <h3 class="pt-2 text-center"><@spring.message 'profile.personalInfo' /></h3>
                <form method="post" action="/profile/edit" id="edit_profile" class="form-horizontal ms-4">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                    </#if>
                    <div class="form-group mt-3">
                        <label class="col-md-3 control-label mt-2"><@spring.message 'authentication.name' /></label>
                        <div class="col-md-8">
                            <input class="form-control rounded-0" type="text" value="${user.name}" id="name"
                                   name="name" style="width: 17rem">
                        </div>
                        <#if user.accountType == "APPLICANT">
                            <div class="form-group mt-3">
                                <label class="col-md-3 control-label"><@spring.message 'authentication.surname' /></label>
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
                            <label class="col-md-3 control-label"><@spring.message 'profile.age' /></label>
                            <div class="col-md-8">
                                <input class="form-control rounded-0" type="number"
                                       value="<#if user.age?has_content>${user.age}<#else> <@spring.message 'profile.missing' /></#if>"
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
                                           value="<@spring.message 'profile.save' />" style="width: 8.5rem">
                                </div>
                                <div class="col-5">
                                    <input type="reset" class="btn btn-dark btn-lg ms-5 rounded-0 ps-2 mt-4"
                                           value="<@spring.message 'profile.cancel' />" style="width: 8.5rem;">
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card-body bg-white ms-5" style="width: 20rem; height: 25rem">
                <h3 class="text-center pt-2"><@spring.message 'authentication.password' /></h3>
                <form method="post" action="/profile/edit/password" id="edit_profile_password"
                      class="form-horizontal">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                    </#if>
                    <div class="form-group mt-3">
                        <label class="control-label ms-3 mt-2"><@spring.message 'profile.newPassword' /></label>
                        <div class="col-md-8">
                            <input class="form-control rounded-0 ms-3" type="password" style="width: 17.7rem;"
                                   id="password"
                                   name="password">
                        </div>
                    </div>
                    <div class="form-group mt-3">
                        <label class="control-label ms-3 mt-2"><@spring.message 'profile.confirmPassword' /></label>
                        <div class="col-md-8">
                            <input class="form-control rounded-0 ms-3" type="password" style="width: 17.7rem;"
                                   id="confirmPassword"
                                   name="confirmPassword">
                        </div>
                    </div>
                    <button class="ms-5 btn btn-primary btn-lg px-5 rounded-0 password_margin" type="submit"
                            style="width: 14.5rem"><@spring.message 'profile.change' />
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
