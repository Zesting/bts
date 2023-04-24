package ezenstudy.bts.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindMemberInfo {

    @NotBlank
    private String name;

    @NotBlank
    private String logId;

    @NotBlank
    private String socialNum;
}
