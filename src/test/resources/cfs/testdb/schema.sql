SET DATABASE SQL SYNTAX ORA TRUE;

drop table Product if exists;
drop table Sale if exists;
drop table Delivery if exists;
drop table SalesLineItem if exists;

 create table Product (
        id bigint not null,
        description varchar(255),
        name varchar(255),
        quantity integer not null,
        primary key (id)
    );

   create table Sale (
        id bigint not null,
        orderDate DATE not null,
        customerName varchar(255),
        delivery_id bigint,
        primary key (id)
    );
    
     create table Delivery (
        id bigint not null,
        deliveryAddress varchar(255),
        deliveryDate date,
        status varchar(255),
        primary key (id)
    );

create table SalesLineItem (
        id bigint not null,
        quantity integer not null,
        product_id bigint not null,
        sale_id bigint not null,
        primary key (id)
    );
	
     alter table Sale 
        add constraint FK2lv4qceic8oqowh0whomb5p4e 
        foreign key (delivery_id) 
        references Delivery;
        
  alter table SalesLineItem 
        add constraint FKgje4axgtnyi2ujml2frwc1yy4 
        foreign key (product_id) 
        references Product;

    alter table SalesLineItem 
        add constraint FK9jpnkrk2tag0moilf4heor4rp 
        foreign key (sale_id) 
        references Sale;

