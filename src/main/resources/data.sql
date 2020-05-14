INSERT INTO `category`  VALUES (1,'assets/images/demos/demo-4/cats/1.png', 'Computer& Laptop');
INSERT INTO `category`  VALUES (2,'assets/images/demos/demo-4/cats/2.png', 'Digital Cameras');
INSERT INTO `category`  VALUES (3,'assets/images/demos/demo-4/cats/3.png', 'Smart Phones');
INSERT INTO `category`  VALUES (4,'assets/images/demos/demo-4/cats/4.png', 'Televisions');
INSERT INTO `category`  VALUES (5,'assets/images/demos/demo-4/cats/5.png', 'Audio');
INSERT INTO `category`  VALUES (6,'assets/images/demos/demo-4/cats/6.png', 'Smart Watches');
INSERT INTO `address` (`id`, `city`, `state`, `street`, `zipcode`) VALUES (100, 'Fairfield', 'IA', '1000 N 4th', '52557');
INSERT INTO `address` (`id`, `city`, `state`, `street`, `zipcode`) VALUES (200, 'Orange', 'CA', '96S 2th', '82552');
insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`)
values ('ADMIN', 'admin', 'admin@miu.edu', 'Mrs', 'Aministrator', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 1);
insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`, `address_id`)
values ('BUYER', 'hanguyen', 'hanguyen@miu.edu', 'Ha', 'Nguyen', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 1, 100);
insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`, `address_id`)
values ('BUYER', 'yenpham', 'yenpham@miu.edu', 'Yen', 'Pham', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 1, 200);
insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`)
values ('SELLER', 'hainguyen', 'hainguyen@miu.edu', 'Hai', 'Nguyen', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 1);
insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`)
values ('SELLER', 'luannguyen', 'luannguyen@miu.edu', 'Luan', 'Nguyen', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 0);

insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`)
values ('SELLER', 'khaian', 'khaian@miu.edu', 'Khai', 'An', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 0);



-- Start 100 because when we insert data it start 1.
INSERT INTO `product` VALUES ('100', '2020-05-14 12:54:40', 'Enjoy immersive visuals with this Samsung 8K smart TV. Quantum HDR technology delivers deep blacks and bright colors, while the Real Game Enhancer helps eliminate stuttering and tearing for a smooth picture. This Samsung 8K smart TV', 'QuietComfort 35', 'http://localhost:8080/assets/images/demos/demo-4/products/product-10.jpg', '30', '279.99', '0', '3', 'luannguyen');
INSERT INTO `product` VALUES ('102', '2020-05-14 12:52:37', 'Enjoy immersive visuals with this Samsung 8K smart TV. Quantum HDR technology delivers deep blacks and bright colors, while the Real Game Enhancer helps eliminate stuttering and tearing for a smooth picture. This Samsung 8K smart TV', 'Canon · EOS · EOS 80D · DSLR', 'http://localhost:8080/assets/images/demos/demo-4/products/product-11.jpg', '10', '349.99', '0', '2', 'luannguyen');
INSERT INTO `product` VALUES ('103', '2020-05-14 12:55:55', 'Enjoy immersive visuals with this Samsung 8K smart TV. Quantum HDR technology delivers deep blacks and bright colors, while the Real Game Enhancer helps eliminate stuttering and tearing for a smooth picture. This Samsung 8K smart TV', 'Apple Watch Series 5', 'http://localhost:8080/assets/images/demos/demo-4/products/product-12.jpg', '11', '214.49', '0', '6', 'luannguyen');
INSERT INTO `product` VALUES ('104', '2020-05-14 12:56:35', 'Enjoy immersive visuals with this Samsung 8K smart TV. Quantum HDR technology delivers deep blacks and bright colors, while the Real Game Enhancer helps eliminate stuttering and tearing for a smooth picture. This Samsung 8K smart TV', 'Dell - XPS 2-in-1 13.4\" Touch-Screen', 'http://localhost:8080/assets/images/demos/demo-4/products/product-13.jpg', '12', '339.99', '0', '1', 'luannguyen');
INSERT INTO `product` VALUES ('105', '2020-05-14 13:02:49', 'Enjoy immersive visuals with this Samsung 8K smart TV. Quantum HDR technology delivers deep blacks and bright colors, while the Real Game Enhancer helps eliminate stuttering and tearing for a smooth picture. This Samsung 8K smart TV', 'Sony Alpha 5100', 'http://localhost:8080/assets/images/demos/demo-4/products/product-14.jpg', '15', '499.99', '0', '2', 'hainguyen');
INSERT INTO `product` VALUES ('106', '2020-05-14 12:59:29', 'Enjoy immersive visuals with this Samsung 8K smart TV. Quantum HDR technology delivers deep blacks and bright colors, while the Real Game Enhancer helps eliminate stuttering and tearing for a smooth picture. This Samsung 8K smart TV', 'Beats Pill+', 'http://localhost:8080/assets/images/demos/demo-4/products/product-15.jpg', '10', '49', '0', '5', 'hainguyen');
INSERT INTO `product` VALUES ('107', '2020-05-14 13:00:20', 'Enjoy immersive visuals with this Samsung 8K smart TV. Quantum HDR technology delivers deep blacks and bright colors, while the Real Game Enhancer helps eliminate stuttering and tearing for a smooth picture. This Samsung 8K smart TV', 'WONDERBOOM Portable Bluetooth Speaker', 'http://localhost:8080/assets/images/demos/demo-4/products/product-16.jpg', '9', '99.99', '0', '5', 'hainguyen');
INSERT INTO `product` VALUES ('108', '2020-05-14 13:00:54', 'Enjoy immersive visuals with this Samsung 8K smart TV. Quantum HDR technology delivers deep blacks and bright colors, while the Real Game Enhancer helps eliminate stuttering and tearing for a smooth picture. This Samsung 8K smart TV', 'Samsung - 55\" 8K UHD TV with HDR', 'http://localhost:8080/assets/images/demos/demo-4/products/product-17.jpg', '2', '149', '0', '4', 'hainguyen');


insert into review(`Id`,`Comment`,`Date_Create`,`Status`,`Buyer_Id`,`product_Id`,`Seller_Id`)
values  (200,'good','2020-05-12 12:38:00',0,'yenpham',105,'khaian');
insert into review(`Id`,`Comment`,`Date_Create`,`Status`,`Buyer_Id`,`product_Id`,`Seller_Id`)
values  (201,'very good','2020-05-12 12:38:00',0,'yenpham',106,'luannguyen');

insert into online_order(`Id`,`date_create`,`date_delivered`,`date_shipping`,`orderno`,`point`,`shipping_address`,`shipping_fee`,`status`,`tax`,`total`,`buyer_id`,`seller_id`)
values  (201,'2020-05-12 12:38:00',null ,'2020-05-12 12:38:00','123IO',106,'1000N 4th Street, Fairfield, IA',23,0,12,50,'hanguyen','luannguyen');

insert into online_order(`Id`,`date_create`,`date_delivered`,`date_shipping`,`orderno`,`point`,`shipping_address`,`shipping_fee`,`status`,`tax`,`total`,`buyer_id`,`seller_id`)
values  (202,'2020-05-13 12:38:00',null,'2020-05-12 12:38:00','123IO',106,'1000N 4th Street, Fairfield, CA',23,2,12,50,'hanguyen','hainguyen');




insert into ORDER_DETAIL (`Id`,`QTY`,`SELL_PRICE`,`PRODUCT_ID`,`ORDER_ID`)
values  (202,2,10,100,202);

insert into ORDER_DETAIL (`Id`,`QTY`,`SELL_PRICE`,`PRODUCT_ID`,`ORDER_ID`)
values  (203,1,10,102,202);

insert into ORDER_DETAIL (`Id`,`QTY`,`SELL_PRICE`,`PRODUCT_ID`,`ORDER_ID`)
values  (204,2,10,103,201);

insert into ORDER_DETAIL (`Id`,`QTY`,`SELL_PRICE`,`PRODUCT_ID`,`ORDER_ID`)
values  (205,1,10,104,201);

insert into bonus_point(`ID`,`POINTS`,`BUYER_ID`,`SELLER_ID`)

values ( 200, 750, 'hanguyen', 'luannguyen' );




create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

