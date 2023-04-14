package ezenstudy.bts.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import ezenstudy.bts.domain.Product;
import ezenstudy.bts.repository.ProductRepository;

public class ProductService {
    
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //상품등록
    public Long register(Product product){
        return productRepository.save(product).getId();
    }
    public Optional<Product> findOnebyId(Long id){
        return productRepository.findbyId(id);
    }
    public List<Product> findProducts(){
        return productRepository.findAll();
    }
    //카테고리 set
    public Set<String> findCategory(){
        Set<String> category = new HashSet<>();
        productRepository.findAll().forEach(p -> category.add(p.getCategory()));
        return category;
    }

}
