CREATE TABLE category
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	created_date DATE DEFAULT CURRENT_DATE,
	updated_date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE product
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	price NUMERIC(10,2),
	stock INT,
	category_id INT REFERENCES category(id),
	created_date DATE DEFAULT CURRENT_DATE,
	updated_date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE customer
(
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255),
	email VARCHAR(255) NOT NULL,
	passwrd VARCHAR(255) NOT NULL,
	created_date DATE DEFAULT CURRENT_DATE,
	updated_date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE "order"
(
	id SERIAL PRIMARY KEY,
	customer_id INT REFERENCES customer(id),
	total_amount NUMERIC(10,2),
	order_date timestamp,
	created_date DATE DEFAULT CURRENT_DATE,
	updated_date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE payment
(
	id SERIAL PRIMARY KEY,
	order_id INT REFERENCES "order"(id),
	payment_method VARCHAR(50),
	amount NUMERIC(10,2) NOT NULL,
	created_date DATE DEFAULT CURRENT_DATE,
	updated_date DATE DEFAULT CURRENT_DATE
);