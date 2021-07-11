package locations;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationsControllerRestAssuredIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocationsService service;

    @BeforeEach
    void init() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.requestSpecification =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON);
        service.deleteAllLocations();
    }

    @Test
    void testListLocations() {
        RestAssuredMockMvc
                .with()
                .body(new CreateLocationCommand(33, "Tunyogmatolcs", 47.85617, 19.16739))
                .post("/api/locations")
                .then()
                .statusCode(201)
                .body("name", equalTo("Tunyogmatolcs"))
                .log();
                with()
                .get("/api/locations")
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("Tunyogmatolcs"))
                .body("size()", equalTo(1))
                .log();
    }

    @Test
    void testFindLocationById() {
        RestAssuredMockMvc.with()
                .body(new CreateLocationCommand(44, "Anarcs", 47.36715, 19.58931))
                .post("/api/locations")
                .then()
                .statusCode(201);
        with()
                .body(new CreateLocationCommand(55, "Gemzse", 47.11679, 19.23269))
                .post("/api/locations")
                .then()
                .statusCode(201);
        with()
                .get("/api/locations/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("Gemzse"));
    }

    @Test
    void testCreateLocation() {
        RestAssuredMockMvc.with()
                .body(new CreateLocationCommand(33, "Tunyogmatolcs", 47.85617, 19.16739))
                .post("/api/locations")
                .then()
                .statusCode(201);

        with()
                .get("/api/locations")
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("Tunyogmatolcs"))
                .body("size()", equalTo(1));
    }

    @Test
    void testUpdateLocation() {
        RestAssuredMockMvc.with()
                .body(new CreateLocationCommand("Kisvárda", 47.15793, 19.51800))
                .post("/api/locations")
                .then()
                .statusCode(201);
        with()
                .body(new UpdateLocationCommand("Záhony", 47.81576, 19.78125))
                .put("/api/locations/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Záhony"))
                .body("lat", equalTo(47.81576F))
                .body("lon", equalTo(19.78125F));
    }

    @Test
    void testDeleteLocation() {
        RestAssuredMockMvc.with()
                .body(new CreateLocationCommand("Kék", 47.24975, 19.41732))
                .post("/api/locations")
                .then()
                .statusCode(201);

        with()
                .delete("/api/locations/1")
                .then()
                .statusCode(204);

        with()
                .get("/api/locations")
                .then()
                .statusCode(200)
                .body("size()", equalTo(0));
    }

    @Test
    void validate() {
        with()
                .body(new CreateLocationCommand("Ilk", 47.13497, 19.37510))
                .post("/api/locations")
                .then()
                .body(matchesJsonSchemaInClasspath("locationDto.json"));
    }
}
