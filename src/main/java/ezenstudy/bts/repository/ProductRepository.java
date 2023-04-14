package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.Product;

public interface ProductRepository {
    public Product save(Product product);
    public Optional<Product> findbyId(Long id);
    public List<Product> findAll();
}
