<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" name="viewport">
    <meta content="ie=edge" http-equiv="X-UA-Compatible">
    <title>All Vacancies</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .vacancy-card {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Vacancies</h1>

    <div class="row">
        <#list vacancies as vacancy>
            <div class="col-md-12">
                <a href="/vacancies/${vacancy.id}" class="text-decoration-none text-dark">
                    <div class="card vacancy-card rounded-0">
                        <div class="card-body rounded-0">
                            <h5 class="card-title">${vacancy.name}</h5>
                            <p class="card-text">Salary: ${vacancy.salary}</p>
                            <p class="card-text">Updated on: ${vacancy.updateTime?string("yy:MM:dd")}</p>
                        </div>
                    </div>
                </a>
            </div>
        </#list>
    </div>
</div>
</body>
</html>
