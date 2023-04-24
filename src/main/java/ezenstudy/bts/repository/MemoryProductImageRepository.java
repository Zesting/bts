package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ezenstudy.bts.domain.ProductImage;

public class MemoryProductImageRepository implements ProductImageRepository{

    private Map<Long,ProductImage> store = new HashMap<>();
    private static long sequence = 0l;

    @Override
    public ProductImage save(ProductImage productImage) {
        productImage.setId(++sequence);
        store.put(productImage.getId(), productImage);
        return productImage;
    }
    @Override
    public Optional<ProductImage> findbyId(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public List<ProductImage> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public List<ProductImage> findbyProductId(Long productId) {
        return store.values().stream().filter(po -> po.getId() == productId).collect(Collectors.toList());
    }
    @Override
    public Long storageSize(){
        return sequence;
    }
    //더미파일
    public MemoryProductImageRepository(){
        ProductImage pi1 = new ProductImage();
        pi1.setColor("black");
        pi1.setImagePath("/images/나이키덩크로우레트로블랙1.jpg");
        pi1.setProductId(1l);
        save(pi1);
        ProductImage pi2 = new ProductImage();
        pi2.setColor("black");
        pi2.setImagePath("/images/닥터마틴마일즈1.jpg");
        pi2.setProductId(2l);
        save(pi2);
    }
}
