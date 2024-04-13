INSERT INTO categories (name)
VALUES ('Производство'),
       ('Медицина'),
       ('Строительство'),
       ('Искусство и дизайн'),
       ('Путешествия и туризм'),
       ('Искусственный интеллект'),
       ('Экология и устойчивое развитие'),
       ('Кибербезопасность'),
       ('Финансовые технологии (FinTech)'),
       ('Электромобили и зеленая энергия');


INSERT INTO vacancies (name, description, categoryId, salary, experienceFrom, experienceTo, isActive, authorId,
                       createdDate, updateTime)
VALUES ('Data Scientist', 'Анализ данных и машинное обучение',
        (SELECT id FROM categories WHERE name = 'Искусственный интеллект'), 150000, 3, 6, TRUE,
        (SELECT id FROM users WHERE email = 'amazon@corp.com'), NOW(), NOW()),
       ('Sustainability Manager', 'Управление проектами по устойчивому развитию',
        (SELECT id FROM categories WHERE name = 'Экология и устойчивое развитие'), 120000, 4, 8, TRUE,
        (SELECT id FROM users WHERE email = 'amazon@corp.com'), NOW(), NOW()),
       ('Cybersecurity Analyst', 'Анализ и защита информации',
        (SELECT id FROM categories WHERE name = 'Кибербезопасность'), 130000, 2, 5, TRUE,
        (SELECT id FROM users WHERE email = 'netflix@corp.com'), NOW(), NOW()),
       ('FinTech Developer', 'Разработка финансовых технологий',
        (SELECT id FROM categories WHERE name = 'Финансовые технологии (FinTech)'), 140000, 3, 7, TRUE,
        (SELECT id FROM users WHERE email = 'apple@corp.com'), NOW(), NOW()),
       ('Green Energy Engineer', 'Разработка проектов по зеленой энергии',
        (SELECT id FROM categories WHERE name = 'Электромобили и зеленая энергия'), 160000, 4, 9, TRUE,
        (SELECT id FROM users WHERE email = 'amazon@corp.com'), NOW(), NOW()),
       ('Production Manager', 'Управление производством', (SELECT id FROM categories WHERE name = 'Производство'),
        130000, 3, 6, TRUE, (SELECT id FROM users WHERE email = 'netflix@corp.com'), NOW(), NOW()),
       ('Medical Researcher', 'Исследования в области медицины', (SELECT id FROM categories WHERE name = 'Медицина'),
        140000, 4, 8, TRUE, (SELECT id FROM users WHERE email = 'apple@corp.com'), NOW(), NOW()),
       ('Construction Engineer', 'Проектирование строительных объектов',
        (SELECT id FROM categories WHERE name = 'Строительство'), 120000, 2, 5, TRUE,
        (SELECT id FROM users WHERE email = 'amazon@corp.com'), NOW(), NOW()),
       ('Art Director', 'Управление искусством и дизайном',
        (SELECT id FROM categories WHERE name = 'Искусство и дизайн'), 140000, 4, 7, TRUE,
        (SELECT id FROM users WHERE email = 'apple@corp.com'), NOW(), NOW()),
       ('Travel Guide', 'Организация и проведение туристических мероприятий',
        (SELECT id FROM categories WHERE name = 'Путешествия и туризм'), 120000, 2, 4, TRUE,
        (SELECT id FROM users WHERE email = 'netflix@corp.com'), NOW(), NOW()),
       ('Financial Analyst', 'Анализ финансовой отчетности и прогнозирование',
        (SELECT id FROM categories WHERE name = 'Финансовые технологии (FinTech)'), 150000, 3, 6, TRUE,
        (SELECT id FROM users WHERE email = 'amazon@corp.com'), NOW(), NOW());


INSERT INTO resumes (name, userId, categoryId, salary, isActive, createdTime, updateTime)
VALUES ('Production Manager', (SELECT id FROM users WHERE email = 'ronaldo@example.com'),
        (SELECT id FROM categories WHERE name = 'Производство'), 105000, TRUE, NOW(), NOW()),
       ('Medical Assistant', (SELECT id FROM users WHERE email = 'alex.arnold@example.com'),
        (SELECT id FROM categories WHERE name = 'Медицина'), 80000, FALSE, NOW(), NOW()),
       ('Construction Engineer', (SELECT id FROM users WHERE email = 'habib@example.com'),
        (SELECT id FROM categories WHERE name = 'Строительство'), 90000, TRUE, NOW(), NOW()),
       ('Art Director', (SELECT id FROM users WHERE email = 'salah@example.com'),
        (SELECT id FROM categories WHERE name = 'Искусство и дизайн'), 110000, TRUE, NOW(), NOW()),
       ('Travel Consultant', (SELECT id FROM users WHERE email = 'ronaldo@example.com'),
        (SELECT id FROM categories WHERE name = 'Путешествия и туризм'), 95000, TRUE, NOW(), NOW())
        ,
       ('AI Developer', (SELECT id FROM users WHERE email = 'alex.arnold@example.com'),
        (SELECT id FROM categories WHERE name = 'Искусственный интеллект'), 120000, FALSE, NOW(), NOW())
        ,
       ('Environmental Engineer', (SELECT id FROM users WHERE email = 'habib@example.com'),
        (SELECT id FROM categories WHERE name = 'Экология и устойчивое развитие'), 100000, TRUE, NOW(), NOW())
        ,
       ('Cybersecurity Analyst', (SELECT id FROM users WHERE email = 'salah@example.com'),
        (SELECT id FROM categories WHERE name = 'Кибербезопасность'), 130000, TRUE, NOW(), NOW()),
       ('FinTech Specialist', (SELECT id FROM users WHERE email = 'ronaldo@example.com'),
        (SELECT id FROM categories WHERE name = 'Финансовые технологии (FinTech)'), 110000, TRUE, NOW(), NOW()),
       ('Green Energy Engineer', (SELECT id FROM users WHERE email = 'alex.arnold@example.com'),
        (SELECT id FROM categories WHERE name = 'Электромобили и зеленая энергия'), 105000, FALSE, NOW(), NOW()),
       ('Marketing Analyst', (SELECT id FROM users WHERE email = 'habib@example.com'),
        (SELECT id FROM categories WHERE name = 'Маркетинг'), 90000, TRUE, NOW(), NOW());


UPDATE users
SET enabled = TRUE,
    role_id = (SELECT id FROM roles WHERE role = 'EMPLOYER')
WHERE email IN ('apple@corp.com', 'netflix@corp.com', 'amazon@corp.com');

UPDATE users
SET enabled = TRUE,
    role_id = (SELECT id FROM roles WHERE role = 'APPLICANT')
WHERE email IN ('ronaldo@example.com', 'alex.arnold@example.com', 'habib@example.com', 'salah@example.com');