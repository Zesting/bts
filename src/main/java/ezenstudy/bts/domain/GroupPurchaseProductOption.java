package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class GroupPurchaseProductOption {
    private Long id;
    private Long groupPurchaseId;
    private Long productOptionId;
    private Integer quantity;
}
