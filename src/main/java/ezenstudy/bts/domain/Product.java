package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private String category;
    
}
