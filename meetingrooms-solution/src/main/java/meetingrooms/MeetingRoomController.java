package meetingrooms;

import java.util.Scanner;

public class MeetingRoomController {

    private Scanner scanner = new Scanner(System.in);

    private MeetingRoomService meetingRoomService =
            new MeetingRoomService(new InMemoryMeetingRooms());

    //    private MeetingRoomService meetingRoomService =
//            new MeetingRoomService(new MariaDbMeetingRooms(new MariaDbDataSource()));

    public static void main(String[] args) {
        new MeetingRoomController().start();
    }

    private void start() {
        System.out.println("Kérem, válasszon az alábbi menüpontok közül:");
        System.out.println("0. Tárgyaló rögzítése\n" +
                "1. Tárgyalók névsorrendben\n" +
                "2. Tárgyalók név alapján visszafele sorrendben\n" +
                "3. Minden második tárgyaló\n" +
                "4. Területek\n" +
                "5. Keresés pontos név alapján\n" +
                "6. Keresés névtöredék alapján\n" +
                "7. Keresés terület alapján\n" +
                "8. Kilépés");
        System.out.println("Kérem, adja meg a választott menü számát:");
        int choosenMenu = scanner.nextInt();
        if (choosenMenu == 0) {
            System.out.println("Kérem, adja meg a rögzítendő terem nevét:");
            String name = scanner.next();
            System.out.println("Kérem, adja meg a rögzítendő terem szélességét:");
            double width = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Kérem, adja meg a rögzítendő terem hosszúságát:");
            double length = scanner.nextDouble();
            scanner.nextLine();
            MeetingRoom meetingRoom = new MeetingRoom(1, name, length, width, length*width);
            meetingRoomService.saveMeetingRoom(meetingRoom);
            System.out.println(meetingRoomService.listNames());
        } else if (choosenMenu == 1) {
            meetingRoomService.listNames();
        } else if (choosenMenu == 2) {
            meetingRoomService.listNamesReverse();
        } else if (choosenMenu == 3) {
            meetingRoomService.listEverySecondMeetingRoom();
        } else if (choosenMenu == 4) {
            meetingRoomService.listMeetingRoomSize();
        } else if (choosenMenu == 5) {
            System.out.println("Kérem, adja meg a keresett terem nevét:");
            String name = scanner.nextLine();
            meetingRoomService.searchByName(name);
        } else if (choosenMenu == 6) {
            System.out.println("Kérem, adja meg a keresett terem nevének töredékét:");
            String namePart = scanner.nextLine();
            meetingRoomService.searchByNamePart(namePart);
        } else if (choosenMenu == 7) {
            System.out.println("Kérem, adja meg a keresett területet:");
            int roomSize = scanner.nextInt();
            meetingRoomService.searchBySize(roomSize);
        } else {
            scanner.close();
        }
    }
}
