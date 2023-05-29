package ezenstudy.bts.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class mainGalleryDTO {
    private Long groupPurchaseId;
    private Integer originalPrice;
    private Integer salePrice;
    private String productName;
    private String information;
    private LocalDateTime saleEndTime;
    private String brand;
    private String category;
    private String imagePath;
    private Integer orderCount;
    private Integer minQuantity;
}
