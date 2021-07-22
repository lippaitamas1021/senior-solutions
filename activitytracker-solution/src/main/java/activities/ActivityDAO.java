package activities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ActivityDao {

    private final EntityManagerFactory emf;

    public ActivityDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Activity activity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
        em.close();
    }
    public Activity findActivityById(long id) {
        EntityManager em = emf.createEntityManager();
        Activity activity = em.find(Activity.class, id);
        em.close();
        return activity;
    }
    public List<Activity> listActivities() {
        EntityManager em = emf.createEntityManager();
        List<Activity> activities = em.createQuery("select a from Activity a order by a.name", Activity.class).getResultList();
        em.close();
        return activities;
    }
    public void changeName(long id, String newName) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.find(Activity.class, id);
        activity.setName(newName);
        em.getTransaction().commit();
        em.close();
    }
    public void updateActivity(long id, String description) {
        EntityManager manager = emf.createEntityManager();
        Activity activity = manager.find(Activity.class, id);
        manager.getTransaction().begin();
        activity.setDescription(description);
        manager.getTransaction().commit();
        manager.close();
    }
    public void deleteActivity(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.getReference(Activity.class, id);
        em.remove(activity);
        em.getTransaction().commit();
        em.close();
    }
    public void updateActivity(Activity activity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(activity);
        em.getTransaction().commit();
        em.close();
    }
    public void updateEActivityNames() {
        EntityManager em = emf.createEntityManager();
        List<Activity> activities = em.createQuery("select a from Activity a order by a.name", Activity.class)
                .getResultList();
        em.getTransaction().begin();
        for (Activity activity : activities) {
            activity.setName(activity.getName() + " ****");
            System.out.println("Modified");
            em.flush();
        }
        em.getTransaction().commit();
        em.close();
    }
    public Activity findActivityByIdWithLabels(long id) {
        EntityManager manager = emf.createEntityManager();
        Activity activity = manager.createQuery("select a from Activity a join fetch a.labels where a.id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        manager.close();
        return activity;
    }
    public Activity findActivityByIdWithNickNames(long id) {
        EntityManager em = emf.createEntityManager();
        Activity activity = em.createQuery("select a from Activity a join fetch a.nickNames where id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }
    public Activity findActivityByIdWithEntries(Long id) {
        EntityManager em = emf.createEntityManager();
        Activity activity = em.createQuery("select a from Activity a join fetch a.activityBookings where id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }
    public Activity findActivityByIdWithPhoneNumbers(Long id) {
        EntityManager em = emf.createEntityManager();
        Activity activity = em.createQuery("select a from Activity a join fetch a.phoneNumbers where id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }
}
