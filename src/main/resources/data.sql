INSERT INTO `category`  VALUES (1,'assets/images/demos/demo-4/cats/1.png', 'Computer& Laptop');
INSERT INTO `category`  VALUES (2,'assets/images/demos/demo-4/cats/2.png', 'Digital Cameras');
INSERT INTO `category`  VALUES (3,'assets/images/demos/demo-4/cats/3.png', 'Smart Phones');
INSERT INTO `category`  VALUES (4,'assets/images/demos/demo-4/cats/4.png', 'Televisions');
INSERT INTO `category`  VALUES (5,'assets/images/demos/demo-4/cats/5.png', 'Audio');
INSERT INTO `category`  VALUES (6,'assets/images/demos/demo-4/cats/6.png', 'Smart Watches');
INSERT INTO `address` (`id`, `city`, `state`, `street`, `zipcode`) VALUES (1, 'Fairfield', 'IA', '1000 N 4th', '52557');
INSERT INTO `address` (`id`, `city`, `state`, `street`, `zipcode`) VALUES (2, 'Orange', 'CA', '96S 2th', '82552');
insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`)
values ('ADMIN', 'admin', 'admin@miu.edu', 'Mrs', 'Aministrator', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 1);
insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`, `address_id`)
values ('BUYER', 'hanguyen', 'hanguyen@miu.edu', 'Ha', 'Nguyen', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 1, 1);
insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`, `address_id`)
values ('BUYER', 'yenpham', 'yenpham@miu.edu', 'Yen', 'Pham', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 1, 2);
insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`)
values ('SELLER', 'hainguyen', 'hainguyen@miu.edu', 'Hai', 'Nguyen', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 1);
insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`)
values ('SELLER', 'luannguyen', 'luannguyen@miu.edu', 'Luan', 'Nguyen', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 0);

insert into account (`account_type`, `username`, `email`, `first_name`, `last_name`, `password`, `account_status`)
values ('SELLER', 'khaian', 'khaian@miu.edu', 'Khai', 'An', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 0);



-- Start 100 because when we insert data it start 1.
insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`seller_id`,`created_date`)
values (100,'Beats by Dr. Dre Wireless Headphones','Headphones','http://localhost:8080/assets/images/demos/demo-4/products/product-10.jpg','30',279.99,1,3,'luannguyen','2020-02-20');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`seller_id`,`created_date`)
values (102,'GoPro - HERO7 Black HD Waterproof Action','Cameras & Camcorders','http://localhost:8080/assets/images/demos/demo-4/products/product-11.jpg','10',349.99,1,2,'luannguyen','2020-02-19');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`seller_id`,`created_date`)
values (103,'Apple - Apple Watch Series 3 with White Sport Band','Smartwatches','http://localhost:8080/assets/images/demos/demo-4/products/product-12.jpg','11',214.49,1,6,'luannguyen','2020-02-18');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`seller_id`,`created_date`)
values (104,'Lenovo - 330-15IKBR 15.6"','Smartwatches','http://localhost:8080/assets/images/demos/demo-4/products/product-13.jpg',12,339.99,1,1,'luannguyen','2020-02-17');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`seller_id`,`created_date`)
values (105,'Sony - Alpha a5100 Mirrorless Camera','Digital Cameras','http://localhost:8080/assets/images/demos/demo-4/products/product-14.jpg','15',499.99,1,2,'hainguyen','2020-02-16');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`seller_id`,`created_date`)
values (106,'Home Mini - Smart Speaker with Google Assistant','Audio','http://localhost:8080/assets/images/demos/demo-4/products/product-15.jpg','10',49.00,1,5,'hainguyen','2020-02-15');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`seller_id`,`created_date`)
values (107,'WONDERBOOM Portable Bluetooth Speaker','Audio','http://localhost:8080/assets/images/demos/demo-4/products/product-16.jpg','9',99.99,1,5,'hainguyen','2020-02-14');


insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`seller_id`,`created_date`)
values (108,'Google - Home Hub with Google Assistant','Smart Home','http://localhost:8080/assets/images/demos/demo-4/products/product-17.jpg','2',149.00,1,4,'hainguyen','2020-02-13');



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




