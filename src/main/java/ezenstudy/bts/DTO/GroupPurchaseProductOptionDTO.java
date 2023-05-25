package ezenstudy.bts.DTO;

import lombok.Data;

@Data
public class GroupPurchaseProductOptionDTO {
    Long id;
    Long groupPurchaseId;
    Long productId;
    String optionColor;
    Integer optionSize;
    Integer quantity;
    Integer price;
}
