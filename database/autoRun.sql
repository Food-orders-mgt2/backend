        \c postgres

        DROP DATABASE paradish;

        CREATE DATABASE paradish;

        \c paradish

        CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

        CREATE TABLE IF NOT EXISTS "User" (
            id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
            username VARCHAR(250) NOT NULL,
            email VARCHAR(250) NOT NULL UNIQUE,
            password VARCHAR(250)
        );

          CREATE TABLE IF NOT EXISTS "Order" (
              id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
              date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
              shipping_cost DOUBLE PRECISION,
              delivery_date_time TIMESTAMP NOT NULL,
              delivery_address VARCHAR(250) NOT NULL,
              list_dish_id VARCHAR(250),
              id_User UUID REFERENCES "User"(id),
              pay_mode VARCHAR(250) CHECK (pay_mode= 'Mobile money' OR pay_mode = 'Bank' OR pay_mode = 'Cash'),
              total_price DOUBLE PRECISION
          );

        CREATE TABLE IF NOT EXISTS "Dish" (
            id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
            name VARCHAR(250) NOT NULL,
            description VARCHAR(250) NOT NULL,
            price DOUBLE PRECISION NOT NULL,
            image VARCHAR(250)
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
