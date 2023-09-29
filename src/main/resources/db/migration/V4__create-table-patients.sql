create table patients(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    dni varchar(20) not null unique,
    address varchar(100) not null,
    county varchar(100) not null,
    zipcode varchar(9) not null,
    complement varchar(100),
    city varchar(100) not null,
    phone varchar(20) not null,
    is_active tinyint not null,

    primary key(id)

);