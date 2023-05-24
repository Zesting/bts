package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name; // 상품명
    private String brand;
    private String category;
    private String bannerImagePath;
    private Integer originalPrice;
}
