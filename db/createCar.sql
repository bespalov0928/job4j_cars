drop table car;
create table cars(
id serial primary key,
name text,
typeId int not null references typeBody(id)
)