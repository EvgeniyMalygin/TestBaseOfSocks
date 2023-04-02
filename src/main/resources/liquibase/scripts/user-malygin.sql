-- liquibase formatted sql
-- changeset malygin:1
CREATE TABLE socks
(
    socks_id    SERIAL PRIMARY KEY,
    color       varchar,
    cotton      integer,
    quantity    integer
);