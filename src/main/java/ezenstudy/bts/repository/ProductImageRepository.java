package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.ProductImage;

public interface ProductImageRepository {
    public ProductImage save(ProductImage productImage);
    public Optional<ProductImage> findbyId(Long id);
    public List<ProductImage> findAll();
    public List<ProductImage> findbyProductId(Long productId);
    public Long storageSize();
}
