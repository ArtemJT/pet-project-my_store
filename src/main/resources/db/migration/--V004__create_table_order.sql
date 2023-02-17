CREATE TABLE IF NOT EXISTS store_db."order"
(
    id serial not null primary key,
    fk_client_id int
    constraint fk_order_client_id references store_db.client
    on update cascade on delete cascade
);
