create table doctors(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    dni varchar(20) not null unique,
    specialty varchar(100) not null,
    address varchar(100) not null,
    county varchar(100) not null,
    zipcode varchar(9) not null,
    complement varchar(100),
    city varchar(100) not null,

    primary key(id)

);