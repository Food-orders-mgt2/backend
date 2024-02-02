\c postgres

CREATE DATABASE paradish;

\c paradish

CREATE TABLE IF NOT EXISTS "User" (
    id VARCHAR(250) PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    first_name VARCHAR(250),
    sex CHAR(1) CHECK (sex = 'M' OR sex='F'),
    email VARCHAR(250) NOT NULL UNIQUE,
    password VARCHAR(250),
    image TEXT,
    is_admin BOOL,
    residence VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS "Order" (
    id VARCHAR(250) PRIMARY KEY,
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    shipping_cost DOUBLE PRECISION,
    delivery_date_time TIMESTAMP NOT NULL,
    delivery_place VARCHAR(250) NOT NULL,
    id_User VARCHAR(250) REFERENCES "User"(id)
);

CREATE TABLE IF NOT EXISTS "Dish" (
    id VARCHAR(250) PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    price DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS "Ingredient" (
    id VARCHAR(250) PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS "included" (
    id VARCHAR(250) PRIMARY KEY,
    id_Order VARCHAR(250) REFERENCES "Order"(id),
    id_Dish VARCHAR(250) REFERENCES "Dish"(id)
);

CREATE TABLE IF NOT EXISTS "contains" (
    id VARCHAR(250) PRIMARY KEY,
    id_Dish VARCHAR(250) REFERENCES "Dish"(id),
    id_Ingredient VARCHAR(250) REFERENCES "Ingredient"(id)
);