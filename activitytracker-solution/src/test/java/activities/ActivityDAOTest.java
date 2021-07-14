package activities;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

class ActivityDAOTest {

    private ActivityDAO activityDAO;

    @BeforeEach
    void setUp() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost/activities");
        dataSource.setUser("activities");
        dataSource.setPassword("activities");
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        activityDAO = new ActivityDAO(entityManagerFactory);
    }

    @Test
    void testSaveThenFindById() {
        Activity activity = new Activity("driving");
        activityDAO.save(activity);
        long id = activity.getId();
        Activity anotherActivity = activityDAO.findById(id);
        assertEquals("driving", anotherActivity.getName());
    }

    @Test
    void testSaveThenListAll() {
        activityDAO.save(new Activity("riding"));
        activityDAO.save(new Activity("skiing"));
        List<Activity> activities = activityDAO.listAll();
        assertEquals(List.of("riding", "skiing"), activities.stream().map(Activity::getName).collect(Collectors.toList()));
    }

    @Test
    public void testChangeName() {
        Activity activity = new Activity("driving");
        activityDAO.save(activity);
        long id = activity.getId();
        activityDAO.changeName(id, "diving");
        Activity anotherActivity = activityDAO.findById(id);
        assertEquals("diving", anotherActivity.getName());
    }

    @Test
    public void testDelete() {
        Activity activity = new Activity("swimming");
        activityDAO.save(activity);
        long id = activity.getId();
        activityDAO.delete(id);
        List<Activity> activities = activityDAO.listAll();
        assertTrue(activities.isEmpty());
    }
}