-- main tables (from specifications)
DROP TABLE IF EXISTS suppliers CASCADE;
CREATE TABLE suppliers
(
    id          SERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(255),
    description TEXT
);

DROP TABLE IF EXISTS categories CASCADE;
CREATE TABLE categories
(
    id          SERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(255),
    department  VARCHAR(255),
    description TEXT
);

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    id              SERIAL PRIMARY KEY NOT NULL,
    supplier_id     INTEGER REFERENCES suppliers (id) ON DELETE CASCADE,
    category_id     INTEGER REFERENCES categories (id) ON DELETE CASCADE,
    name            VARCHAR(255),
    description     TEXT,
    image_file_name VARCHAR(255)
);

-- additional tables (mirroring the code)
DROP TABLE IF EXISTS prices CASCADE;
CREATE TABLE prices
(
    id          SERIAL PRIMARY KEY NOT NULL,
    product_id  INTEGER REFERENCES products (id) ON DELETE CASCADE,
    sum         FLOAT,
    currency    VARCHAR(10),
    date        DATE
);

DROP TABLE IF EXISTS addresses CASCADE;
CREATE TABLE addresses
(
    id              SERIAL PRIMARY KEY NOT NULL,
    country         VARCHAR(100),
    city            VARCHAR(100),
    zipcode         VARCHAR(10),
    home_address    VARCHAR(100)
);

DROP TABLE IF EXISTS carts CASCADE;
CREATE TABLE carts
(
    id  SERIAL PRIMARY KEY NOT NULL
);

DROP TABLE IF EXISTS users CASCADE;
DROP TYPE IF EXISTS userStatus;
CREATE TYPE userStatus AS ENUM('SIGNED', 'UNSIGNED');
CREATE TABLE users
(
    id              SERIAL PRIMARY KEY NOT NULL,
    name            VARCHAR(100),
    email           VARCHAR(100),
    password        VARCHAR(100),
    phone_number    VARCHAR(30),
    billing_id      INTEGER REFERENCES addresses (id) ON DELETE SET NULL,
    shipping_id     INTEGER REFERENCES addresses (id) ON DELETE SET NULL,
    user_status     userStatus,
    my_cart_id      INTEGER REFERENCES carts (id) NOT NULL
);

DROP TABLE IF EXISTS items CASCADE;
CREATE TABLE items
(
    id          SERIAL PRIMARY KEY NOT NULL,
    product_id  INTEGER REFERENCES products (id) ON DELETE SET NULL,
    price_id    INTEGER REFERENCES prices (id) ON DELETE SET NULL,
    cart_id     INTEGER REFERENCES carts (id) ON DELETE CASCADE,
    quantity    INTEGER
);

DROP TABLE IF EXISTS logs CASCADE;
CREATE TABLE logs
(
    id      SERIAL PRIMARY KEY NOT NULL
);

DROP TABLE IF EXISTS events CASCADE;
CREATE TABLE events
(
    id              SERIAL PRIMARY KEY NOT NULL,
    date            DATE,
    description     VARCHAR(100),
    log_id          INTEGER REFERENCES logs (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS orders CASCADE;
DROP TYPE IF EXISTS orderStatus;
CREATE TYPE orderStatus AS ENUM('CHECKED', 'PAID', 'CONFIRMED', 'SHIPPED');
CREATE TABLE orders
(
    id              SERIAL PRIMARY KEY NOT NULL,
    name            VARCHAR(50),
    description     VARCHAR(100),
    cart_id         INTEGER REFERENCES carts (id) ON DELETE SET NULL,
    user_id         INTEGER REFERENCES users (id) ON DELETE CASCADE,
    order_status    orderStatus,
    log_id          INTEGER REFERENCES logs (id) UNIQUE
);