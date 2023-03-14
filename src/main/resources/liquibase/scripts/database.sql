-- liquibase formatted sql

--changeset shulga:1
CREATE TABLE Socks
(
    id          bigint primary key,
    color       TEXT,
    cotton_part integer

);
--changeset shulga:2
create sequence socks_seq
    increment by 50;

alter sequence socks_seq owner to storekeeper;
