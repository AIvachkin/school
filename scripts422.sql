CREATE TABLE human
(
    id            SERIAL,
    name          TEXT PRIMARY KEY,
    age           INTEGER CHECK (age >= 18),
    driverLicense BOOLEAN,
    auto_id       INTEGER REFERENCES auto (id)
);

CREATE TABLE auto
(
    id    SERIAL PRIMARY KEY,
    brand TEXT NOT NULL,
    model TEXT NOT NULL UNIQUE,
    cost  NUMERIC(9, 2)
);

