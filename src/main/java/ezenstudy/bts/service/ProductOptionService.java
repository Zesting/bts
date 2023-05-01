package ezenstudy.bts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import ezenstudy.bts.domain.ProductOption;
import ezenstudy.bts.repository.ProductOptionRepository;

public class ProductOptionService {

    private ProductOptionRepository productOptionRepository;

    public ProductOptionService(ProductOptionRepository productOptionRepository) {
        this.productOptionRepository = productOptionRepository;
    }

    public Long register(ProductOption productOption) {
        return productOptionRepository.save(productOption).getId();
    }

    public Optional<ProductOption> findOnebyId(Long id) {
        return productOptionRepository.findbyId(id);
    }

    public List<ProductOption> findProductOptions() {
        return productOptionRepository.findAll();
    }

    public List<ProductOption> findListbyProductId(Long productId) {
        return productOptionRepository.findbyProductId(productId);
    }
    public List<Integer> findSizesbyProductId(Long productId){
        Set<Integer> sizes = productOptionRepository.findbyProductId(productId)
        .stream().map(option -> option.getSize()).collect(Collectors.toSet());
        return new ArrayList<Integer>(sizes);
    }
    public List<String> findColorsbyProductId(Long productId){
        Set<String> colors = productOptionRepository.findbyProductId(productId)
        .stream().map(option -> option.getColor()).collect(Collectors.toSet());
        return new ArrayList<String>(colors);
    }

}
