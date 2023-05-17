package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String brand;
    private String category;
    private String bannerImagePath;
    private Integer originalPrice;
}
