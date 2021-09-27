drop table cars;
create table cars(
id serial primary key,
name text,
typeId int not null references typeBody(id),
modelId int not null references models(id)
);