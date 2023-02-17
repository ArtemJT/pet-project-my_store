CREATE TABLE IF NOT EXISTS store_db.client
(
    id serial not null primary key,
    "name" text,
    email text,
    phone text
);
