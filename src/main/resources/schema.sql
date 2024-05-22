CREATE TABLE IF NOT EXISTS  user_account (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE (username),
    UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS exchange (
    id BIGINT NOT NULL AUTO_INCREMENT,
    origin_currency VARCHAR(255),
    origin_Amount DOUBLE NOT NULL,
    destination_currency VARCHAR(255) NOT NULL,
    destination_amount DOUBLE NOT NULL,
    user_id BIGINT NOT NULL,
    created_date DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_account(id)
);


