package meetingrooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MeetingRoomServiceTest {

    InMemoryMeetingRooms inMemoryMeetingRooms = new InMemoryMeetingRooms();

    @BeforeEach
    void setUp() {
        inMemoryMeetingRooms.saveMeetingRoom(new MeetingRoom("Grey", 5, 4));
        inMemoryMeetingRooms.saveMeetingRoom(new MeetingRoom("Green", 6, 3));
        inMemoryMeetingRooms.saveMeetingRoom(new MeetingRoom("Blue", 4, 5));
        inMemoryMeetingRooms.saveMeetingRoom(new MeetingRoom("Black", 7, 6));
    }

    @Test
    void saveMeetingRoomTest() {
        assertEquals(4, inMemoryMeetingRooms.listNames().size());
    }

    @Test
    void listNamesTest() {
        assertEquals("Black", inMemoryMeetingRooms.listNames().get(0).getName());
    }

    @Test
    void listNamesReverseTest() {
        assertEquals("Grey", inMemoryMeetingRooms.listNamesReverse().get(0).getName());
    }

    @Test
    void listEverySecondMeetingRoomTest() {
        assertEquals(2, inMemoryMeetingRooms.listEverySecondMeetingRoom().size());
    }

    @Test
    void listMeetingRoomSizeTest() {
        assertEquals(42, inMemoryMeetingRooms.listMeetingRoomBySize().get(0).countArea());
    }

    @Test
    void searchByNameTest() {
        assertEquals("Green", inMemoryMeetingRooms.searchByName("Green").get(0).getName());
    }

    @Test
    void searchByNamePartTest() {
        assertEquals("Green", inMemoryMeetingRooms.searchByNamePart("ee").get(0).getName());
        assertEquals("Blue", inMemoryMeetingRooms.searchByNamePart("lu").get(0).getName());
        assertEquals("Black", inMemoryMeetingRooms.searchByNamePart("ac").get(0).getName());
    }

    @Test
    void searchBySize() {
        assertEquals("Black", inMemoryMeetingRooms.searchBySize(40).get(0).getName());
    }

    @Test
    void deleteMeetingRoom() {
        inMemoryMeetingRooms.deleteMeetingRoom("Grey");
        assertEquals(3, inMemoryMeetingRooms.listNames().size());
    }
}
