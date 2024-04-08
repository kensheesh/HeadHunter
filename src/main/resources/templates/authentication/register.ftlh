<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>Http server Index page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
            integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
            crossorigin="anonymous"></script>

</head>

<body class="bg-body-secondary">
<div class="container">
    <div class="d-flex justify-content-center">
        <div class="card rounded-0 p-4 pb-1 mt-5 text-black shadow-sm"
             style="width: 28rem; height: 49rem;">
            <div class="card-body">
                <h5 class="card-title fs-4 mb-5 fw-bolder">Регистрация</h5>
                <form action="/register" class="mb-5 fs-6 fw-medium lh-sm" method="post">

                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-user fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill">
                            <label class="form-label" for="name">Имя</label>
                            <input type="text" id="name" name="name" class="form-control rounded-0"/>
                        </div>
                    </div>
                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-user fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill ">
                            <label class="form-label" for="surname">Фамилия</label>
                            <input type="text" id="surname" name="surname" class="form-control rounded-0"/>
                        </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
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

                    <div class="form-group mb-3 pb-0 ">
                        <label class="form-label mb-2" for="accountType">Тип Аккаунта</label>
                        <select class="form-select rounded-0" id="accountType" name="accountType">
                            <option value="EMPLOYER">Работодатель</option>
                            <option value="APPLICANT">Соискатель</option>
                        </select>
                    </div>


<#--                    <div class="form-group row">-->
<#--                        <label for="userType" class="col-2"></label>-->
<#--                        <select name="accountType" id="userType" class="form-brd-radius col-7">-->
<#--                            <option value="" disabled selected hidden>Тип аккаунта</option>-->
<#--                            <option value="EMPLOYER" class="form-brd-radius col-7">Работодатель</option>-->
<#--                            <option value="APPLICANT" class="form-brd-radius col-7">Соискатель</option>-->
<#--                        </select>-->
<#--                    </div>-->

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
</body>