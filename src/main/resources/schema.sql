CREATE TABLE app_users
(
    user_id   SERIAL PRIMARY KEY NOT NULL,
    full_name VARCHAR(50)        NOT NULL,
    email     VARCHAR(50)        NOT NULL,
    password  VARCHAR(255)       NOT NULL
);

CREATE TABLE books
(
    book_id     SERIAL PRIMARY KEY NOT NULL,
    title       VARCHAR(255)       NOT NULL,
    description VARCHAR(255)       NOT NULL,
    author      VARCHAR(100)       NOT NULL,
    user_id     INT                NOT NULL,
    CONSTRAINT app_user_fk FOREIGN KEY (user_id) REFERENCES app_users (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO books (title, description, author, user_id)
VALUES ('To Kill a Mockingbird', 'A story of racial injustice and the loss of innocence', 'Harper Lee', 1);

INSERT INTO books (title, description, author, user_id)
VALUES ('1984', 'A dystopian novel about totalitarianism', 'George Orwell', 1);

INSERT INTO books (title, description, author, user_id)
VALUES ('Pride and Prejudice', 'A classic romance about love and social class', 'Jane Austen', 1);

INSERT INTO books (title, description, author, user_id)
VALUES ('The Great Gatsby', 'A tale of wealth, love, and the American Dream', 'F. Scott Fitzgerald', 1);

INSERT INTO books (title, description, author, user_id)
VALUES ('The Catcher in the Rye', 'A coming-of-age story of teenage rebellion', 'J.D. Salinger', 1);

INSERT INTO books (title, description, author, user_id)
VALUES ('Lord of the Rings', 'An epic fantasy adventure', 'J.R.R. Tolkien', 1);

INSERT INTO books (title, description, author, user_id)
VALUES ('Harry Potter and the Sorcerer''s Stone', 'A magical journey begins', 'J.K. Rowling', 1);

INSERT INTO books (title, description, author, user_id)
VALUES ('The Hobbit', 'A prelude to an epic adventure', 'J.R.R. Tolkien', 1);

INSERT INTO books (title, description, author, user_id)
VALUES ('Brave New World', 'A futuristic vision of a controlled society', 'Aldous Huxley', 1);

INSERT INTO books (title, description, author, user_id)
VALUES ('The Alchemist', 'A journey of self-discovery and destiny', 'Paulo Coelho', 1);