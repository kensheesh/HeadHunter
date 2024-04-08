<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resume</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"></head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card shadow">
                <div class="card-body">
                    <h3 class="card-title text-center text-primary mb-4">Информация об авторе:</h3>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted">Автор вакансии:</div>
                        <div class="col-md-8">${resume.user.name} ${resume.user.surname}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted">Email автора:</div>
                        <div class="col-md-8">${resume.user.email}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted">Телефон автора:</div>
                        <div class="col-md-8">${resume.user.phoneNumber}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card shadow">
                <div class="card-body">
                    <h3 class="card-title text-center text-primary mb-4">Резюме: ${resume.name}</h3>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted">Название резюме:</div>
                        <div class="col-md-8">${resume.name}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted">Категория:</div>
                        <div class="col-md-8">${resume.categoryName}</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4 text-muted">Ожидаемый уровень оклада:</div>
                        <div class="col-md-8">${resume.salary}</div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-4 text-muted">Образование:</div>
                        <div class="col-md-8">
                            <ul class="list-unstyled">
                                <#list resume.educationInfos as item>
                                    <li>${item.institution} (${item.degree}) - ${item.startDate} - ${item.endDate}</li>
                                </#list>
                            </ul>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-4 text-muted">Опыт работы:</div>
                        <div class="col-md-8">
                            <ul class="list-unstyled">
                                <#list resume.workExpInfos as item>
                                    <li>
                                        <div>Название компании: ${item.companyName}</div>
                                        <div>Должность: ${item.position}</div>
                                        <div>Обязанности: ${item.responsibilities}</div>
                                        <div>Стаж: ${item.years}</div>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-4 text-muted">Ссылки на соц. сети:</div>
                        <div class="col-md-8">
                            <ul class="list-unstyled">
                                <#list resume.contactInfos as contactInfo>
                                    <#if contactInfo.contactType == "Telegram">
                                        <li>telegram: ${contactInfo.value}</li>
                                    </#if>
                                    <#if contactInfo.contactType == "Facebook">
                                        <li>facebook: ${contactInfo.value}</li>
                                    </#if>
                                    <#if contactInfo.contactType == "Email">
                                        <li>email: ${contactInfo.value}</li>
                                    </#if>
                                    <#if contactInfo.contactType == "LinkedIn">
                                        <li>linkedin: ${contactInfo.value}</li>
                                    </#if>
                                    <#if contactInfo.contactType == "PhoneNumber">
                                        <li>phone number: ${contactInfo.value}</li>
                                    </#if>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
