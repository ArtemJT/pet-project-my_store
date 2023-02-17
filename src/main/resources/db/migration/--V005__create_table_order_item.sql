CREATE TABLE IF NOT EXISTS store_db.order_item
(
    id serial,
    count int,
    fk_order_id int constraint fk_order_item_order_id references store_db."order"
        on update cascade on delete cascade,
    fk_product_id int constraint fk_order_item_product_id references store_db.product
        on update cascade on delete cascade
);
