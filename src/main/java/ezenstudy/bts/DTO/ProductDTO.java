package ezenstudy.bts.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.domain.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private String product_name;
    private String product_category;
    private String product_brand;
    private String product_description;
    private List<MultipartFile> product_images;

    public Product convertToDomain(){
        Product p = new Product();
        p.setName(product_name);
        p.setCategory(product_category);
        p.setBrand(product_brand);
        p.setDescription(product_description);
        return p;
    }
}
