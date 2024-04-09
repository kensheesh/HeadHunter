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
<div class="container">
    <h1 class="text-center mt-2 mb-5">Edit Profile</h1>
    <div class="row">
        <div class="col-md-4">
            <div class="card-body bg-white ms-5" style="width: 20rem; height: 25rem">
                <div class="text-center">
                    <h3 class="pt-2">Avatar</h3>
                    <img class="img-thumbnail rounded-0 mt-4" src="/users/avatars/${user.id}" alt="photo">
                </div>
                <form class="text-center my-3" action="/users/avatars/${user.id}" method="post"
                      enctype="multipart/form-data">
                    <input type="file" class="form-control file_form mb-5" id="inputGroupFile04"
                           aria-describedby="inputGroupFileAddon04" aria-label="Upload" name="file"
                           style="width: 7rem;">
                    <button class="btn btn-warning btn-lg px-5 rounded-0" type="submit">Загрузить</button>
                </form>
            </div>
        </div>


        <div class="col-md-4 personal-info">
            <div class="card-body bg-white ms-5" style="width: 20rem; height: 25rem">
                <h3 class="pt-2 text-center">Personal info</h3>
                <form method="post" action="/users/edit/${user.id}" id="edit_profile"
                      class="form-horizontal ms-4">
                    <div class="form-group mt-3">
                        <label class="col-md-3 control-label mt-2">Name</label>
                        <div class="col-md-8">
                            <input class="form-control rounded-0" type="text" value="${user.name}" id="name"
                                   name="name" style="width: 17rem">
                        </div>
                        <div class="form-group mt-3">
                            <label class="col-md-3 control-label">Surname</label>
                            <div class="col-md-8">
                                <input class="form-control rounded-0" type="text"
                                       value="<#if user.surname?has_content>${user.surname}<#else> </#if>"
                                       id="surname"
                                       name="surname" style="width: 17rem">
                            </div>
                        </div>
                    </div>
                    <div class="form-group mt-3">
                        <label class="col-md-3 control-label">Age</label>
                        <div class="col-md-8">
                            <input class="form-control rounded-0" type="number"
                                   value="<#if user.age?has_content>${user.age}<#else> отсутствует</#if>"
                                   id="age"
                                   name="age" style="width: 17rem">
                        </div>
                    </div>
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
                <form method="post" action="/users/edit/password/${user.id}" id="edit_profile_password"
                      class="form-horizontal">
                    <div class="form-group mt-3">
                        <label class="control-label ms-3 mt-2">New password:</label>
                        <div class="col-md-8">
                            <input class="form-control rounded-0 ms-3" type="password" style="width: 17.7rem;" id="password"
                                   name="password">
                        </div>
                    </div>
                    <div class="form-group mt-3">
                        <label class="control-label ms-3 mt-2">Confirm Password:</label>
                        <div class="col-md-8">
                            <input class="form-control rounded-0 ms-3" type="password" style="width: 17.7rem;" id="confirmPassword"
                                   name="confirmPassword">
                        </div>
                    </div>
                    <button class="ms-5 btn btn-primary btn-lg px-5 rounded-0 password_margin" type="submit" style="width: 14.5rem">Изменить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
