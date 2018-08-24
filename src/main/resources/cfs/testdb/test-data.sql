SET DATABASE SQL SYNTAX ORA TRUE;

insert into Product values (1,'Ketchup','Ketchup',50);
insert into Product values (2,'Vinegar','Vinegar',10);
insert into Product values (3,'Soy Sauce','Soy Sauce',30);
insert into Delivery values(1,'MAKATI',TO_DATE('2018-08-30','yyyy-MM-dd'),'DELIVERED');
insert into Delivery values(2,'PASAY',TO_DATE('2018-08-15','yyyy-MM-dd'),'PENDING');
insert into Sale values(1,TO_DATE('2018-07-30','yyyy-MM-dd'),'Kier Tenorio',1);
insert into Sale values(2,TO_DATE('2020-06-03','yyyy-MM-dd'),'John Cena',2);

insert into SalesLineItem values (1,10,1,1);
insert into SalesLineItem values (2,15,3,1);
insert into SalesLineItem values (3,10,1,2);
insert into SalesLineItem values (4,15,3,2);