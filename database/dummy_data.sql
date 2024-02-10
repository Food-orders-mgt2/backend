-- Insertions pour la table "User"
INSERT INTO "User" (name, first_name,  email, password, image, role, residence) VALUES
('Doe', 'John', 'john.doe@email.com', 'hashed_password_1', '/images/john.jpg', 'admin', 'City A'),
('Smith', 'Alice', 'alice.smith@email.com', 'hashed_password_2', '/images/alice.jpg','admin', 'City B'),
('Brown', 'Bob', 'bob.brown@email.com', 'hashed_password_3', '/images/bob.jpg','customer','City C'),
('White', 'Eva', 'eva.white@email.com', 'hashed_password_4', '/images/eva.jpg','customer',  'City A'),
('Johnson', 'Charlie', 'charlie.johnson@email.com', 'hashed_password_5', '/images/charlie.jpg','customer', 'City B');

-- Insertions pour la table "Order"
INSERT INTO "Order" (date_time, shipping_cost, delivery_date_time, delivery_place, id_User, pay_mode) VALUES
('2024-02-02 12:00:00', 10.50, '2024-02-02 12:00:00', 'Address 1', (SELECT id FROM "User" WHERE name = 'Doe'), 'Mobile money'),
('2024-02-03 14:30:00', 8.20, '2024-02-03 14:30:00', 'Address 2', (SELECT id FROM "User" WHERE name = 'Smith'), 'Mobile money'),
('2024-02-04 18:45:00', 15.75, '2024-02-04 18:45:00', 'Address 3', (SELECT id FROM "User" WHERE name = 'Brown'), 'Bank'),
('2024-02-05 11:15:00', 12.00, '2024-02-05 11:15:00', 'Address 4', (SELECT id FROM "User" WHERE name = 'White'), 'Cash'),
('2024-02-06 16:00:00', 7.80, '2024-02-06 16:00:00', 'Address 5', (SELECT id FROM "User" WHERE name = 'Johnson'), 'Cash');

-- Insertions pour la table "Dish"
INSERT INTO "Dish" (name, price,image) VALUES
('Pasta Carbonara', 12.99,'dnflkl/ildn.img'),
('Chicken Alfredo', 15.50,'dnflkl/ildn.img'),
('Margherita Pizza', 9.75,'dnflkl/ildn.img'),
('Beef Stir-Fry', 14.25,'dnflkl/ildn.img'),
('Vegetarian Lasagna', 11.99,'dnflkl/ildn.img');

-- Insertions pour la table "Ingredient"
INSERT INTO "Ingredient" (name) VALUES
('Spaghetti'),
('Chicken Breast'),
('Tomato Sauce'),
('Beef Strips'),
('Lasagna Noodles');

-- Insertions pour la table "included"
INSERT INTO "included" (id_Order, id_Dish) VALUES
((SELECT id FROM "Order" WHERE shipping_cost = 10.50), (SELECT id FROM "Dish" WHERE name = 'Pasta Carbonara')),
((SELECT id FROM "Order" WHERE shipping_cost = 8.20), (SELECT id FROM "Dish" WHERE name = 'Chicken Alfredo')),
((SELECT id FROM "Order" WHERE shipping_cost = 15.75), (SELECT id FROM "Dish" WHERE name = 'Margherita Pizza')),
((SELECT id FROM "Order" WHERE shipping_cost = 12.00), (SELECT id FROM "Dish" WHERE name = 'Beef Stir-Fry')),
((SELECT id FROM "Order" WHERE shipping_cost = 7.80), (SELECT id FROM "Dish" WHERE name = 'Vegetarian Lasagna'));


-- Insertions pour la table "contains"
INSERT INTO "contains" (id_Dish, id_Ingredient) VALUES
((SELECT id FROM "Dish" WHERE name = 'Pasta Carbonara'), (SELECT id FROM "Ingredient" WHERE name = 'Spaghetti')),
((SELECT id FROM "Dish" WHERE name = 'Chicken Alfredo'), (SELECT id FROM "Ingredient" WHERE name = 'Chicken Breast')),
((SELECT id FROM "Dish" WHERE name = 'Margherita Pizza'), (SELECT id FROM "Ingredient" WHERE name = 'Tomato Sauce')),
((SELECT id FROM "Dish" WHERE name = 'Beef Stir-Fry'), (SELECT id FROM "Ingredient" WHERE name = 'Beef Strips')),
((SELECT id FROM "Dish" WHERE name = 'Vegetarian Lasagna'), (SELECT id FROM "Ingredient" WHERE name = 'Lasagna Noodles'));
