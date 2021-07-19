package nav;

import lombok.Data;
import java.time.LocalDateTime;

@Data
@IsValidCreateAppointmentCommand
public class CreateAppointmentCommand {

    @IsValidTaxNumber
    private String taxnumber;

//    @IsValidInterval
    private Interval interval;

    @IsValidType
    private String typeCode;
}
