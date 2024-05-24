<#import "../spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">

<head>
    <style>
        .navbar-links a {
            margin-right: 10px;
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
    <title>Http server Index page</title>
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
        </div>
    </div>
</nav>

<div class="container">
    <div class="d-flex justify-content-center">
        <div class="card rounded-0 p-4 pb-1 mt-5 text-black shadow-sm"
             style="width: 28rem">
            <div class="card-body">
                <h5 class="card-title fs-4 mb-5 fw-bolder"><@spring.message 'authentication.registerSubject'/></h5>
                <form action="/register" class="mb-2 fs-6 fw-medium lh-sm" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
                    <div class="form-group mb-3 pb-0 ">
                        <label class="form-label mb-2"
                               for="accountType"><@spring.message 'authentication.accountsType'/></label>
                        <select class="form-select rounded-0" id="accountType" name="accountType">
                            <option value="APPLICANT"><@spring.message 'authentication.applicant'/></option>
                            <option value="EMPLOYER"><@spring.message 'authentication.employer'/></option>
                        </select>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-user fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill">
                            <label class="form-label" for="name"><@spring.message 'authentication.name'/></label>
                            <@spring.formInput "userCreateDto.name" 'type="text" id="name" name="name" class="form-control rounded-0"'/>
                            <@spring.showErrors "<br>" "error text-danger"/>
                        </div>
                    </div>
                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-user fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill " id="surnameField">
                            <label class="form-label" for="surname"><@spring.message 'authentication.surname'/></label>
                            <@spring.formInput "userCreateDto.surname" 'type="text" id="surname" name="surname" class="form-control rounded-0"'/>
                            <@spring.showErrors "<br>" "error text-danger"/>
                        </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4 pb-1" id="emailField">
                        <i class="fas fa-envelope fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill">
                            <label class="form-label" for="email">E-mail</label>
                            <@spring.formInput "userCreateDto.email" 'type="email" id="email" name="email" class="form-control rounded-0"'/>
                            <@spring.showErrors "<br>" "error text-danger"/>
                        </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-lock fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill">
                            <label class="form-label"
                                   for="password"><@spring.message 'authentication.password'/></label>
                            <@spring.formInput "userCreateDto.password" 'id="password" name="password" class="form-control rounded-0"'/>
                            <@spring.showErrors "<br>" "error text-danger"/>
                        </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-phone-alt fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill">
                            <label class="form-label"
                                   for="phoneNumber"><@spring.message 'authentication.phoneNumber'/></label>
                            <@spring.formInput "userCreateDto.phoneNumber" 'type="tel" id="phoneNumber" name="phoneNumber" class="form-control rounded-0"'/>
                            <@spring.showErrors "<br>" "error text-danger"/>
                        </div>
                    </div>

                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4 mt-4 pt-3">
                        <button type="submit"
                                class="btn btn-primary btn-lg px-5 rounded-0"><@spring.message 'authentication.registerTo'/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="d-flex justify-content-center">
        <div class="card rounded-0 mt-3 text-black shadow-sm"
             style="width: 28rem; height: 4rem;">
            <div class="d-flex align-items-center pb-0">
                <div class="card-body">
                    <p class="card-text fs-6 mb-5 fw-medium text-center"><@spring.message 'authentication.areRegistered'/>
                        <a
                                href="/login"><@spring.message 'navbar.signIn'/></a></p>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const accountTypeSelect = document.getElementById('accountType');
        const nameInput = document.getElementById('name');
        const surnameField = document.getElementById('surnameField');
        const emailField = document.getElementById('emailField');

        accountTypeSelect.addEventListener('change', function () {
            const selectedValue = accountTypeSelect.value;

            if (selectedValue === 'EMPLOYER') {
                nameInput.previousElementSibling.textContent = '<@spring.message 'authentication.nameOfCompany'/>';
                surnameField.style.display = 'none';
                emailField.style.marginTop = '-40px';

            } else {
                nameInput.previousElementSibling.textContent = 'Имя';
                surnameField.style.display = 'block';
                emailField.style.marginTop = '-10px';

            }
        });
    });
</script>
</body>
</html>
