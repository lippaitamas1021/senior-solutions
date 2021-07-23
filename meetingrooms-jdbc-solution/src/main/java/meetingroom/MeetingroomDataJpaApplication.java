package meetingroom;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class MeetingroomDataJpaApplication implements CommandLineRunner {

    private final MeetingRooms meetingRooms;

    public static void main(String[] args) {
        SpringApplication.run(MeetingroomDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello Spring Boot!");

        meetingRooms.save(new MeetingRoom("Jupiter"));
        meetingRooms.save(new MeetingRoom("Uranus"));
        meetingRooms.save(new MeetingRoom("Pluto"));

        System.out.println(meetingRooms.findAll());


        System.out.println(meetingRooms.findAllByNameLike("Ju%"));
    }
}
