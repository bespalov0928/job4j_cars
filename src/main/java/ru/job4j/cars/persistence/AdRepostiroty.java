package ru.job4j.cars.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.controller.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdRepostiroty {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

//            TypeBody cupe = new TypeBody("купе");
//            TypeBody sedan = new TypeBody("седан");
//            TypeBody universal = new TypeBody("универсал");
//            session.save(cupe);
//            session.save(sedan);
//            session.save(universal);
//
//            TypeBody cupe = (TypeBody) session.createQuery("from ru.job4j.cars.controller.TypeBody t where t.name='купе'").uniqueResult();
//            TypeBody sedan = (TypeBody) session.createQuery("from ru.job4j.cars.controller.TypeBody t where t.name='седан'").uniqueResult();
//            TypeBody universal = (TypeBody) session.createQuery("from ru.job4j.cars.controller.TypeBody t where t.name='универсал'").uniqueResult();
//            Car vaz = new Car("ваз", cupe);
//            Car moskvich = new Car("москвич", universal);
//            Car gaz = new Car("газ", sedan);
//            Car izh = new Car("иж", universal);
//            session.save(vaz);
//            session.save(moskvich);
//            session.save(gaz);
//            session.save(izh);
//
//            Foto foto1 = new Foto("C:\\projects\\job4j_cars\\images\\image1.png");
//            Foto foto2 = new Foto("C:\\projects\\job4j_cars\\images\\image2.png");
//            Foto foto3 = new Foto("C:\\projects\\job4j_cars\\images\\image3.png");
//            session.save(foto1);
//            session.save(foto2);
//            session.save(foto3);
//
//            Account accountIvanov = new Account("ivavov", "ivanov@mail.ru", "89991245012");
//            Account accountPetov = new Account("petrov", "petrov@mail.ru", "88945621212");
//            Account accountSidorov = new Account("sidorov", "sidorov@mail.ru", "88975621256");
//            session.save(accountIvanov);
//            session.save(accountPetov);
//            session.save(accountSidorov);
//
//
//            Car vaz = (Car) session.createQuery("from ru.job4j.cars.controller.Car c where c.name = 'ваз'").uniqueResult();
//            Car moskvich = (Car) session.createQuery("from ru.job4j.cars.controller.Car c where c.name = 'москвич'").uniqueResult();
//            Car gaz = (Car) session.createQuery("from ru.job4j.cars.controller.Car c where c.name = 'газ'").uniqueResult();
//            Foto foto1 = (Foto) session.createQuery("from ru.job4j.cars.controller.Foto f where f.id=1").uniqueResult();
//            Foto foto2 = (Foto) session.createQuery("from ru.job4j.cars.controller.Foto f where f.id=2").uniqueResult();
//            Account accountIvanov = (Account) session.createQuery("from ru.job4j.cars.controller.Account a where a.email='ivanov@mail.ru'").uniqueResult();
//            Account accountPetov = (Account) session.createQuery("from ru.job4j.cars.controller.Account a where a.email='petrov@mail.ru'").uniqueResult();
//            Account accountSidorov = (Account) session.createQuery("from ru.job4j.cars.controller.Account a where a.email='sidorov@mail.ru'").uniqueResult();
//            Item item1 = new Item("Небитнекрашен", vaz, Arrays.asList(new Foto[]{foto1, foto2}), false, accountIvanov,new Date(System.currentTimeMillis()));
//            Item item2 = new Item("Небитнекрашен", moskvich, new ArrayList<>(), false, accountPetov,new Date(System.currentTimeMillis()));
//            Item item3 = new Item("Небитнекрашен", gaz, new ArrayList<>(), true, accountSidorov,new Date(System.currentTimeMillis()));
//            session.save(item1);
//            session.save(item2);
//            session.save(item3);

//            //объявления за последний день
//            Timestamp timestamp = Timestamp.valueOf("2021-08-11 00:00:00");
//            List<Item> item = (List<Item>) session.createQuery("from ru.job4j.cars.controller.Item i where  i.dateItem >= :fDate")
//                    .setParameter("fDate", timestamp)
//                    .list();
//
//            //показать объявления с фото
//            List<Item> items = session.createQuery("from ru.job4j.cars.controller.Item i where i.fotos.size >0").list();
//
//            //показать объявления  определнной марки
//            Car gaz = (Car) session.createQuery("from ru.job4j.cars.controller.Car c where c.name = 'газ'").uniqueResult();
//            List<Item> list = session.createQuery("from ru.job4j.cars.controller.Item i where i.car = :fCar")
//                    .setParameter("fCar", gaz)
//                    .list();

            //показать все объявления
            List<Item> list = session.createQuery("select distinct i from Item i "
//                    +"left join fetch i.car c")
                    +"left join fetch i.fotos f"
                    +"left join fetch i.account a"
                    +"left join fetch i.car c")

                    .list();

//            List<Foto> listFoto = session.createQuery("select f from Foto f left join fetch f.item i").list();

//            List<Item> list = session.createQuery("from Item as item "
//                    + " left join fetch item.car as car"
//                    + " left join fetch item.fotos as foto"
//                    + " left join fetch item.account as accaunt")
//                    .list();



            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
