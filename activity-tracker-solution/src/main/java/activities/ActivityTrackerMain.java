package activities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ActivityTrackerMain {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager manager = factory.createEntityManager();
        insert(manager);
        factory.close();
    }

    private static void insert(EntityManager manager) {
        manager.getTransaction().begin();
        manager.persist(new Activity(LocalDateTime.of(2021, 7, 20, 9, 43),
                "Running at Margaret Island", ActivityType.RUNNING));
        manager.persist(new Activity(LocalDateTime.of(2021, 7, 19, 15, 0),
                "Road cycling", ActivityType.BIKING));
        manager.persist(new Activity(LocalDateTime.of(2021, 7, 18, 8, 30),
                "Walk trip in the forest", ActivityType.HIKING));
        manager.getTransaction().commit();
        manager.close();
    }
}
