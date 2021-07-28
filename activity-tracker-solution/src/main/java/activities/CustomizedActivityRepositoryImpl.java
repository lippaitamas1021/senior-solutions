//package activities;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//public class CustomizedActivityRepositoryImpl implements CustomizedActivityRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Activity> findByNameStartingWith(String namePrefix) {
//        return entityManager.createQuery("select a from Activity a where a.name like :namePrefix",
//                Activity.class).setParameter("namePrefix", namePrefix + "%").getResultList();
//    }
//}
