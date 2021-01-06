create table person(
	id varchar2(20) primary key,
	name varchar2(30) not null
);

insert into person values('kim4321', '김삿갓');

select * from person;