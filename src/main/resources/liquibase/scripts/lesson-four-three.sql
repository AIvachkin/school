- liquibase formatted sql

- changeset aivachkin:1
CREATE INDEX search_for_name_idx ON student (name);

- changeset aivachkin:2
CREATE INDEX search_for_name_and_color_idx ON faculty (name, color);
