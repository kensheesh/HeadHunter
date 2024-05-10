<!DOCTYPE html>
<html lang="ru">

<head>
    <style>
        .navbar-links a {
            margin-right: 10px;
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
                    <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="d-flex justify-content-center">
        <div class="card rounded-0 p-4 pb-1 mt-5 text-black shadow-sm"
             style="width: 28rem; height: 49rem;">
            <div class="card-body">
                <h5 class="card-title fs-4 mb-5 fw-bolder">Регистрация</h5>
                <form action="/register" class="mb-5 fs-6 fw-medium lh-sm" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                    </#if>
                    <div class="form-group mb-3 pb-0 ">
                        <label class="form-label mb-2" for="accountType">Тип Аккаунта</label>
                        <select class="form-select rounded-0" id="accountType" name="accountType">
                            <option value="APPLICANT">Соискатель</option>
                            <option value="EMPLOYER">Работодатель</option>
                        </select>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-user fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill">
                            <label class="form-label" for="name">Имя</label>
                            <input type="text" id="name" name="name" class="form-control rounded-0"/>
                        </div>
                    </div>
                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-user fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill " id="surnameField">
                            <label class="form-label" for="surname">Фамилия</label>
                            <input type="text" id="surname" name="surname" class="form-control rounded-0"/>
                        </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4 pb-1" id="emailField">
                        <i class="fas fa-envelope fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill">
                            <label class="form-label" for="email">E-mail</label>
                            <input type="email" id="email" name="email" class="form-control rounded-0"/>
                        </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-lock fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill">
                            <label class="form-label" for="password">Пароль</label>
                            <input type="password" id="password" name="password" class="form-control rounded-0"/>
                        </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-phone-alt fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill">
                            <label class="form-label" for="phoneNumber">Номер Телефона</label>
                            <input type="tel" id="phoneNumber" name="phoneNumber" class="form-control rounded-0"/>
                        </div>
                    </div>

                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4 mt-4 pt-3">
                        <button type="submit" class="btn btn-primary btn-lg px-5 rounded-0">Зарегистрироваться</button>
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
                    <p class="card-text fs-6 mb-5 fw-medium text-center">Уже зарегистрированы? <a
                                href="/login">Войдите</a></p>
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

        accountTypeSelect.addEventListener('change', function () {
            const selectedValue = accountTypeSelect.value;

            if (selectedValue === 'EMPLOYER') {
                nameInput.previousElementSibling.textContent = 'Имя компании';
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
