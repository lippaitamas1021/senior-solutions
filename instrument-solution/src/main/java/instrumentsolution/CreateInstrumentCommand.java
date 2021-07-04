package instrumentsolution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInstrumentCommand {

    @NotBlank(message = "Name can not stay blank")
    private String brand;

    private InstrumentType type;

    @Positive
    private int price;
}
