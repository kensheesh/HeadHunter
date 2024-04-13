INSERT INTO users (name, surname, age, email, password, phoneNumber, avatar, accountType)
VALUES ('Криштиану', 'Роналду', 28, 'ronaldo@example.com', '$2a$10$0NXLvLR38w3e1dUQy/TSHO.JaxVQxNPTNHg1RmacauJczIbpOQA9u', '555-0101', 'base_avatar.png', 'APPLICANT'),
       ('Apple', null, 35, 'apple@corp.com', '$2a$10$0NXLvLR38w3e1dUQy/TSHO.JaxVQxNPTNHg1RmacauJczIbpOQA9u', '555-0102', 'base_avatar.png', 'EMPLOYER'),
       ('Александр', 'Арнольд', 34, 'alex.arnold@example.com', '$2a$10$0NXLvLR38w3e1dUQy/TSHO.JaxVQxNPTNHg1RmacauJczIbpOQA9u', '555-0401', 'base_avatar.png', 'APPLICANT'),
       ('Netflix', null, 28, 'netflix@corp.com', '$2a$10$0NXLvLR38w3e1dUQy/TSHO.JaxVQxNPTNHg1RmacauJczIbpOQA9u', '555-0402', 'base_avatar.png', 'EMPLOYER'),
       ('Хабиб', 'Нурмагамедов', 31, 'habib@example.com', '$2a$10$0NXLvLR38w3e1dUQy/TSHO.JaxVQxNPTNHg1RmacauJczIbpOQA9u', '555-0403', 'base_avatar.png', 'APPLICANT'),
       ('Amazon', null, 27, 'amazon@corp.com', '$2a$10$0NXLvLR38w3e1dUQy/TSHO.JaxVQxNPTNHg1RmacauJczIbpOQA9u', '555-0404', 'base_avatar.png', 'EMPLOYER'),
       ('Мухаммед', 'Салах', 26, 'salah@example.com', '$2a$10$0NXLvLR38w3e1dUQy/TSHO.JaxVQxNPTNHg1RmacauJczIbpOQA9u', '555-0405', 'base_avatar.png', 'APPLICANT');


INSERT INTO categories (name)
VALUES ('Информационные технологии'),
       ('Маркетинг'),
       ('HrРекрутинг'),
       ('Бухгалтерия'),
       ('Образование');

INSERT INTO resumes (name, userId, categoryId, salary, isActive, createdTime, updateTime)
VALUES ('Engineer', (SELECT id FROM users WHERE email = 'ronaldo@example.com'),
        (SELECT id FROM categories WHERE name = 'HrРекрутинг'), 95000, TRUE, NOW(), NOW()),
       ('Graphics Designer', (SELECT id FROM users WHERE email = 'alex.arnold@example.com'),
        (SELECT id FROM categories WHERE name = 'Маркетинг'), 75000, FALSE, NOW(), NOW()),
       ('Projects Coordinator', (SELECT id FROM users WHERE email = 'habib@example.com'),
        (SELECT id FROM categories WHERE name = 'Образование'), 65000, TRUE, NOW(), NOW()),
       ('Financing Analyst', (SELECT id FROM users WHERE email = 'salah@example.com'),
        (SELECT id FROM categories WHERE name = 'Бухгалтерия'), 120000, TRUE, NOW(), NOW());

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
                                                                  AND userId = (SELECT id FROM users WHERE email = 'ronaldo@example.com')),
        'telega112233'),
       ((SELECT id FROM contactTypes WHERE type = 'LinkedIn'), (SELECT id
                                                                FROM resumes
                                                                WHERE name = 'Engineer'
                                                                  AND userId = (SELECT id FROM users WHERE email = 'ronaldo@example.com')),
        'linkedi42423'),
       ((SELECT id FROM contactTypes WHERE type = 'Facebook'), (SELECT id
                                                                FROM resumes
                                                                WHERE name = 'Graphics Designer'
                                                                  AND userId = (SELECT id FROM users WHERE email = 'alex.arnold@example.com')),
        'faceeboooook'),
       ((SELECT id FROM contactTypes WHERE type = 'Telegram'), (SELECT id
                                                                FROM resumes
                                                                WHERE name = 'Graphics Designer'
                                                                  AND userId = (SELECT id FROM users WHERE email = 'alex.arnold@example.com')),
        'telega000011'),
       ((SELECT id FROM contactTypes WHERE type = 'LinkedIn'), (SELECT id
                                                                FROM resumes
                                                                WHERE name = 'Financing Analyst'
                                                                  AND userId = (SELECT id FROM users WHERE email = 'salah@example.com')),
        'linkaaa');


INSERT INTO educationInfo (resumeId, institution, program, startDate, endDate, degree)
VALUES ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'alex.arnold@example.com')),
        'Harvard',
        'Computer Scientist', '2015-09-01', '2019-06-30', 'B'),
       ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'salah@example.com')),
        'Masachusitch', 'Graphic Designer', '2014-09-01', '2018-06-30', 'A'),
       ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'habib@example.com')),
        'School', 'Project Management', '2016-09-01', '2020-06-30', 'C');


INSERT INTO workExperienceInfo (resumeId, years, companyName, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'habib@example.com')), 3,
        'Tech Corp', 'Junior Developer', '...'),
       ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'ronaldo@example.com')), 5,
        'Design Studio', 'Senior Graphic Designer', '...'),
       ((SELECT id FROM resumes WHERE userId = (SELECT id FROM users WHERE email = 'alex.arnold@example.com')), 2,
        'Project Inc.', 'HR', '...');

INSERT INTO vacancies (name, description, categoryId, salary, experienceFrom, experienceTo, isActive, authorId,
                       createdDate, updateTime)
VALUES ('Designer GUI', 'Дизайн интерфейсов и взаимодействия', (SELECT id FROM categories WHERE name = 'Бухгалтерия'),
        110000, 2, 4, TRUE, (SELECT id FROM users WHERE email = 'netflix@corp.com'), '2022-03-15', '2022-03-15'),
       ('Scrum Master', 'Управление командой разработки', (SELECT id FROM categories WHERE name = 'Образование'),
        140000, 3, 5, FALSE, (SELECT id FROM users WHERE email = 'netflix@corp.com'), NOW(), NOW()),
       ('Accountant', 'Ведение бухгалтерского учета',
        (SELECT id FROM categories WHERE name = 'Информационные технологии'), 90000, 1, 3, TRUE,
        (SELECT id FROM users WHERE email = 'apple@corp.com'), NOW(), NOW()),
       ('Employing recruiter', 'Поиск и подбор персонала', (SELECT id FROM categories WHERE name = 'Образование'),
        85000, 2, 4, TRUE, (SELECT id FROM users WHERE email = 'apple@corp.com'), NOW(), '2030-01-15'),
       ('Teacher', 'Преподавание в области IT', (SELECT id FROM categories WHERE name = 'HrРекрутинг'), 95000, 3, 7,
        false, (SELECT id FROM users WHERE email = 'apple@corp.com'), NOW(), '2010-01-15');


INSERT INTO respondedApplicants (resumeId, vacancyId, confirmation)
VALUES ((SELECT resumes.id
         FROM resumes
                  JOIN users ON resumes.userId = users.id
         WHERE resumes.name = 'Financing Analyst'
           AND users.email = 'salah@example.com'),
        (SELECT id FROM vacancies WHERE name = 'Accountant'), FALSE);
