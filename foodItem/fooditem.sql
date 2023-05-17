SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE food_items (
  id varchar(5) PRIMARY KEY NOT NULL UNIQUE,
  name varchar(50),
  description VARCHAR(100),
  price DECIMAL(10, 2) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO food_items (id,name, description, price)
VALUES ("0001","Pop Corn", "Caramel", "12");

INSERT INTO food_items (id,name, description, price)
VALUES ("0002","Nuggets", "Fried", "9.5");

INSERT INTO food_items (id,name, description, price)
VALUES ("0003","Chips", "BBQ", "5.5");

INSERT INTO food_items (id,name, description, price)
VALUES ("0004","Hot Dog", "Cheese", "7.8");

INSERT INTO food_items (id,name, description, price)
VALUES ("0005","Ice Lemon Tea", "Heaven&Earth", "3.6");

DROP TABLE food_items;
COMMIT;
