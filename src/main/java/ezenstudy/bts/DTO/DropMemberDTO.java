package ezenstudy.bts.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DropMemberDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String logId;

    @NotBlank
    private String logPwd;
}
