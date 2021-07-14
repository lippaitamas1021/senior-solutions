//package locations;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.zalando.problem.Problem;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class LocationsControllerIT {
//
//    @Autowired
//    TestRestTemplate template;
//
//    @Test
//    void testValidation() {
//      Problem problem = template.postForObject("/locations",
//                new HttpEntity<>(new CreateLocationCommand("")),
//                Problem.class);
//        assertEquals("Location not found", problem.getTitle());
//    }
//}
