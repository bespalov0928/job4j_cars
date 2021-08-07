drop table item
create table items(
id serial primary key,
description text,
carId int not null references car(id),
fotoId int references foto(id),
sold boolean,
accauntId int references account(id)
)