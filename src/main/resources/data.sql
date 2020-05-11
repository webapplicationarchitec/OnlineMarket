INSERT INTO `category` (`id`, `name`) VALUES (1, 'Computer& Laptop');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'Digital Cameras');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'Smart Phones');
INSERT INTO `category` (`id`, `name`) VALUES (4, 'Televisions');
INSERT INTO `category` (`id`, `name`) VALUES (5, 'Audio');
INSERT INTO `category` (`id`, `name`) VALUES (6, 'Smart Watches');
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
values ('SELLER', 'luannguyen', 'luannguyen@miu.edu', 'Luan', 'Nguyen', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 1);



insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`sellerid`)
values (1,'Beats by Dr. Dre Wireless Headphones','Headphones','http://localhost:8080/assets/images/demos/demo-4/products/product-10.jpg','30',279.99,1,3,'luannguyen');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`sellerid`)
values (2,'GoPro - HERO7 Black HD Waterproof Action','Cameras & Camcorders','http://localhost:8080/assets/images/demos/demo-4/products/product-11.jpg','10',349.99,1,2,'luannguyen');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`sellerid`)
values (3,'Apple - Apple Watch Series 3 with White Sport Band','Smartwatches','http://localhost:8080/assets/images/demos/demo-4/products/product-12.jpg','11',214.49,1,6,'hanguyen');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`sellerid`)
values (4,'Lenovo - 330-15IKBR 15.6"','Smartwatches','http://localhost:8080/assets/images/demos/demo-4/products/product-13.jpg',12,339.99,1,1,'hanguyen');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`sellerid`)
values (5,'Sony - Alpha a5100 Mirrorless Camera','Digital Cameras','http://localhost:8080/assets/images/demos/demo-4/products/product-14.jpg','15',499.99,1,2,'yenpham');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`sellerid`)
values (6,'Home Mini - Smart Speaker with Google Assistant','Audio','http://localhost:8080/assets/images/demos/demo-4/products/product-15.jpg','10',49.00,1,5,'yenpham');

insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`sellerid`)
values (7,'WONDERBOOM Portable Bluetooth Speaker','Audio','http://localhost:8080/assets/images/demos/demo-4/products/product-16.jpg','9',99.99,1,5,'hainguyen');


insert into product(`Id`,`description`,`name`,`photo`,`point`,`price`,`status`,`category_id`,`sellerid`)
values (8,'Google - Home Hub with Google Assistant','Smart Home','http://localhost:8080/assets/images/demos/demo-4/products/product-17.jpg','2',149.00,1,4,'hainguyen');

