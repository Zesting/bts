package ezenstudy.bts.service;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.ProductOption;
import ezenstudy.bts.repository.ProductOptionRepository;

public class ProductOptionService {
    
    private ProductOptionRepository productOptionRepository;

    public ProductOptionService(ProductOptionRepository productOptionRepository) {
        this.productOptionRepository = productOptionRepository;
    }
    
    public Long register(ProductOption productOption){
        return productOptionRepository.save(productOption).getId();
    }
    public Optional<ProductOption> findOnebyId(Long id){
        return productOptionRepository.findbyId(id);
    }
    public List<ProductOption> findProductOptions(){
        return productOptionRepository.findAll();
    }
    public List<ProductOption> findListbyProductId(Long productId){
        return productOptionRepository.findbyProductId(productId);
    }

    
}
