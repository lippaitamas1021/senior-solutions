package meetingrooms;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MariaDbMeetingRooms implements MeetingRoomRepository {

    private JdbcTemplate jdbcTemplate;

    public MariaDbMeetingRooms(MariaDbDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public MariaDbMeetingRooms(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveMeetingRoom(MeetingRoom meetingRoom) {
        jdbcTemplate.update("INSERT INTO meetingRooms(room_name, room_width, room_length, room_area) VALUES (?, ?, ?, ?)",
                meetingRoom.getName(), meetingRoom.getWidth(), meetingRoom.getLength(), meetingRoom.getWidth()*meetingRoom.getLength());
    }

    @Override
    public List<MeetingRoom> listNames() {
        return jdbcTemplate.query("SELECT id, room_name, room_length, room_width, room_area FROM meetingRooms ORDER BY room_name",
                (resultSet, i) -> new MeetingRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("room_name"),
                        resultSet.getDouble("room_length"),
                        resultSet.getDouble("room_width"),
                        resultSet.getDouble("room_area")));
    }

    @Override
    public List<MeetingRoom> listNamesReverse() {
        List<MeetingRoom> result = listNames();
        Collections.reverse(result);
        return result;
    }

    @Override
    public List<MeetingRoom> listEverySecondMeetingRoom() {
        List<MeetingRoom> result = new ArrayList<>();
        jdbcTemplate.query("SELECT id, room_name, room_length, room_width, room_area FROM meetingRooms ORDER BY room_name",
                (resultSet, i) -> {
                    MeetingRoom meetingRoom = new MeetingRoom(
                            resultSet.getInt("id"),
                            resultSet.getString("room_name"),
                            resultSet.getDouble("room_length"),
                            resultSet.getDouble("room_width"),
                            resultSet.getDouble("room_area"));
                    if (i % 2 == 1) {
                        result.add(meetingRoom);
                    }
                    throw new IllegalArgumentException("No match found");
                });
        return result;
    }

    @Override
    public List<MeetingRoom> listMeetingRoomBySize() {
        return jdbcTemplate.query("SELECT * FROM meetingrooms ORDER BY room_area",
                (resultSet, i) -> new MeetingRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("room_name"),
                        resultSet.getDouble("room_length"),
                        resultSet.getDouble("room_width"),
                        resultSet.getDouble("room_area")));
    }

    @Override
    public List<MeetingRoom> searchByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name can not stay null");
        }
        return jdbcTemplate.query("SELECT * FROM meetingRooms WHERE room_name like ?",
                (resultSet, i) -> new MeetingRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("room_name"),
                        resultSet.getDouble("room_length"),
                        resultSet.getDouble("room_width"),
                        resultSet.getDouble("room_area")),
                name);
    }

    @Override
    public List<MeetingRoom> searchByNamePart(String namePart) {
        if (namePart == null || namePart.isEmpty()) {
            throw new IllegalArgumentException("Please give the part of the name");
        }
        return jdbcTemplate.query("SELECT * FROM meetingRooms WHERE room_name like ?",
                (resultSet, i) -> new MeetingRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("room_name"),
                        resultSet.getDouble("room_length"),
                        resultSet.getDouble("room_width"),
                        resultSet.getDouble("room_area")),
                "%" + namePart + "%");
    }

    @Override
    public List<MeetingRoom> searchBySize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size can not be 0");
        }
        return jdbcTemplate.query("SELECT * FROM meetingRooms WHERE room_area > ?",
                (resultSet, i) -> new MeetingRoom(
                        resultSet.getInt("id"),
                        resultSet.getString("room_name"),
                        resultSet.getDouble("room_length"),
                        resultSet.getDouble("room_width"),
                        resultSet.getDouble("room_area")),
                size);
    }

    @Override
    public void deleteMeetingRoom(String name) {
        jdbcTemplate.update("DELETE * FROM meetingRooms WHERE room_name = name");
    }
}
