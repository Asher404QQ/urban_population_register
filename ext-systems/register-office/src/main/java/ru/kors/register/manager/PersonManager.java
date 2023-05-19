package ru.kors.register.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.kors.register.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PersonManager {
    public static void main(String[] args) {
//        sessionExample();

        jpaExample();
    }

    private static void jpaExample() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Person p1 = new Person();
        p1.setFirstName("Иван");
        p1.setLastName("Ибрагимов");
        entityManager.persist(p1);
        System.out.println(p1.getPersonId());

        entityManager.getTransaction().commit();

        entityManager = entityManagerFactory.createEntityManager();
        List list = entityManager.createQuery("from Person").getResultList();
        list.forEach(p -> System.out.println(p));

        entityManager.close();
    }

    private static void sessionExample() {
        SessionFactory sessionFactory = buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.getTransaction().begin();

        Person p1 = new Person();
        p1.setFirstName("Константин");
        p1.setLastName("Васильев");

        Long id = (Long) session.save(p1);

        session.getTransaction().commit();

        Person person = session.get(Person.class, id);
        System.out.println(person);

        List<Person> list = session.createQuery("from Person", Person.class).list();
        list.forEach(per -> System.out.println(per));

        session.close();
    }

    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable throwable) {
            System.err.println("Initial SessionFactory creation failed. " + throwable);
            throw new ExceptionInInitializerError(throwable);
        }
    }
}
