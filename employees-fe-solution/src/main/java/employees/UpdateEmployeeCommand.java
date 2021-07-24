package employees;

import lombok.Data;

@Data
public class UpdateEmployeeCommand {

    @IsValidName
    private String name;
}
