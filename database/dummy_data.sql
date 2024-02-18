-- Insertions pour la table "User"
INSERT INTO "User" ( username, email, password)
VALUES
( 'john_doe', 'john.doe@email.com', 'hashed_password_1'),
( 'alice_smith', 'alice.smith@email.com', 'hashed_password_2'),
( 'bob_brown', 'bob.brown@email.com', 'hashed_password_3'),
( 'eva_white', 'eva.white@email.com', 'hashed_password_4'),
( 'charlie_johnson', 'charlie.johnson@email.com', 'hashed_password_5');

-- Insertions pour la table "Order"
INSERT INTO "Order" ( date_time, shipping_cost, delivery_date_time, delivery_address, list_dish_id, id_User, pay_mode, total_price)
VALUES
( CURRENT_TIMESTAMP, 10.50, '2024-02-02 12:00:00', 'Address 1', '1,2', (SELECT id FROM "User" WHERE username = 'john_doe'), 'Mobile money', 10.50),
( CURRENT_TIMESTAMP, 8.20, '2024-02-03 14:30:00', 'Address 2', '3,4', (SELECT id FROM "User" WHERE username = 'alice_smith'), 'Mobile money', 8.20),
( CURRENT_TIMESTAMP, 15.75, '2024-02-04 18:45:00', 'Address 3', '5', (SELECT id FROM "User" WHERE username = 'bob_brown'), 'Bank', 15.75),
( CURRENT_TIMESTAMP, 12.00, '2024-02-05 11:15:00', 'Address 4', '6', (SELECT id FROM "User" WHERE username = 'eva_white'), 'Cash', 12.00),
( CURRENT_TIMESTAMP, 7.80, '2024-02-06 16:00:00', 'Address 5', '7', (SELECT id FROM "User" WHERE username = 'charlie_johnson'), 'Cash', 7.80);

-- Insertions pour la table "Dish"
INSERT INTO "Dish" (name, description, price, image)
VALUES
('Pasta Carbonara', 'Pates cremeuses au bacon et parmesan', 12.99, '/images/Pasta carbonara.jpg'),
('Chicken Alfredo', 'Pates cremeuses au poulet et sauce Alfredo', 15.50, '/images/Chicken Alfredo.jpg'),
('Margherita Pizza', 'Pizza classique avec sauce tomate et fromage mozzarella', 9.75, '/images/Margherita Pizza.jpg'),
('Asparagus and Beef Stir-Fry', 'Bœuf sauté aux legumes et sauce soja', 14.25, '/images/Asparagus and Beef Stir-Fry.jpg'),
('Vegetarian Lasagna', 'Pates etagees aux legumes et au fromage', 11.99, '/images/Vegetarian Lasagna.jpg');


-- Insertions pour la table "Ingredient"
INSERT INTO "Ingredient" ( name)
VALUES
( 'Spaghetti'),
( 'Chicken Breast'),
( 'Tomato Sauce'),
( 'Beef Strips'),
( 'Lasagna Noodles');

    -- Insertions pour la table "included"
    INSERT INTO "included" ( id_Order, id_Dish)
    VALUES
    ( (SELECT id FROM "Order" WHERE shipping_cost = 10.50), (SELECT id FROM "Dish" WHERE name = 'Pasta Carbonara')),
    ( (SELECT id FROM "Order" WHERE shipping_cost = 8.20), (SELECT id FROM "Dish" WHERE name = 'Chicken Alfredo')),
    ( (SELECT id FROM "Order" WHERE shipping_cost = 15.75), (SELECT id FROM "Dish" WHERE name = 'Margherita Pizza')),
    ( (SELECT id FROM "Order" WHERE shipping_cost = 12.00), (SELECT id FROM "Dish" WHERE name = 'Asparagus and Beef Stir-Fry')),
    ( (SELECT id FROM "Order" WHERE shipping_cost = 7.80), (SELECT id FROM "Dish" WHERE name = 'Vegetarian Lasagna'));

    -- Insertions pour la table "contains"
    INSERT INTO "contains" ( id_Dish, id_Ingredient)
    VALUES
    ( (SELECT id FROM "Dish" WHERE name = 'Pasta Carbonara'), (SELECT id FROM "Ingredient" WHERE name = 'Spaghetti')),
    ( (SELECT id FROM "Dish" WHERE name = 'Chicken Alfredo'), (SELECT id FROM "Ingredient" WHERE name = 'Chicken Breast')),
    ( (SELECT id FROM "Dish" WHERE name = 'Margherita Pizza'), (SELECT id FROM "Ingredient" WHERE name = 'Tomato Sauce')),
    ( (SELECT id FROM "Dish" WHERE name = 'Beef Stir-Fry'), (SELECT id FROM "Ingredient" WHERE name = 'Beef Strips')),
    ( (SELECT id FROM "Dish" WHERE name = 'Vegetarian Lasagna'), (SELECT id FROM "Ingredient" WHERE name = 'Lasagna Noodles'));
