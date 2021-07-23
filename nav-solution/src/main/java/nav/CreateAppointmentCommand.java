package nav;

import lombok.Data;

@Data
@IsValidCreateAppointmentCommand
public class CreateAppointmentCommand {

    @IsValidTaxNumber
    private String taxnumber;

    @IsValidInterval
    private Interval interval;

    @IsValidType
    private String typeCode;
}
