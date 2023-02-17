CREATE TABLE IF NOT EXISTS mjicx0btdgs4w3fi.product
(
--     id      SERIAL NOT NULL PRIMARY KEY,
    product_id   INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (product_id),
    date_added   DATETIME,
    model        VARCHAR(255),
    measure      VARCHAR(20),
    stock_status VARCHAR(20),
    price        DECIMAL(6, 2)
);