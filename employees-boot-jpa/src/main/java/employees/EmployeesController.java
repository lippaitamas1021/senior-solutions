package employees;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeesController {

    private final EmployeesRepo employeesRepo;

    @PostMapping
    public void createEmployee(@RequestBody Employee employee) {
        employeesRepo.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> listEmployees() {
        return employeesRepo.listEmployees();
    }
}
