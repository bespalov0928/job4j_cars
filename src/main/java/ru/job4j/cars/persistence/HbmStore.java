package ru.job4j.cars.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.controller.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HbmStore implements AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final HbmStore INST = new HbmStore();
    }

    public static HbmStore instOf() {
        return Lazy.INST;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            session.close();
            return rsl;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public List<Item> findAll() {
        return this.tx(session -> {
            List<Item> list = session.createQuery("select distinct i from Item i "
                    + "left join fetch i.fotos f"
                    + "left join fetch i.account a"
                    + "left join fetch i.car c")

                    .list();

            return list;
        });
    }

    public <T> T createItem(T item) {
        return this.tx(session -> {
            session.save(item);
            return item;
        });
    }

    public Item findItemId(int id) {
        return this.tx(session -> {
            Item rsl = (Item) session.createQuery("from ru.job4j.cars.controller.Item i " +
                            "left join fetch i.fotos where i.id=:id"
            ).setParameter("id", id).uniqueResult();
            return rsl;
        });
    }

    public Item updateItem(Item item) {
        return this.tx(session -> {
            session.update(item);
            return item;
        });

    }

    public void updateItemFotos(Item item) {
        this.tx(session -> {
            session.createQuery("update Item i set i.car = :icar, i.sold =:isold where i.id =:iid"
            ).setParameter("icar", item.getCar()
            ).setParameter("isold", item.getSold()
            ).executeUpdate();
            return null;
        });
    }

    public Brend findBrendId(int id) {
        return this.tx(session -> {
            Brend rsl = (Brend) session.createQuery("from ru.job4j.cars.controller.Brend where id=:id"
            ).setParameter("id", id).uniqueResult();
            return rsl;
        });
    }

    public List<Brend> findAllBrend() {
        return this.tx(session -> {
            List<Brend> rsl = session.createQuery("from ru.job4j.cars.controller.Brend").list();
            return rsl;
        });
    }

    public Model findModelId(int id) {
        return this.tx(session -> {
            Model rsl = (Model) session.createQuery("from ru.job4j.cars.controller.Model where id=:id"
            ).setParameter("id", id).uniqueResult();
            return rsl;
        });
    }

    public List<Model> findAllModel() {
        return this.tx(session -> {
            List<Model> rsl = session.createQuery("from ru.job4j.cars.controller.Model").list();
            return rsl;
        });
    }

    public TypeBody findBodyId(int id) {

        return this.tx(session -> {
            TypeBody rsl = (TypeBody) session.createQuery("from ru.job4j.cars.controller.TypeBody where id=:id"
            ).setParameter("id", id).uniqueResult();
            return rsl;
        });
    }

    public List<TypeBody> findAllBody() {
        return this.tx(session -> {
            List<TypeBody> rsl = session.createQuery("from ru.job4j.cars.controller.TypeBody").list();
            return rsl;
        });
    }

    public Car findCarId(int id) {
        return this.tx(session -> {
            Car rsl = (Car) session.createQuery("from ru.job4j.cars.controller.Car where id = :id"
            ).setParameter("id", id
            ).uniqueResult();
            return rsl;
        });
    }

    public Car findCar(Brend brend, Model model, TypeBody body) {

        return this.tx(session -> {
            Car rsl = (Car) session.createQuery("from ru.job4j.cars.controller.Car c " +
                    "where c.brend = :brend " +
                    "and  c.model=:model " +
                    "and c.typeBody = :body"
            ).setParameter("brend", brend
            ).setParameter("model", model
            ).setParameter("body", body
            ).uniqueResult();
            return rsl;
        });
    }

    public Account findAccauntId(int id) {
        return this.tx(session -> {
            Account rsl = (Account) session.createQuery("from ru.job4j.cars.controller.Account where id = :id"
            ).setParameter("id", id
            ).uniqueResult();
            return rsl;
        });
    }

    public Account findAccountByEmail(String email){
        return this.tx(session -> {
            Account rsl = (Account) session.createQuery("from ru.job4j.cars.controller.Account where email = :email"
            ).setParameter("email", email
            ).uniqueResult();
            return rsl;
        });
    }



    public List<Foto> findFotosId(int item_id) {
        return this.tx(session -> {
            Item item = (Item) session.createQuery("select distinct i from Item i "
                    + "left join fetch i.fotos f where i.id = :id"
            ).setParameter("id", item_id
            ).uniqueResult();

            List<Foto> fotos = new ArrayList<>();
            if (item != null) {
                fotos = item.getFotos();
            }
            return fotos;
        });
    }

    public Foto findFotoByName(String name) {
        return this.tx(session -> {
            Foto rsl = (Foto) session.createQuery("from ru.job4j.cars.controller.Foto where pathName =:pathName"
            ).setParameter("pathName", name
            ).uniqueResult();
            return rsl;
        });
    }

    public Foto findFotoByNameId(String name, int item_id) {
        return this.tx(session -> {
            Item item = (Item) session.createQuery("from ru.job4j.cars.controller.Item i " +
                    "left join fetch i.fotos where i.id = :id"
            ).setParameter("id", item_id
            ).uniqueResult();

            Foto rsl = null;
            List<Foto> fotos = item.getFotos();
            if (item != null) {
                for (Foto foto : fotos) {
                    if (foto.getPathName().equals(name)) {
                        rsl = foto;
                        break;
                    }
                }
            }
            return rsl;
        });
    }

    public User findUserByEmail(String email){
        return this.tx(session -> {
            User rsl = (User) session.createQuery("from ru.job4j.cars.controller.User where email =:email"
            ).setParameter("email", email
            ).uniqueResult();
            return rsl;
        });

    }
}

