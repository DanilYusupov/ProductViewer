CREATE SCHEMA products;
CREATE TABLE products.items (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  category TEXT NOT NULL,
  rating SMALLINT DEFAULT 0,
  price INT);