package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeesServiceTest {

    EmployeesService employeesService = new EmployeesService(new InMemoryEmployeesRepository());

    @BeforeEach
    void init() {
        employeesService.deleteAll();
    }

    @Test
    void saveThenListTest() {
        employeesService.save("John Doe");
        List<Employee> employees = employeesService.listEmployees();
        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
    }

    @Test
    void saveTwoThenListTest() {
        employeesService.save("John Doe");
        employeesService.save("Jane Doe");
        List<Employee> employees = employeesService.listEmployees();
        assertEquals(2, employees.size());
        assertEquals("Jane Doe", employees.get(0).getName());
        assertEquals("John Doe", employees.get(1).getName());
    }
}
