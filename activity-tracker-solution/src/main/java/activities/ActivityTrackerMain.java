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

        manager.persist(new Activity(LocalDateTime.of(2021, 7, 13, 14, 55),
                "gyors kör a tó körül", ActivityType.RUNNING));
        manager.persist(new Activity(LocalDateTime.of(2021, 7, 15, 19, 15),
                "esti levezetés", ActivityType.RUNNING));
        manager.persist(new Activity(LocalDateTime.of(2021, 7, 17, 6, 0),
                "hajnali bicózás az erdőben", ActivityType.BIKING));

        manager.getTransaction().commit();

        manager.close();
    }

//    public static void main(String[] args) {
//        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ActivityConfiguration.class)) {
//            ActivityRepository activityRepository = context.getBean(ActivityRepository.class);
//            activityRepository.save(new Activity("Debug"));
//            StreamSupport.stream(activityRepository.findAll().spliterator(), false)
//                    .map(Activity::getName).forEach(System.out::println);
//        }
//    }

//    public static void main(String[] args) {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
//        EntityManager manager = factory.createEntityManager();
//        insert(manager);
//        factory.close();
//    }
//
//    private static void insert(EntityManager manager) {
//        manager.getTransaction().begin();
//        manager.persist(new Activity(LocalDateTime.of(2021, 7, 20, 9, 43),
//                ActivityType.RUNNING));
//        manager.persist(new Activity(LocalDateTime.of(2021, 7, 19, 15, 0),
//                "Road cycling", ActivityType.BIKING));
//        manager.persist(new Activity(LocalDateTime.of(2021, 7, 18, 8, 30),
//                "Walk trip in the forest", ActivityType.HIKING));
//        manager.getTransaction().commit();
//        manager.close();
//    }
}
