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
                <li class="nav-item">
                    <a class="nav-link" href="/resumes">Resumes</a>
                </li>
            </ul>l
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="card-body bg-light mb-3 pb-4 ps-2">
        <h3 class="card-title text-center text-primary mb-4">Информация об авторе:</h3>
        <div class="row mb-3">
            <div class="col-md-4 text-muted">Автор вакансии:</div>
            <div class="col-md-8">${vacancy.user.name}</div>
        </div>
        <div class="row mb-3">
            <div class="col-md-4 text-muted">Email автора:</div>
            <div class="col-md-8">${vacancy.user.email}</div>
        </div>
        <div class="row mb-3">
            <div class="col-md-4 text-muted">Телефон автора:</div>
            <div class="col-md-8">${vacancy.user.phoneNumber}</div>
        </div>
    </div>

    <h3 class="text-center text-black mb-4">Вакансия: ${vacancy.name}</h3>
    <div class="elem-container bg-light p-3 ">
        <div class="row mb-3">
            <div class="col-md-3 text-secondary">Название вакансии:</div>
            <div class="col-md-9">${vacancy.name}</div>
        </div>
        <div class="row mb-3">
            <div class="col-md-3 text-secondary">Описание вакансии:</div>
            <div class="col-md-9">${vacancy.description}</div>
        </div>
        <div class="row mb-3">
            <div class="col-md-3 text-secondary">Категория:</div>
            <div class="col-md-9">${vacancy.categoryName}</div>
        </div>
        <div class="row mb-3">
            <div class="col-md-3 text-secondary">Заработная плата:</div>
            <div class="col-md-9">${vacancy.salary}</div>
        </div>
        <div class="row mb-3">
            <div class="col-md-3 text-secondary">Требуемый опыт:</div>
            <div class="col-md-9">
                <div class="d-flex align-items-center">
                    <div class="mr-2">от</div>
                    <div class="form-brd-radius bg-white p-1">${vacancy.experienceFrom}</div>
                    <div class="mr-2 ml-3">до</div>
                    <div class="form-brd-radius bg-white p-1">${vacancy.experienceTo}</div>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-3 text-secondary">Дата публикации:</div>
            <div class="col-md-9">${vacancy.updateTime}</div>
        </div>
        <#if type == "APPLICANT">
            <div class="col-md-12 d-flex justify-content-center mt-5">
                <a class="btn btn-success btn-block w-50" href="/apply/vacancy/${vacancy.id}">Откликнуться</a>
            </div>
        </#if>

    </div>
</div>
</body>