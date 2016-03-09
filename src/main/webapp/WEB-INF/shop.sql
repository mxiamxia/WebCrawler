USE shop;
DROP TABLE IF EXISTS `items`;

CREATE TABLE items (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` VARCHAR(30),
  `sale_price`  DOUBLE,
  `size` INTEGER,
  `color` VARCHAR(20),
  `status` BOOLEAN default false,
  PRIMARY KEY (`id`)
);

DROP TABLE  IF EXISTS `item_history`;

CREATE TABLE item_history (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` VARCHAR(30),
  `dtime` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `original_price` DOUBLE,
  `sale_price`  DOUBLE,
  `size` INTEGER,
  `color` VARCHAR(20),
  `recommendation` BOOLEAN,
  PRIMARY KEY (`id`)
);

INSERT INTO items (item_id, sale_price, size, color) VALUES ('B22222', 99.99, 8, 'RED');

SELECT * FROM item_history WHERE  `dtime` BETWEEN '2015-08-18 15:40:03' AND '2015-08-18 15:40:53'