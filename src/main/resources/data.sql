CREATE TABLE users
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    surname     VARCHAR(255) NOT NULL,
    age         INT          NOT NULL,
    email       VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255),
    avatar      VARCHAR(255),
    accountType VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS categories
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    parentId INT,
    FOREIGN KEY (parentId) REFERENCES categories (id)
);


CREATE TABLE IF NOT EXISTS vacancies
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    description    TEXT         NOT NULL,
    categoryId     INT          NOT NULL,
    salary         DOUBLE       NOT NULL,
    experienceFrom INT          NOT NULL,
    experienceTo   INT          NOT NULL,
    isActive       BOOLEAN      NOT NULL,
    authorId       INT          NOT NULL,
    createdDate    DATETIME     NOT NULL,
    updateTime     DATETIME     NOT NULL,
    FOREIGN KEY (categoryId) REFERENCES categories (id),
    FOREIGN KEY (authorId) REFERENCES users (id)
);


CREATE TABLE IF NOT EXISTS resumes
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    userId      INT          NOT NULL,
    name        VARCHAR(255) NOT NULL,
    categoryId  INT          NOT NULL,
    salary      INT          NOT NULL,
    isActive    BOOLEAN      NOT NULL,
    createdTime DATETIME     NOT NULL,
    updateTime  DATETIME     NOT NULL,
    FOREIGN KEY (categoryId) REFERENCES categories (id),
    FOREIGN KEY (userId) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS respondedApplicants
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    resumeId     INT     NOT NULL,
    vacancyId    INT     NOT NULL,
    confirmation BOOLEAN NOT NULL,
    FOREIGN KEY (resumeId) REFERENCES resumes (id),
    FOREIGN KEY (vacancyId) REFERENCES vacancies (id)
);


INSERT INTO users (name, surname, age, email, password, phoneNumber, avatar, accountType)
VALUES ('John', 'Doe', 28, 'john.doe@example.com', 'securepass', '555-0101', '', 'APPLICANT');

INSERT INTO users (name, surname, age, email, password, phoneNumber, avatar, accountType)
VALUES ('Alice', 'Smith', 35, 'alice.smith@corp.com', 'securepass123', '555-0102', '', 'EMPLOYER');

INSERT INTO categories (name)
VALUES ('Информационные технологии'),
       ('Маркетинг');

INSERT INTO vacancies (name, description, categoryId, salary, experienceFrom, experienceTo, isActive, authorId,
                       createdDate, updateTime)
VALUES ('Senior Java Developer', 'Разработка высоконагруженных систем', 1, 150000, 3, 5, TRUE, 1, NOW(), NOW()),
       ('Менеджер по продажам', 'Работа с клиентской базой, увеличение продаж', 2, 70000, 1, 3, TRUE, 2, NOW(), NOW()),
       ('Менеджер по покупкам', 'Работа по покупке за хорошую цену', 2, 340000, 4, 7, TRUE, 1, NOW(), NOW());

INSERT INTO resumes (name, userId, categoryId, salary, isActive, createdTime, updateTime)
VALUES ('Разработчик ПО', 1, 1, 100000, TRUE, NOW(), NOW()),
       ('Тестировщик', 2, 2, 80000, TRUE, NOW(), NOW()),
       ('Видеограф', 2, 2, 801000, TRUE, NOW(), NOW());

INSERT INTO respondedApplicants (resumeId, vacancyId, confirmation)
VALUES (1, 1, FALSE),
       (2, 2, FALSE);