package activities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class ActivityTrackerMain {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager entityManager = factory.createEntityManager();
        insert(entityManager);
        factory.close();
    }

    private static void insert(EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(new Activity("Playing basketball"));
        entityManager.persist(new Activity(ActivityType.BASKETBALL, "Playing basketball", LocalDate.of(
                2021,7,20)));
        entityManager.persist(new Activity(ActivityType.HIKING, 5L, "Hiking in Matra", LocalDate.of(
                2021,7,19), "17 km long hiking in North-Hungary"));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
