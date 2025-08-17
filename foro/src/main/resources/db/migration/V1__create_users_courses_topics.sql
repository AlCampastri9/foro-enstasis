CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS courses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL UNIQUE,
    category VARCHAR(150) NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS topics (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    message TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    author_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    CONSTRAINT fk_topics_author FOREIGN KEY (author_id) REFERENCES users(id),
    CONSTRAINT fk_topics_course FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- Usuario admin por defecto (password: admin) -> BCrypt hash
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$5w/6xq4wG2o1Qq7tH6iZQe3oQf3hJ6Y0p7v4m2cHq3mZr5uS3a5y6', 'ADMIN')
ON DUPLICATE KEY UPDATE username = username;

-- Curso por defecto
INSERT INTO courses (name, category) VALUES ('General', 'DEFAULT')
ON DUPLICATE KEY UPDATE name = name;
