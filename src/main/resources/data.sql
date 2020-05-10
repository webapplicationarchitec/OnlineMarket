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

-- INSERT INTO `account` VALUES ('Admin','admin', '1', 'admin@miu.edu', 'Ha', 'Nguyen', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', null, null);
-- INSERT INTO `account` VALUES ('Seller','seller', '1', 'thanhtran@miu.edu', 'Luan', 'Tran', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', null, '2');
-- INSERT INTO `account` VALUES ('Buyer','buyer', '1', 'thainguyen@miu.edu', 'Hai', 'Nguyen', '$2b$10$QwKk5pHK1RhFfeFqPiaGx.7XHD/B5Y2LbxtkRJVKVOTZhezdRacwa', 'DebitCard', '1');