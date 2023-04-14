package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class ProductOption {
    private Long id;
    private Long productId;
    private Integer size;
    private String color;
    private Integer quantity;
    private Integer price;
}
