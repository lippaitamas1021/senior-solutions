package activities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ActivityDAO {

    private EntityManagerFactory emf;

    public ActivityDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Activity activity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
        em.close();
    }

    public Activity findById(String depName, long id) {
        EntityManager em = emf.createEntityManager();
        Activity activity = em.find(Activity.class, new ActivityId(depName, id));
        em.close();
        return activity;
    }

    public List<Activity> listAll() {
        EntityManager em = emf.createEntityManager();
        List<Activity> activities = em.createQuery("select a from Activity a order by a.name", Activity.class).getResultList();
        em.close();
        return activities;
    }

    public void changeName(String depName, long id, String newName) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.find(Activity.class, new ActivityId(depName, id));
        activity.setName(newName);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.getReference(Activity.class, id);
        em.remove(activity);
        em.getTransaction().commit();
        em.close();
    }
}