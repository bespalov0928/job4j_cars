drop table items
create table items(
  id serial primary key,
  description text,
  carId int not null references cars(id),
  fotoId int references fotos(id),
  sold boolean,
  accauntId int references accounts(id),
  dateItem timestamp
);