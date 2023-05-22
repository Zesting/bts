package ezenstudy.bts.domain;

import ezenstudy.bts.DTO.GroupPurchaseProductOptionDTO;
import lombok.Data;

@Data
public class GroupPurchaseProductOption {
    private Long id;
    private Long groupPurchaseId;
    private Long productOptionId;
    private Integer quantity;

    public GroupPurchaseProductOptionDTO transferToDTO(){
        GroupPurchaseProductOptionDTO dto = new GroupPurchaseProductOptionDTO();
        dto.setGroupPurchaseId(groupPurchaseId);
        dto.setQuantity(quantity);
        return dto;
    }
}
