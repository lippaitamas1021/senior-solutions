package employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

    private Long id;
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
