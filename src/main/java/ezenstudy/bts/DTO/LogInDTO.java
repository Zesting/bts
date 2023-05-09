package ezenstudy.bts.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogInDTO {

    @NotBlank
    private String logId;

    @NotBlank
    private String logPwd;
}
