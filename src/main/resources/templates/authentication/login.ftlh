<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>Http server Index page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body class="bg-body-secondary">
<div class="container">
    <div class="d-flex justify-content-center">
        <div class="card rounded-0 p-4 pb-1 mt-5 text-black shadow-sm"
             style="width: 28rem; height: 25.4rem;">
            <div class="card-body">
                <h5 class="card-title fs-4 mb-5 fw-bolder">Вход</h5>
                <form action="/login" method="post" id="login" class="mb-5 fs-6 fw-medium lh-sm">
                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-envelope fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill">
                            <label class="form-label" for="email">E-mail</label>
                            <input type="email" id="email" name="username" class="form-control rounded-0"/>
                        </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4 pb-1">
                        <i class="fas fa-lock fa-lg fa-fw"></i>
                        <div class="form-outline flex-fill">
                            <label class="form-label" for="password">Пароль</label>
                            <input type="password" id="password" name="password" class="form-control rounded-0"/>
                        </div>
                    </div>

                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4 mt-4 pt-3">
                        <input type="submit" class="btn btn-primary btn-lg px-5 rounded-0" value="Login">
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
                    <p class="card-text fs-6 mb-5 fw-medium text-center">Еще нет аккаунта? <a
                                href="/register">Зарегистрируйтесь</a></p>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    document.getElementById("login").addEventListener('submit', login);
    function login(event) {
        let form = event.target;
        let fromjsonUser = new FormData(form);
        let user = Object.fromEntries(fromjsonUser);
        let userInJsonToJS = JSON.stringify(user)
        localStorage.setItem('user', userInJsonToJS);
    }
</script>
</body>