<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-body-secondary">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-6">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h2 class="text-center">Create Vacancy</h2>
                </div>
                <div class="card-body">
                    <form action="/vacancies/create/${user.id}" method="post">
                        <div class="mb-3">
                            <label for="name" class="form-label">Vacancy Name:</label>
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="Enter vacancy name">
                        </div>
                        <div class="mb-3">
                            <label for="categoryName" class="form-label">Category:</label>
                            <select class="form-select" id="categoryName" name="categoryName">
                                <option value="Медицина" class="form-brd-radius col-6">Медицина</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="salary" class="form-label">Salary:</label>
                            <input type="number" class="form-control" id="salary" name="salary"
                                   placeholder="Enter salary">
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label">Description:</label>
                            <textarea class="form-control" id="description" name="description" rows="3"
                                      placeholder="Enter description"></textarea>
                        </div>
                        <div class="mb-3 row">
                            <div class="col">
                                <label for="experienceFrom" class="form-label">Experience From:</label>
                                <input type="text" class="form-control" id="experienceFrom" name="experienceFrom"
                                       placeholder="From">
                            </div>
                            <div class="col">
                                <label for="experienceTo" class="form-label">Experience To:</label>
                                <input type="text" class="form-control" id="experienceTo" name="experienceTo"
                                       placeholder="To">
                            </div>
                        </div>
                        <div class="mb-3 form-check">
                            <input type="hidden" name="isActive" value="false">
                            <input type="checkbox" class="form-check-input" id="isActiveCheckbox">
                            <label class="form-check-label" for="isActiveCheckbox">Active</label>
                        </div>
                        <div class="d-grid gap-2">
                            <button class="btn btn-primary" type="submit">Create Vacancy</button>
                            <button class="btn btn-secondary" type="reset">Clear</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function submitForm() {
        var form = document.getElementById('vacancyForm');
        var isActiveCheckbox = document.getElementById('isActiveCheckbox');

        if (isActiveCheckbox.checked) {
            form.elements['isActive'].value = 'true';
        } else {
            form.elements['isActive'].value = 'false';
        }
        form.submit();
    }
</script>
</body>
</html>
