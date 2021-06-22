package meetingrooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryMeetingRoomsTest {

    InMemoryMeetingRooms room1;
    InMemoryMeetingRooms room2;
    List<MeetingRoom> rooms = new ArrayList<>();

    @BeforeEach
    void init(){
        rooms = Arrays.asList(
                new MeetingRoom("Grey",5,4),
                new MeetingRoom("Green",6,3),
                new MeetingRoom("Blue",4,5),
                new MeetingRoom("Black",7,6));
        room1 = new InMemoryMeetingRooms();
        room2 = new InMemoryMeetingRooms(rooms);
    }

    @Test
    void saveMeetingRoomTest() {
        for (MeetingRoom room : rooms) {
            room1.saveMeetingRoom(room);
        }
        room1.saveMeetingRoom(null);
        List<MeetingRoom> result = room1.getMeetingRooms();
        assertEquals(4, result.size());
    }

    @Test
    void listNamesTest() {
        List<MeetingRoom> result = room2.listNames();
        assertEquals(4, result.size());
        assertEquals("Black",result.get(0).getName());
        assertEquals("Grey",result.get(3).getName());
    }

    @Test
    void listNamesReverseTest() {
        List<MeetingRoom> result = room2.listNamesReverse();
        assertEquals(4,result.size());
        assertEquals("Blue",result.get(2).getName());
        assertEquals("Green",result.get(1).getName());
    }

    @Test
    void listEverySecondMeetingRoomTest() {
        List<MeetingRoom> result = room2.listEverySecondMeetingRoom();
        assertEquals(2,result.size());
        assertEquals("Grey",result.get(0).getName());
        assertEquals("Blue",result.get(1).getName());
    }

    @Test
    void listMeetingRoomBySizeTest() {
        List<MeetingRoom> result = room2.listMeetingRoomBySize();
        assertEquals(4, result.size());
        assertEquals("Green", result.get(2).getName());
        assertEquals(20, result.get(3).countArea());
    }

    @Test
    void searchByNameTest() {
        List<MeetingRoom> result = room2.searchByName("Grey");
        assertEquals("Grey", result.get(0).getName());
    }

    @Test
    void searchByNameThrowsExceptionTest() {
        assertThrows(IllegalArgumentException.class,()-> room2.searchByName(null));
        assertThrows(IllegalArgumentException.class,()-> room2.searchByName(""));
        assertThrows(IllegalArgumentException.class,()-> room2.searchByName("Brown"));
    }

    @Test
    void searchByNamePartTest() {
        assertEquals(1, room2.searchByNamePart("ue").size());
        assertEquals(2, room2.searchByNamePart("l").size());
    }

    @Test
    void searchByByNamePartNullTest() {
        assertThrows(IllegalArgumentException.class, () -> room2.searchByNamePart(null));
    }

    @Test
    void searchBySizeTest() {
        assertEquals(4, room2.searchBySize(0).size());
        List<MeetingRoom> meetingRoom = room2.searchBySize(20);
        assertEquals(1, meetingRoom.size());
    }
}
