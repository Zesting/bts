package ezenstudy.bts.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DropMemberForm {

    @NotBlank
    private String name;

    @NotBlank
    private String logId;

    @NotBlank
    private String logPwd;
}
