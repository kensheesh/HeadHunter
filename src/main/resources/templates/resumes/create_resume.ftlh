<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Resume</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group label {
            font-weight: bold;
        }

        .form-group input,
        .form-group select {
            width: 100%;
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
        }

        .btn-toggle-fields {
            margin-bottom: 1rem;
        }

        .btn-toggle-fields .btn {
            margin-right: 0.5rem;
        }

        .hidden-fields {
            display: none;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Resume Submission Form</h2>
    <form action="/resumes/create" method="post" id="resumeForm">
        <div class="form-group">
            <label for="name">Name of the resume:</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="categoryName">Category:</label>
            <select id="categoryName" name="categoryName" class="form-select" required>
                <option value="Маркетинг">Маркетинг</option>
                <option value="Медицина">Медицина</option>
            </select>
        </div>

        <div class="form-group">
            <label for="salary">Expected Salary (USD):</label>
            <input type="number" id="salary" name="salary" class="form-control" required>
        </div>

        <div class="form-check mb-3">
            <input type="checkbox" name="isActive" value="true" class="form-check-input" id="isActiveCheckbox">
            <label for="isActiveCheckbox" class="form-check-label">Active:</label>
        </div>

        <div class="btn-toggle-fields mb-3">
            <button type="button" class="btn btn-primary toggle-education">Education</button>
            <button type="button" class="btn btn-primary toggle-experience">Experience</button>
            <button type="button" class="btn btn-primary toggle-contacts">Contacts</button>
        </div>

        <div class="hidden-fields education-fields">
            <div class="form-group">
                <label for="institution">Institution:</label>
                <input type="text" id="institution" name="institution" class="form-control">
            </div>
            <div class="form-group">
                <label for="program">Program:</label>
                <input type="text" id="program" name="program" class="form-control">
            </div>
            <div class="form-group">
                <label for="degree">Degree:</label>
                <input type="text" id="degree" name="degree" class="form-control">
            </div>
            <div class="form-group">
                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" class="form-control">
            </div>
            <div class="form-group">
                <label for="endDate">End Date:</label>
                <input type="date" id="endDate" name="endDate" class="form-control">
            </div>
        </div>

        <div class="hidden-fields experience-fields">
            <div class="form-group">
                <label for="position">Position:</label>
                <input type="text" id="position" name="position" class="form-control">
            </div>
            <div class="form-group">
                <label for="years">Years:</label>
                <input type="text" id="years" name="years" class="form-control">
            </div>
            <div class="form-group">
                <label for="companyName">Name of company:</label>
                <input type="text" id="companyName" name="companyName" class="form-control">
            </div>
            <div class="form-group">
                <label for="responsibilities">Responsibilities:</label>
                <input type="text" id="responsibilities" name="responsibilities" class="form-control">
            </div>
        </div>

        <div class="hidden-fields contact-fields">
            <div class="form-group">
                <div>
                    <label for="phone">Phone:</label>
                    <input type="tel" id="phone" name="phone">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email">
                    <label for="telegram">Telegram:</label>
                    <input type="text" id="telegram" name="telegram">
                    <label for="linkedin">LinkedIn:</label>
                    <input type="text" id="linkedin" name="linkedin">
                    <label for="facebook">Facebook:</label>
                    <input type="text" id="facebook" name="facebook">
                </div>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Submit</button>

    </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script>
    document.querySelector('.toggle-education').addEventListener('click', function () {
        toggleFields('education-fields');
    });

    document.querySelector('.toggle-experience').addEventListener('click', function () {
        toggleFields('experience-fields');
    });

    document.querySelector('.toggle-contacts').addEventListener('click', function () {
        toggleFields('contact-fields');
    });

    function toggleFields(className) {
        const fields = document.querySelectorAll('.' + className);
        fields.forEach(field => {
            if (field.classList.contains('hidden-fields')) {
                field.classList.remove('hidden-fields');
            } else {
                field.classList.add('hidden-fields');
            }
        });
    }
</script>
</body>
</html>
