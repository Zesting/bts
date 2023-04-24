package ezenstudy.bts.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogInForm {

    @NotBlank
    private String logId;

    @NotBlank
    private String logPwd;
}
