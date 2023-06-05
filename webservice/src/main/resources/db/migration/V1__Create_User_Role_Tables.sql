CREATE TABLE role (
    id SERIAL,
    name VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE usr (
    id SERIAL,
    login VARCHAR(50) NOT NULL UNIQUE ,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE ,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    birthday TIMESTAMP,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES role(id)
)