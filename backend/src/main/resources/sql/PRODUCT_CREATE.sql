DROP TABLE IF EXISTS PRODUCT;
create table PRODUCT(
    id bigint not null auto_increment primary key,
    name varchar(100),
    category varchar(100),
    description varchar(2000),
    country varchar(50),
    image varchar(250),
    constraint unique_product_1 unique(id)
);