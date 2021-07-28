package activities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityDaoTest {

    @Autowired
    private ActivityDao activityDao;

    @Test
    void testSaveAndFindActivity() {
        activityDao.saveActivity(new Activity("Programming"));
        Activity activity = activityDao.findActivityByName("Programming");
        assertEquals("Programming", activity.getName());
    }
}