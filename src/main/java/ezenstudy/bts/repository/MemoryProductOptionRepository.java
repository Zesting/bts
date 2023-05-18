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

        ProductOption po6 = new ProductOption();
        po6.setProductId(3l);
        po6.setColor("black");
        po6.setSize(250);
        save(po6);

        ProductOption po7 = new ProductOption();
        po7.setProductId(3l);
        po7.setColor("black");
        po7.setSize(255);
        save(po7);

        ProductOption po8 = new ProductOption();
        po8.setProductId(3l);
        po8.setColor("black");
        po8.setSize(260);
        save(po8);

        ProductOption po9 = new ProductOption();
        po9.setProductId(3l);
        po9.setColor("black");
        po9.setSize(265);
        save(po9);

        ProductOption po10 = new ProductOption();
        po10.setProductId(3l);
        po10.setColor("black");
        po10.setSize(270);
        save(po10);

        ProductOption po11 = new ProductOption();
        po11.setProductId(3l);
        po11.setColor("black");
        po11.setSize(275);
        save(po11);
        
        ProductOption po12 = new ProductOption();
        po12.setProductId(3l);
        po12.setColor("black");
        po12.setSize(280);
        save(po12);

        ProductOption po13 = new ProductOption();
        po13.setProductId(3l);
        po13.setColor("gray");
        po13.setSize(270);
        save(po13);

        ProductOption po14 = new ProductOption();
        po14.setProductId(3l);
        po14.setColor("gray");
        po14.setSize(280);
        save(po14);

        ProductOption po15 = new ProductOption();
        po15.setProductId(4l);
        po15.setColor("black");
        po15.setSize(260);
        save(po15);

        ProductOption po16 = new ProductOption();
        po16.setProductId(4l);
        po16.setColor("black");
        po16.setSize(270);
        save(po16);

        ProductOption po17 = new ProductOption();
        po17.setProductId(4l);
        po17.setColor("black");
        po17.setSize(280);
        save(po17);

        ProductOption po18 = new ProductOption();
        po18.setProductId(5l);
        po18.setColor("purple");
        po18.setSize(220);
        save(po18);

        ProductOption po19 = new ProductOption();
        po19.setProductId(5l);
        po19.setColor("purple");
        po19.setSize(230);
        save(po19);

        ProductOption po20 = new ProductOption();
        po20.setProductId(5l);
        po20.setColor("purple");
        po20.setSize(240);
        save(po20);

        ProductOption po21 = new ProductOption();
        po21.setProductId(5l);
        po21.setColor("purple");
        po21.setSize(250);
        save(po21);

        ProductOption po22 = new ProductOption();
        po22.setProductId(5l);
        po22.setColor("yellow");
        po22.setSize(220);
        save(po22);

        ProductOption po23 = new ProductOption();
        po23.setProductId(5l);
        po23.setColor("yellow");
        po23.setSize(230);
        save(po23);

        ProductOption po24 = new ProductOption();
        po24.setProductId(5l);
        po24.setColor("yellow");
        po24.setSize(240);
        save(po24);

        ProductOption po25 = new ProductOption();
        po25.setProductId(5l);
        po25.setColor("yellow");
        po25.setSize(250);
        save(po25);
    }
}
