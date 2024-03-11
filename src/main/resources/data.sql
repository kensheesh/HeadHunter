CREATE TABLE IF NOT EXISTS users
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(50),
    surname      VARCHAR(50),
    age          INT,
    email        VARCHAR(100),
    password     VARCHAR(100),
    phone_number VARCHAR(20),
    avatar       TEXT,
    account_type VARCHAR(10)
);

-- Пользователи
INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
VALUES ('John', 'Doe', 30, 'john.doe@example.com', 'password123', '1234567890', 'ASDF', 'APPLICANT'),
       ('Jane', 'Smith', 45, 'jane.smith@example.com', 'password456', '0987654321', 'ASDF', 'EMPLOYER');