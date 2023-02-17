CREATE TABLE IF NOT EXISTS store_db.address
(
    id           serial,
    fk_client_id int
        constraint fk_address_client_id references store_db.client
            on update cascade on delete cascade,
    country      text,
    city         text,
    street       text,
    house        text
);
