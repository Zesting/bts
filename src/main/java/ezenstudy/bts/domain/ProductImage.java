package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class ProductImage {
    
    private Long id;
    private Long productId;
    private String color;
    private String imagePath;
}
