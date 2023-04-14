package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.ProductOption;

public interface ProductOptionRepository {
    public ProductOption save(ProductOption productOption);
    public Optional<ProductOption> findbyId(Long id);
    public List<ProductOption> findAll();
    public List<ProductOption> findbyProductId(Long productId);
}
