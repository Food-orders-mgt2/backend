    \c postgres

    DROP DATABASE paradish;

    CREATE DATABASE paradish;

    \c paradish

    CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

    CREATE TABLE IF NOT EXISTS "User" (
        id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
        name VARCHAR(250) NOT NULL,
        first_name VARCHAR(250),
        email VARCHAR(250) NOT NULL UNIQUE,
        password VARCHAR(250),
        image TEXT,
        role VARCHAR(250) CHECK (role = 'customer' OR role = 'admin'),
        residence VARCHAR(250)
    );

    CREATE TABLE IF NOT EXISTS "Order" (
        id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
        date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        shipping_cost DOUBLE PRECISION,
        delivery_date_time TIMESTAMP NOT NULL,
        delivery_place VARCHAR(250) NOT NULL,
        id_User UUID REFERENCES "User"(id)
    );

    CREATE TABLE IF NOT EXISTS "Dish" (
        id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
        name VARCHAR(250) NOT NULL,
        price DOUBLE PRECISION NOT NULL
    );

    CREATE TABLE IF NOT EXISTS "Ingredient" (
        id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
        name VARCHAR(250) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS "included" (
        id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
        id_Order UUID REFERENCES "Order"(id),
        id_Dish UUID REFERENCES "Dish"(id)
    );

    CREATE TABLE IF NOT EXISTS "contains" (
        id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
        id_Dish UUID REFERENCES "Dish"(id),
        id_Ingredient UUID REFERENCES "Ingredient"(id)
    );
