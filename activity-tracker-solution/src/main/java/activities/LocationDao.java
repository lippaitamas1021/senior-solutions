//package activities;
//
//import org.springframework.stereotype.Repository;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//
//@Repository
//public class LocationDao {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private final EntityManagerFactory emf;
//
//    public LocationDao(EntityManagerFactory emf) {
//        this.emf = emf;
//    }
//
//    @Transactional
//    public void saveLocation(Location location) {
//        entityManager = emf.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(location);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    public Location findLocationNumber(int number) {
//        entityManager = emf.createEntityManager();
//        Location location = entityManager.createQuery("select location from Location location where location.number = :number",
//                Location.class)
//                .setParameter("number", number)
//                .getSingleResult();
//        entityManager.close();
//        return location;
//    }
//}
