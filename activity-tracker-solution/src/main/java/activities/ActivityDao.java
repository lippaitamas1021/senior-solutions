package activities;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivityDao {

    private EntityManagerFactory factory;

    public ActivityDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveActivity(Activity activity) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(activity);
        manager.getTransaction().commit();
        manager.close();
    }

    public Activity findActivityById(long id) {
        EntityManager manager = factory.createEntityManager();
        Activity activity = manager.find(Activity.class, id);
        manager.close();
        return activity;
    }

    public List<Activity> listActivities() {
        EntityManager manager = factory.createEntityManager();
        List<Activity> activities = manager.createQuery("select activity from Activity activity order by activity.startTime", Activity.class).getResultList();
        manager.close();
        return activities;
    }

    public void updateActivity(long id, String description) {
        EntityManager manager = factory.createEntityManager();
        Activity activity = manager.find(Activity.class, id);
        manager.getTransaction().begin();
        activity.setDescription(description);
        manager.getTransaction().commit();
        manager.close();
    }

    public Activity findActivityByIdWithLabels(long id) {
        EntityManager manager = factory.createEntityManager();
        Activity activity = manager.createQuery("select a from Activity a join fetch a.labels where a.id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        manager.close();
        return activity;
    }

    public Activity findActivityByIdWithTrackPoints(long id) {
        EntityManager manager = factory.createEntityManager();
        Activity activity = manager.createQuery("select a from Activity a join fetch a.trackPoints where a.id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        manager.close();
        return activity;
    }

    public List<Coordinate> findTrackPointCoordinatesByDate(LocalDateTime afterThis, int start, int max) {
        EntityManager manager = factory.createEntityManager();
        List<Activity> activities = manager.createNamedQuery("trackPointsAfterDate", Activity.class)
                .setParameter("startTime", afterThis)
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();
        manager.close();
        List<Coordinate> coordinates = getCoordinates(activities);
        return coordinates;
    }

    private List<Coordinate> getCoordinates(List<Activity> activities) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Activity a : activities) {
            for (TrackPoint t : a.getTrackPoints()) {
                coordinates.add(new Coordinate(t.getLat(), t.getLon()));
            }
        }
        return coordinates;
    }

    public List<Object[]> findTrackPointCountByActivity() {
        EntityManager manager = factory.createEntityManager();
        List<Object[]> activities = manager.createQuery(/*"select a.description, count(t) from Activity a join a.trackPoints t group by a.description order by a.startTime"*/"select a.description, size(a.trackPoints) from Activity a order by a.startTime", Object[].class)
                .getResultList();
        manager.close();
        return activities;
    }

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private final EntityManagerFactory factory;
//
//    public ActivityDao(EntityManagerFactory factory) {
//        this.factory = factory;
//    }
//
//    @Transactional
//    public void saveActivity(Activity activity) {
//        entityManager = factory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(activity);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    public Activity findActivityById(long id) {
//        entityManager = factory.createEntityManager();
//        Activity activity = entityManager.find(Activity.class, id);
//        entityManager.close();
//        return activity;
//    }
//
//    public List<Activity> listActivities() {
//        entityManager = factory.createEntityManager();
//        List<Activity> activities = entityManager.createQuery("select activity from Activity activity order by activity.startTime",
//                Activity.class)
//                .getResultList();
//        entityManager.close();
//        return activities;
//    }
//
//    @Transactional
//    public void changeName(long id, String newName) {
//        entityManager = factory.createEntityManager();
//        entityManager.getTransaction().begin();
//        Activity activity = entityManager.find(Activity.class, id);
//        activity.setName(newName);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    @Transactional
//    public void updateActivity(long id, String description) {
//        entityManager= factory.createEntityManager();
//        Activity activity = entityManager.find(Activity.class, id);
//        entityManager.getTransaction().begin();
//        activity.setDescription(description);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    @Transactional
//    public void updateEActivityNames() {
//        entityManager = factory.createEntityManager();
//        List<Activity> activities = entityManager.createQuery("select a from Activity a order by a.name", Activity.class)
//                .getResultList();
//        entityManager.getTransaction().begin();
//        for (Activity activity : activities) {
//            activity.setName(activity.getName() + " ****");
//            System.out.println("Modified");
//            entityManager.flush();
//        }
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    @Transactional
//    public void deleteActivity(long id) {
//        entityManager = factory.createEntityManager();
//        entityManager.getTransaction().begin();
//        Activity activity = entityManager.getReference(Activity.class, id);
//        entityManager.remove(activity);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    public Activity findActivityByIdWithLabels(long id) {
//        entityManager = factory.createEntityManager();
//        Activity activity = entityManager.createQuery("select a from Activity a join fetch a.labels where a.id = :id", Activity.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        entityManager.close();
//        return activity;
//    }
//
//    public Activity findActivityByIdWithNickNames(long id) {
//        entityManager = factory.createEntityManager();
//        Activity activity = entityManager.createQuery("select a from Activity a join fetch a.nickNames where a.id = :id", Activity.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        entityManager.close();
//        return activity;
//    }
//
//    public Activity findActivityByIdWithEntries(Long id) {
//        entityManager = factory.createEntityManager();
//        Activity activity = entityManager.createQuery("select a from Activity a join fetch a.activityBookings where a.id = :id", Activity.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        entityManager.close();
//        return activity;
//    }
//
//    public Activity findActivityByIdWithPhoneNumbers(Long id) {
//        entityManager = factory.createEntityManager();
//        Activity activity = entityManager.createQuery("select a from Activity a join fetch a.phoneNumbers where a.id = :id", Activity.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        entityManager.close();
//        return activity;
//    }
//
//    public Activity findActivityByIdWithTrackPoints(long id) {
//        entityManager = factory.createEntityManager();
//        Activity activity = entityManager.createQuery("select a from Activity a join fetch a.trackPoints where a.id = :id", Activity.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        entityManager.close();
//        return activity;
//    }
//
////    public List<Coordinate> findTrackPointCoordinatesByDate(LocalDateTime afterThis, int start, int max) {
////        EntityManager manager = factory.createEntityManager();
////        List<Activity> activities = manager.createNamedQuery("trackPointsAfterDate", Activity.class)
////                .setParameter("startTime", afterThis)
////                .setFirstResult(start)
////                .setMaxResults(max)
////                .getResultList();
////        manager.close();
////        return getCoordinates(activities);
////    }
//
//    private List<Coordinate> getCoordinates(List<Activity> activities) {
//        List<Coordinate> coordinates = new ArrayList<>();
//        for (Activity a : activities) {
//            for (TrackPoint t : a.getTrackPoints()) {
//                coordinates.add(new Coordinate(t.getLat(), t.getLon()));
//            }
//        }
//        return coordinates;
//    }
//
//    public List<Object[]> findTrackPointCountByActivity() {
//        entityManager = factory.createEntityManager();
//        List<Object[]> activities = entityManager.createQuery(/*"select a.description, count(t) from Activity a join a.trackPoints t group by a.description order by a.startTime"*/"select a.description, size(a.trackPoints) from Activity a order by a.startTime", Object[].class)
//                .getResultList();
//        entityManager.close();
//        return activities;
//    }
//
//    @Transactional
//    public void addPhoneNumber(long id, PhoneNumber phoneNumber) {
//        entityManager = factory.createEntityManager();
//        entityManager.getTransaction().begin();
////        Activity activity = em.find(Activity.class, id);
//        Activity activity = entityManager.getReference(Activity.class, id);    //proxy
//        phoneNumber.setActivity(activity);
//        entityManager.persist(phoneNumber);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    public Activity findActivityByIdWithPhoneNumbers(long id) {
//        entityManager = factory.createEntityManager();
//        Activity activity = entityManager.createQuery("select a from Activity a join fetch a.phoneNumbers where a.id = :id",
//                Activity.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        return activity;
//    }
//
//    public Activity findActivityByIdWithVisitors(long id) {
//        entityManager = factory.createEntityManager();
//        Activity activity = entityManager.createQuery("select a from Activity a join fetch a.visitors where a.id = :id",
//                Activity.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        entityManager.close();
//        return activity;
//    }
//
//    public Activity findActivityByName(String name) {
//        entityManager = factory.createEntityManager();
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Activity> cq = cb.createQuery(Activity.class);
//        Root<Activity> activityRoot = cq.from(Activity.class);
//        cq.select(activityRoot).where(cb.equal(activityRoot.get("name"), name));
//        Activity activity = entityManager.createQuery(cq).getSingleResult();
//        entityManager.close();
//        return activity;
//    }
//
//    public List<Activity> lisActivities(int start, int maxResult) {
//        entityManager = factory.createEntityManager();
//        List<Activity> activities = entityManager.createNamedQuery("listActivities", Activity.class)
//                .setFirstResult(start)
//                .setMaxResults(maxResult)
//                .getResultList();
//        entityManager.close();
//        return activities;
//    }
//
//    public int findLocationNumberByActivityName(String name) {
//        entityManager = factory.createEntityManager();
//        int i = entityManager.createQuery("select location.number from Activity a join fetch a.location location where a.name = :name",
//                Integer.class)
//                .setParameter("name", name)
//                .getSingleResult();
//        entityManager.close();
//        return i;
//    }
//
//    public List<Object[]> listActivityBaseData() {
//        entityManager = factory.createEntityManager();
//        List<Object[]> data = entityManager.createQuery("select a.id, a.name from Activity a")
//                .getResultList();
//        entityManager.close();
//        return data;
//    }
//
//    public List<ActBaseDataDto> listActivityDto() {
//        entityManager = factory.createEntityManager();
//        List<ActBaseDataDto> data = entityManager.createQuery("select new activities.ActBaseDataDto(a.id, a.name)" +
//                "from Activity a order by a.name")
//                .getResultList();
//        entityManager.close();
//        return data;
//    }
}
