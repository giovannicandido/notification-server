CREATE TABLE notification
(
    id BIGINT PRIMARY KEY NOT NULL,
    message VARCHAR(140) NOT NULL,
    message_imageurl VARCHAR(255)
);
CREATE TABLE statistics
(
    id BIGINT PRIMARY KEY NOT NULL,
    date TIMESTAMP,
    type VARCHAR(255),
    user_id VARCHAR(40)
);
CREATE TABLE token
(
    token CHAR(36) PRIMARY KEY NOT NULL,
    application VARCHAR(50) NOT NULL UNIQUE,
    date_created TIMESTAMP
);