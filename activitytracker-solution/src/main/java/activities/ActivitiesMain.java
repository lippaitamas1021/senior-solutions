package activities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ActivitiesMain {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Activity activity = new Activity("walking");
        em.persist(activity);
//        em.persist(new Activity("hiking"));
//        em.persist(new Activity("racing"));
//        em.persist(new Activity("cycling"));
        em.getTransaction().commit();
        System.out.println(activity.getId());
        long id = activity.getId();
        activity = em.find(Activity.class, id);
        System.out.println(activity);

        em.getTransaction().begin();
        activity = em.find(Activity.class, id);
        activity.setName("reading");
        em.getTransaction().commit();

        List<Activity> activities = em.createQuery("select a from Activity a", Activity.class)
                .getResultList();
        System.out.println(activities);

        em.getTransaction().begin();
        activity = em.find(Activity.class, id);
        em.remove(activity);
        em.getTransaction().commit();

        activities = em.createQuery("select a from Activity a", Activity.class).getResultList();
        System.out.println(activities);

        em.close();
        factory.close();
    }
}
