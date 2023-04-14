package ezenstudy.bts.service;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.ProductImage;
import ezenstudy.bts.repository.ProductImageRepository;

public class ProductImageService {
    
    private ProductImageRepository productImageRepository;

    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }
    
    public Long register(ProductImage productOption){
        return productImageRepository.save(productOption).getId();
    }
    public Optional<ProductImage> findOnebyId(Long id){
        return productImageRepository.findbyId(id);
    }
    public List<ProductImage> findProductImages(){
        return productImageRepository.findAll();
    }
    public List<ProductImage> findListbyProductId(Long productId){
        return productImageRepository.findbyProductId(productId);
    }
}
