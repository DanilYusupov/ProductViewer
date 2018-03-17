CREATE TABLE test_table (
  id SERIAL PRIMARY KEY,
  category TEXT NOT NULL,
  name TEXT NOT NULL,
  price INT,
  rating SMALLINT);