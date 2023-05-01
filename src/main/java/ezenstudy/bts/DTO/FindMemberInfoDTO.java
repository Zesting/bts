package ezenstudy.bts.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindMemberInfoDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String socialNum;

    private String logId;

}
