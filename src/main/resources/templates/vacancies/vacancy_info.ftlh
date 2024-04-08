<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
          name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<div class="container mt-5">
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
        <div class="row mb-3">
            <div class="col-md-3 text-secondary">Профиль компании:</div>
            <div class="col-md-9">
                <a href="/companies/{employerId}" class="btn btn-primary rounded-0">Перейти к профилю компании</a>
            </div>
        </div>
        <div class="col-md-12 d-flex justify-content-center mt-5">
            <button class="btn btn-success btn-block w-50">Откликнуться</button>
        </div>
    </div>
</div>