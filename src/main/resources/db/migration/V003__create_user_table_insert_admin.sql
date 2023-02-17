CREATE TABLE IF NOT EXISTS mjicx0btdgs4w3fi.user
(
    user_id    serial primary key,
    date_added DATETIME,
    name       varchar(120) not null,
    password   varchar(120) not null,
    email      varchar(120) not null,
    ip         varchar(40),
    role       varchar(20)  not null
);

INSERT INTO mjicx0btdgs4w3fi.user (date_added, name, password, email, ip, role)
VALUES (
        '2023-01-01 01:01:01',
        'admin',
        '$2a$10$bIUD5UskhHw/XoNz28sLKeJqsdkQuv5Lo00NaDnwvXhmLzUXA3iwq',
        'mystoreadmin@proton.me',
        '31.202.67.1',
        'ADMIN'
        );
-- login: admin     password: admin
