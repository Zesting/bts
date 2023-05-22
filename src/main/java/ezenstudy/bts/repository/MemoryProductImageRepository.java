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
        return store.values().stream().filter(po -> po.getProductId() == productId).collect(Collectors.toList());
    }
    @Override
    public Long storageSize(){
        return sequence;
    }
    //더미파일
    public MemoryProductImageRepository(){
        ProductImage pi1 = new ProductImage();
        pi1.setColor("black");
        pi1.setImagePath("/images/product/나이키덩크로우레트로블랙1.jpg");
        pi1.setProductId(1l);
        save(pi1);
        ProductImage pi2 = new ProductImage();
        pi2.setColor("black");
        pi2.setImagePath("/images/product/닥터마틴마일즈1.jpg");
        pi2.setProductId(2l);
        save(pi2);

        ProductImage pi3 = new ProductImage();
        pi3.setColor("black");
        pi3.setImagePath("/images/product/나이키에어베이퍼맥스4.jpg");
        pi3.setProductId(3l);
        save(pi3);

        ProductImage pi4 = new ProductImage();
        pi4.setColor("black");
        pi4.setImagePath("/images/product/나이키에어베이퍼맥스5.jpg");
        pi4.setProductId(3l);
        save(pi4);

        ProductImage pi5 = new ProductImage();
        pi5.setColor("black");
        pi5.setImagePath("/images/product/나이키에어베이퍼맥스6.jpg");
        pi5.setProductId(3l);
        save(pi5);

        ProductImage pi6 = new ProductImage();
        pi6.setColor("white");
        pi6.setImagePath("/images/product/나이키에어베이퍼맥스1.jpg");
        pi6.setProductId(3l);
        save(pi6);

        ProductImage pi7 = new ProductImage();
        pi7.setColor("white");
        pi7.setImagePath("/images/product/나이키에어베이퍼맥스2.jpg");
        pi7.setProductId(3l);
        save(pi7);

        ProductImage pi8 = new ProductImage();
        pi8.setColor("white");
        pi8.setImagePath("/images/product/나이키에어베이퍼맥스3.jpg");
        pi8.setProductId(3l);
        save(pi8);

        ProductImage pi9 = new ProductImage();
        pi9.setColor("black");
        pi9.setImagePath("/images/product/아리조나에바블랙1.jpg");
        pi9.setProductId(4l);
        save(pi9);

        ProductImage pi10 = new ProductImage();
        pi10.setColor("purple");
        pi10.setImagePath("/images/product/에어조던1엘리베이트하이1.jpg");
        pi10.setProductId(5l);
        save(pi10);

        ProductImage pi11 = new ProductImage();
        pi11.setColor("purple");
        pi11.setImagePath("/images/product/에어조던1엘리베이트하이2.jpg");
        pi11.setProductId(5l);
        save(pi11);

        ProductImage pi12 = new ProductImage();
        pi12.setColor("purple");
        pi12.setImagePath("/images/product/에어조던1엘리베이트하이3.jpg");
        pi12.setProductId(5l);
        save(pi12);

        ProductImage pi13 = new ProductImage();
        pi13.setColor("yellow");
        pi13.setImagePath("/images/product/에어조던1엘리베이트하이4.jpg");
        pi13.setProductId(5l);
        save(pi13);

        ProductImage pi14 = new ProductImage();
        pi14.setColor("yellow");
        pi14.setImagePath("/images/product/에어조던1엘리베이트하이5.jpg");
        pi14.setProductId(5l);
        save(pi14);

        ProductImage pi15 = new ProductImage();
        pi15.setColor("yellow");
        pi15.setImagePath("/images/product/에어조던1엘리베이트하이6.jpg");
        pi15.setProductId(5l);
        save(pi15);
    }
}
