<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1 class="text-center mt-2 mb-5">Edit Profile</h1>
    <div class="row">
        <div class="col-md-4">
            <div class="text-center">
                <img class="img-thumbnail rounded-0" src="/users/avatars/${user.id}" alt="photo">
            </div>
            <form class="text-center my-3" action="/users/avatars/${user.id}" method="post"
                  enctype="multipart/form-data">
                <input type="file" name="file" id="image_input" required><br><br>
                <button class="btn btn-warning" type="submit">Загрузить</button>
            </form>
        </div>
        <div class="col-md-8 personal-info">
            <div class="">
                <h3>Personal info</h3>
                <form method="post" action="/users/edit/${user.id}" id="edit_profile" class="form-horizontal">

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-3 control-label">Name:</label>
                                <div class="col-md-8">
                                    <input class="form-control rounded-0" type="text" value="${user.name}" id="name"
                                           name="name">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-3 control-label">Surname:</label>
                                <div class="col-md-8">
                                    <input class="form-control rounded-0" type="text"
                                           value="<#if user.surname?has_content>${user.surname}<#else> </#if>"
                                           id="surname"
                                           name="surname">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group mt-3">
                                <label class="col-md-3 control-label">Password:</label>
                                <div class="col-md-8">
                                    <input class="form-control rounded-0" type="password" value="${user.password}"
                                           id="password"
                                           name="password">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group mt-3">
                                <label class="col-md-3 control-label">Age:</label>
                                <div class="col-md-8">
                                    <input class="form-control rounded-0" type="number"
                                           value="<#if user.age?has_content>${user.age}<#else> отсутствует</#if>"
                                           id="age"
                                           name="age">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-8">
                            <input type="submit" class="btn btn-primary btn-sm px-5 rounded-0" value="Save Changes">
                            <span></span>
                            <input type="reset" class="btn btn-default" value="Cancel">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
