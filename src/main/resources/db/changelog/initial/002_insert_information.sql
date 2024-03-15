INSERT INTO users (name, surname, age, email, password, phoneNumber, avatar, accountType)
VALUES ('Джон', 'Доу', 28, 'john.doe@example.com', 'se3111curepasfs', '555-0101', '', 'APPLICANT'),
       ('Алис', 'Смит', 35, 'alice.smith@corp.com', 'securepass123', '555-0101', '', 'EMPLOYER'),
       ('Лиам', 'Вилсон', 34, 'asdf.wilson@example.com', 'lia21312mPass789', '555-0401', '', 'APPLICANT'),
       ('Робин', 'Робинсон', 28, 'asdf.robinson@corp.com', 'Se11cure456', '555-0402', '', 'EMPLOYER'),
       ('Джон', 'Джонсон', 31, 'asdf.johnson@example.com', 'av11a1234', '555-0403', '', 'APPLICANT'),
       ('Майк', 'Гарсиа', 27, 'asdf.garcia@corp.com', 'ib2ell3a35678', '555-0404', '', 'EMPLOYER'),
       ('Софья', null, 26, 'asdf.martinez@example.com', '3s3o32112iePass910', '555-0405', '', 'APPLICANT');

INSERT INTO categories (name)
VALUES ('Информационные технологии'),
       ('Маркетинг'),
       ('HrРекрутинг'),
       ('Бухгалтерия'),
       ('Образование');

INSERT INTO resumes (name, userId, categoryId, salary, isActive, createdTime, updateTime)
VALUES ('Engineer', (SELECT id FROM users WHERE email = 'john.doe@example.com'),
        (SELECT id FROM categories WHERE name = 'HrРекрутинг'), 95000, TRUE, NOW(), NOW()),
       ('DevOops Engineer', (SELECT id FROM users WHERE email = 'asdf.robinson@corp.com'),
        (SELECT id FROM categories WHERE name = 'Информационные технологии'), 140000, TRUE, NOW(), NOW()),
       ('Graphics Designer', (SELECT id FROM users WHERE email = 'alice.smith@corp.com'),
        (SELECT id FROM categories WHERE name = 'HrРекрутинг'), 75000, FALSE, NOW(), NOW()),
       ('Projects Coordinator', (SELECT id FROM users WHERE email = 'asdf.wilson@example.com'),
        (SELECT id FROM categories WHERE name = 'Образование'), 65000, TRUE, NOW(), NOW()),
       ('Financing Analyst', (SELECT id FROM users WHERE email = 'asdf.johnson@example.com'),
        (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 120000, TRUE, NOW(), NOW());

INSERT INTO resumes (name, userId, categoryId, salary, isActive, createdTime, updateTime)
VALUES ('Designer', 5, 3, 753000, FALSE, NOW(), NOW());


INSERT INTO vacancies (name, description, categoryId, salary, experienceFrom, experienceTo, isActive, authorId,
                       createdDate, updateTime)
VALUES ('Designer GUI', 'Дизайн интерфейсов и взаимодействия', (SELECT id FROM categories WHERE name = 'Бухгалтерия'),
        110000, 2, 4, TRUE, (SELECT id FROM users WHERE email = 'asdf.robinson@corp.com'), '2022-03-15', '2022-03-15'),
       ('Scrum Master', 'Управление командой разработки', (SELECT id FROM categories WHERE name = 'Образование'),
        140000, 3, 5, FALSE, (SELECT id FROM users WHERE email = 'asdf.johnson@example.com'), NOW(), NOW()),
       ('Accountant', 'Ведение бухгалтерского учета',
        (SELECT id FROM categories WHERE name = 'Информационные технологии'), 90000, 1, 3, TRUE,
        (SELECT id FROM users WHERE email = 'asdf.wilson@example.com'), NOW(), NOW()),
       ('Employing recruiter', 'Поиск и подбор персонала', (SELECT id FROM categories WHERE name = 'Образование'),
        85000, 2, 4, TRUE, (SELECT id FROM users WHERE email = 'alice.smith@corp.com'), NOW(), '2030-01-15'),
       ('Teacher', 'Преподавание в области IT', (SELECT id FROM categories WHERE name = 'HrРекрутинг'), 95000, 3, 7,
        false, (SELECT id FROM users WHERE email = 'john.doe@example.com'), NOW(), '2010-01-15');


INSERT INTO respondedApplicants (resumeId, vacancyId, confirmation)
VALUES ((SELECT resumes.id
         FROM resumes
                  JOIN users ON resumes.userId = users.id
         WHERE resumes.name = 'Engineer'
           AND users.email = 'john.doe@example.com'),
        (SELECT id FROM vacancies WHERE name = 'Employing recruiter'), TRUE),

       ((SELECT resumes.id
         FROM resumes
                  JOIN users ON resumes.userId = users.id
         WHERE resumes.name = 'DevOops Engineer'
           AND users.email = 'asdf.robinson@corp.com'),
        (SELECT id FROM vacancies WHERE name = 'Designer GUI'), FALSE),

       ((SELECT resumes.id
         FROM resumes
                  JOIN users ON resumes.userId = users.id
         WHERE resumes.name = 'Financing Analyst'
           AND users.email = 'asdf.johnson@example.com'),
        (SELECT id FROM vacancies WHERE name = 'Employing recruiter'), TRUE),

       ((SELECT resumes.id
         FROM resumes
                  JOIN users ON resumes.userId = users.id
         WHERE resumes.name = 'Projects Coordinator'
           AND users.email = 'asdf.wilson@example.com'),
        (SELECT id FROM vacancies WHERE name = 'Employing recruiter'), FALSE),

       ((SELECT resumes.id
         FROM resumes
                  JOIN users ON resumes.userId = users.id
         WHERE resumes.name = 'Projects Coordinator'
           AND users.email = 'asdf.wilson@example.com'),
        (SELECT id FROM vacancies WHERE name = 'Teacher'), TRUE);


INSERT INTO contactTypes (type)
VALUES ('Telegram'),
       ('LinkedIn'),
       ('Facebook'),
       ('Email'),
       ('PhoneNumber');

INSERT INTO contactsInfo (ContactTypeId, resumeId, content)
VALUES ((SELECT id FROM contactTypes WHERE type = 'Telegram'), (SELECT id
                                                                FROM resumes
                                                                WHERE name = 'Engineer'
                                                                  AND userId = (SELECT id FROM users WHERE email = 'john.doe@example.com')),
        'john.doe@example.com'),
       ((SELECT id FROM contactTypes WHERE type = 'LinkedIn'), (SELECT id
                                                                FROM resumes
                                                                WHERE name = 'Engineer'
                                                                  AND userId = (SELECT id FROM users WHERE email = 'john.doe@example.com')),
        '555-0101'),
       ((SELECT id FROM contactTypes WHERE type = 'Facebook'), (SELECT id
                                                                FROM resumes
                                                                WHERE name = 'Engineer'
                                                                  AND userId = (SELECT id FROM users WHERE email = 'john.doe@example.com')),
        'john.doe.linkedin.com'),
       ((SELECT id FROM contactTypes WHERE type = 'Telegram'), (SELECT id
                                                                FROM resumes
                                                                WHERE name = 'DevOops Engineer'
                                                                  AND userId = (SELECT id FROM users WHERE email = 'asdf.robinson@corp.com')),
        'alice.smith@corp.com'),
       ((SELECT id FROM contactTypes WHERE type = 'LinkedIn'), (SELECT id
                                                                FROM resumes
                                                                WHERE name = 'DevOops Engineer'
                                                                  AND userId = (SELECT id FROM users WHERE email = 'asdf.robinson@corp.com')),
        '555-0102');


INSERT INTO educationInfo (resumeId, institution, program, startDate, endDate, degree)
VALUES ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'john.doe@example.com')), 'Harvard',
        'Computer Scientist', '2015-09-01', '2019-06-30', 'B'),
       ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'alice.smith@corp.com')),
        'Masachusitch', 'Graphic Designer', '2014-09-01', '2018-06-30', 'A'),
       ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'asdf.wilson@example.com')),
        'School', 'Project Management', '2016-09-01', '2020-06-30', 'C');


INSERT INTO workExperienceInfo (resumeId, years, companyName, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'john.doe@example.com')), 3,
        'Tech Corp', 'Junior Developer', '...'),
       ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'alice.smith@corp.com')), 5,
        'Design Studio', 'Senior Graphic Designer', '...'),
       ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'asdf.wilson@example.com')), 2,
        'Project Inc.', 'HR', '...');


INSERT INTO messages (respondedApplicantsId, content, timestamp)
VALUES (1, 'Thank you', NOW()),
       (2, 'Bye bye', NOW()),
       (3, 'Good evening', NOW());