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
             style="
             width: 28rem; height: 27.4rem;">
            <div class="card-body">
                <h5 class="card-title fs-4 mb-5 fw-bolder">Вход</h5>
                <form action="/login" method="post" id="login-form" class="mb-5 fs-6 fw-medium lh-sm">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
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
                    <a href="/forgot_password">Забыли пароль?</a>
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
    let form = document.getElementById('login-form')

    // form.addEventListener('submit', onLoginHandler)

    function onLoginHandler(event) {
        event.preventDefault()

        let target = event.target
        let data = new FormData(target)
        let user = Object.fromEntries(data)

        let headers = new Headers()
        headers.set('Authorization', 'Basic ' + btoa(user.email + ':' + user.password))

        fetchAuthorized(headers)
        // let userJson = JSON.stringify(user)
        // localStorage.setItem('user', userJson)
        // window.location.href = 'http://localhost:8089/'

    }

    async function fetchAuthorized(auth) {
        try {
            await makeRequest('http://localhost:9089/api/auth/login', updateOptions({
                method: 'post',
                headers: auth
            }));
        } catch (e) {
            alert(e)
        }
    }

    // btoa()
    // atob()

    function makeHeaders() {
        let user = restoreUser()
        // console.log(user);

        let headers = new Headers()
        headers.set('Content-Type', 'application/json')
        if (user) {
            headers.set('Authorization', 'Basic ' + btoa(user.email + ':' + user.password))
        }
        return headers
    }

    async function makeRequest(url, options) {
        let settings = options || requestSettings
        let response = await fetch(url, settings)

        if (response.ok) {
            return await response.json()
        } else {
            let err = new Error(response.status.toString())
            err.response = response
            console.log(err);
            throw err
        }
    }

    function updateOptions(options) {
        console.log(options);
        let update = {...options}
        update.mode = 'cors'
        update.headers = makeHeaders()
        return update
    }

    const requestSettings = {
        method: 'get',
        headers: makeHeaders()
    }

    function restoreUser() {
        return JSON.parse(localStorage.getItem('user'))
    }
</script>
</body>