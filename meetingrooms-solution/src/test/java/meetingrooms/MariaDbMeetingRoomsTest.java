package meetingrooms;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MariaDbMeetingRoomsTest {

    private MariaDbMeetingRooms meetingRoom;
    private List<MeetingRoom> rooms;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        rooms = Arrays.asList(
                new MeetingRoom("Brown", 4, 8),
                new MeetingRoom("Red", 3, 7),
                new MeetingRoom("White", 9, 5),
                new MeetingRoom("Yellow", 6, 8));
        MariaDbDataSource dataSource;
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");
        } catch (SQLException e) {
            throw new IllegalStateException("Can not create connect", e);
        }
        jdbcTemplate = new JdbcTemplate(dataSource);
        meetingRoom = new MariaDbMeetingRooms(jdbcTemplate);
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();
        for (MeetingRoom room : rooms) {
            meetingRoom.saveMeetingRoom(room);
        }
    }

    @Test
    void saveMeetingRoom() {
    }

    @Test
    void listNames() {
    }

    @Test
    void listNamesReverse() {
    }

    @Test
    void listEverySecondMeetingRoom() {
    }

    @Test
    void listMeetingRoomBySize() {
    }

    @Test
    void searchByName() {
    }

    @Test
    void searchByNamePart() {
    }

    @Test
    void searchBySize() {
    }

    @Test
    void deleteMeetingRoom() {
    }
}
