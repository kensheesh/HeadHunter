INSERT INTO users (name, surname, age, email, password, phoneNumber, avatar, accountType)
VALUES ('Джон', 'Доу', 28, 'john.doe@example.com', 'se3111curepasfs', '555-0101', '', 'APPLICANT'),
       ('Алис', 'Смит', 35, 'alice.smith@corp.com', 'securepass123', '555-0102', '', 'EMPLOYER'),
       ('Лиам', 'Вилсон', 34, 'asdf.wilson@example.com', 'lia21312mPass789', '555-0401', '', 'APPLICANT'),
       ('Робин', 'Робинсон', 28, 'asdf.robinson@corp.com', 'Se11cure456', '555-0402', '', 'EMPLOYER'),
       ('Джон', 'Джонсон', 31, 'asdf.johnson@example.com', 'av11a1234', '555-0403', '', 'APPLICANT'),
       ('Майк', 'Гарсиа', 27, 'asdf.garcia@corp.com', 'ib2ell3a35678', '555-0404', '', 'EMPLOYER'),
       ('Софья', 'Мартинез', 26, 'asdf.martinez@example.com', '3s3o32112iePass910', '555-0405', '', 'APPLICANT');

INSERT INTO categories (name)
VALUES ('Информационные технологии'),
       ('Маркетинг'),
       ('HrРекрутинг'),
       ('Бухгалтерия'),
       ('Образование');

INSERT INTO resumes (name, userId, categoryId, salary, isActive, createdTime, updateTime)
VALUES ('Engineer', 1, 3, 95000, TRUE, NOW(), NOW()),
       ('DevOops Engineer', 4, 1, 140000, TRUE, NOW(), NOW()),
       ('Graphics Designer', 2, 3, 75000, TRUE, NOW(), NOW()),
       ('Projects Coordinator', 3, 5, 65000, TRUE, NOW(), NOW()),
       ('Financing Analyst', 5, 4, 120000, TRUE, NOW(), NOW());

INSERT INTO vacancies (name, description, categoryId, salary, experienceFrom, experienceTo, isActive, authorId,
                       createdDate, updateTime)
VALUES ('Designer GUI', 'Дизайн интерфейсов и взаимодействия', 4, 110000, 2, 4, TRUE, 4, NOW(), NOW()),
       ('Scrum Master', 'Управление командой разработки', 5, 140000, 3, 5, TRUE, 5, NOW(), NOW()),
       ('Accountant', 'Ведение бухгалтерского учета', 1, 90000, 1, 3, TRUE, 3, NOW(), NOW()),
       ('Employing recruiter', 'Поиск и подбор персонала', 5, 85000, 2, 4, TRUE, 2, NOW(), NOW()),
       ('Teacher', 'Преподавание в области IT', 3, 95000, 3, 7, TRUE, 1, NOW(), NOW());

INSERT INTO respondedApplicants (resumeId, vacancyId, confirmation)
VALUES (1, 4, TRUE),
       (2, 1, FALSE),
       (5, 4, TRUE),
       (4, 4, FALSE),
       (4, 5, TRUE);

INSERT INTO contactTypes (type)
VALUES ('Telegram'),
       ('LinkedIn'),
       ('Facebook'),
       ('Email'),
       ('PhoneNumber');

INSERT INTO contactsInfo (ContactTypeId, resumeId, content)
VALUES (1, 1, 'john.doe@example.com'),
       (2, 1, '555-0101'),
       (3, 1, 'john.doe.linkedin.com'),
       (1, 2, 'alice.smith@corp.com'),
       (2, 2, '555-0102');

INSERT INTO educationInfo (resumeId, institution, program, startDate, endDate, degree)
VALUES (1, 'Harvard', 'Computer Scientist', '2015-09-01', '2019-06-30', 'B'),
       (2, 'Masachusitch', 'Graphic Designer', '2014-09-01', '2018-06-30', 'A'),
       (3, 'School', 'Project Management', '2016-09-01', '2020-06-30', 'C');

INSERT INTO workExperienceInfo (resumeId, years, companyName, position, responsibilities)
VALUES (1, 3, 'Tech Corp', 'Junior Developer', '..'),
       (2, 5, 'Design Studio', 'Senior Graphic Designer', '...'),
       (3, 2, 'Project Inc.', 'HR', '...');

INSERT INTO messages (respondedApplicantsId, content, timestamp)
VALUES (1, 'Thank you', NOW()),
       (2, 'Bye bye', NOW()),
       (3, 'Good evening', NOW());