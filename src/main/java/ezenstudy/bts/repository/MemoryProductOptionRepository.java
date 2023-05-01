package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ezenstudy.bts.domain.ProductOption;

public class MemoryProductOptionRepository implements ProductOptionRepository{

    private Map<Long,ProductOption> store = new HashMap<>();
    private static long sequence = 0l;

    @Override
    public ProductOption save(ProductOption productOption) {
        productOption.setId(++sequence);
        store.put(productOption.getId(), productOption);
        return productOption;
    }

    @Override
    public Optional<ProductOption> findbyId(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<ProductOption> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<ProductOption> findbyProductId(Long productId) {
        return store.values().stream().filter(po -> po.getProductId() == productId).collect(Collectors.toList());
    }
    //더미파일
    public MemoryProductOptionRepository(){
        ProductOption po1 = new ProductOption();
        po1.setProductId(1l);
        po1.setColor("black");
        po1.setSize(260);
        save(po1);
        ProductOption po2 = new ProductOption();
        po2.setProductId(1l);
        po2.setColor("black");
        po2.setSize(270);
        save(po2);
        ProductOption po3 = new ProductOption();
        po3.setProductId(1l);
        po3.setColor("black");
        po3.setSize(280);
        save(po3);
        ProductOption po4 = new ProductOption();
        po4.setProductId(2l);
        po4.setColor("black");
        po4.setSize(270);
        save(po4);
        ProductOption po5 = new ProductOption();
        po5.setProductId(2l);
        po5.setColor("black");
        po5.setSize(290);
        save(po5);
    }
    
}
