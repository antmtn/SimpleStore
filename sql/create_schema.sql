DROP TABLE IF EXISTS OrderItems, Orders;
DROP TABLE IF EXISTS CartItems, Carts;
DROP TABLE IF EXISTS Products, Users;

CREATE TABLE Products (
    product_id INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    price      DECIMAL(8,2) NOT NULL,
    quantity   INT UNSIGNED NOT NULL,
    image      VARCHAR(255) NOT NULL
);

CREATE TABLE Users (
    user_id     INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    is_admin    BOOLEAN DEFAULT FALSE,
    username    VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL
);

CREATE TABLE Carts (
    cart_id     INT PRIMARY KEY AUTO_INCREMENT,
    user_id     INT REFERENCES Users(user_id)
);

CREATE TABLE CartItems (
    cart_id     INT NOT NULL,
    product_id  INT NOT NULL,
    quantity    TINYINT UNSIGNED NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES Carts(cart_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id),
    PRIMARY KEY(cart_id, product_id)
);

CREATE TABLE Orders (
    order_id INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE OrderItems (
    order_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    quantity TINYINT UNSIGNED NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);