CREATE TABLE book
(
    id          SERIAL,
    name        VARCHAR(50) NOT NULL UNIQUE,
    author      VARCHAR(50),
    genre       VARCHAR(50) NOT NULL,
    description TEXT,
    grade       int,
    release_date    DATE NOT NULL,
    image_link VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE SEQUENCE book_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 1000
    NO CYCLE
    CACHE 10;