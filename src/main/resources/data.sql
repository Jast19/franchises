INSERT INTO Franchise (name) VALUES ('McDonald');
INSERT INTO Franchise (name) VALUES ('Burger King');

INSERT INTO Subsidiary (franchise_id, name) VALUES (1, 'McDonald - Bogotá');
INSERT INTO Subsidiary (franchise_id, name) VALUES (1, 'McDonald - Medellín');
INSERT INTO Subsidiary (franchise_id, name) VALUES (2, 'Burger King - Bogotá');
INSERT INTO Subsidiary (franchise_id, name) VALUES (2, 'Burger King - Medellín');

INSERT INTO Product (subsidiary_id, name, stock) VALUES (1, 'Big Mac', 100);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (1, 'McNuggets', 200);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (1, 'McCafe', 150);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (1, 'Fresas con crema', 50);

INSERT INTO Product (subsidiary_id, name, stock) VALUES (2, 'Whopper', 80);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (2, 'Chicken Fries', 120);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (2, 'Coca-Cola', 250);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (2, 'Aros de cebolla', 100);

INSERT INTO Product (subsidiary_id, name, stock) VALUES (3, 'Quesadilla', 150);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (3, 'Burrito', 50);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (3, 'Nachos', 200);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (3, 'Tacos', 120);

INSERT INTO Product (subsidiary_id, name, stock) VALUES (4, 'Hamburguesa', 180);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (4, 'Sándwich', 70);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (4, 'Pizza', 100);
INSERT INTO Product (subsidiary_id, name, stock) VALUES (4, 'Pollo frito', 250);