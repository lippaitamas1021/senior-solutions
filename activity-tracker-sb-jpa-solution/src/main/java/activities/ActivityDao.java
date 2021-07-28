package activities;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ActivityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveActivity(Activity activity) {
        entityManager.persist(activity);
    }

    public Activity findActivityByName(String name) {
        return entityManager.createQuery("select a from Activity a where a.name = :name", Activity.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
