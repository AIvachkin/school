--liquibase formatted sql

--changeset aivachkin:1
create index search_for_name_idx ON student (name);

--changeset aivachkin:2
create index search_for_name_and_color_idx ON faculty (name, color);
