<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Vacancy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-body-secondary">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h2 class="text-center">Edit Vacancy</h2>
                </div>
                <div class="card-body">
                    <form action="/vacancies/edit/${vacancy.id}" method="post" id="vacancyForm">
                        <input type="hidden" name="id" value="${vacancy.id}">
                        <div class="mb-3">
                            <label for="name" class="form-label">Vacancy Name:</label>
                            <input type="text" class="form-control" id="name" name="name"
                                   value="${vacancy.name}">
                        </div>
                        <div class="mb-3">
                            <label for="categoryName" class="form-label">Category:</label>
                            <select class="form-select" id="categoryName" name="categoryName">
                                <option value="Медицина" class="form-brd-radius col-6">Медицина</option>
                                <option value="Информационные технологии">Информационные технологии</option>
                                <option value="Маркетинг">Маркетинг</option>
                                <option value="HrРекрутинг">HrРекрутинг</option>
                                <option value="Бухгалтерия">Бухгалтерия</option>
                                <option value="Образование">Образование</option>
                                <option value="Производство">Производство</option>
                                <option value="Строительство">Строительство</option>
                                <option value="Искусство и дизайн">Искусство и дизайн</option>
                                <option value="Путешествия и туризм">Путешествия и туризм</option>
                                <option value="Искусственный интеллект">Искусственный интеллект</option>
                                <option value="Экология и устойчивое развитие">Экология и устойчивое развитие</option>
                                <option value="Кибербезопасность">Кибербезопасность</option>
                                <option value="Финансовые технологии (FinTech)">Финансовые технологии (FinTech)</option>
                                <option value="Электромобили и зеленая энергия">Электромобили и зеленая энергия</option>                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="salary" class="form-label">Salary:</label>
                            <input type="text" class="form-control" id="salary" name="salary"
                                   value="${vacancy.salary}">
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label">Description:</label>
                            <textarea class="form-control" id="description" name="description"
                                      rows="3">${vacancy.description}</textarea>
                        </div>
                        <label for="isActiveCheckbox" class="form-label">Active:</label>
                        <input type="checkbox" name="isActive" value="true" class="form-check-input"
                               id="isActiveCheckbox">

                        <div class="d-grid gap-2">
                            <button class="btn btn-primary" type="submit">Save Changes</button>
                            <a class="btn btn-secondary" href="/profile">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    document.getElementById('vacancyForm').addEventListener('submit', function(event) {
        // Получаем значение поля "salary"
        var salaryInput = document.getElementById('salary');
        var salaryValue = salaryInput.value.trim();

        // Преобразуем значение в число
        var salaryNumber = parseFloat(salaryValue);

        // Проверяем, удалось ли преобразовать в число
        if (!isNaN(salaryNumber)) {
            // Если удалось, обновляем значение в поле перед отправкой
            salaryInput.value = salaryNumber;
        } else {
            // Если не удалось, предотвращаем отправку формы и выводим сообщение об ошибке
            event.preventDefault();
            alert('Please enter a valid number for salary.');
        }
    });
</script>
</body>
</html>
