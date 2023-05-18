package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.Product;

public class MemoryProductRepository implements ProductRepository{

    Map<Long,Product> store = new HashMap<>();
    private static long sequence = 0l;

    @Override
    public Product save(Product product) {
        product.setId(++sequence);
        store.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findbyId(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Long storageSize(){
        return sequence;
    }
    //더미데이터 생성자
    public MemoryProductRepository(){
        Product p1 = new Product();
        p1.setBrand("nike");
        p1.setName("나이키 덩크 로우 레트로 SE");
        p1.setCategory("러닝");
        p1.setBannerImagePath("/images/banner/나이키덩크로우레트로배너");
        p1.setOriginalPrice(129000);
        save(p1);

        Product p2 = new Product();
        p2.setBrand("drmartens");
        p2.setName("마일즈");
        p2.setCategory("샌들");
        p1.setBannerImagePath("");
        p1.setOriginalPrice(170000);
        save(p2);

        Product p3 = new Product();
        p3.setBrand("nike");
        p3.setName("에어 베이퍼맥스 2023 플라이니트");
        p3.setCategory("러닝");
        p3.setBannerImagePath("");
        p3.setOriginalPrice(249000);
        save(p3);

        Product p4 = new Product();
        p4.setBrand("BIRKENSTOCK");
        p4.setName("아리조나 에바 블랙 129421");
        p4.setCategory("슬리퍼");
        p4.setBannerImagePath("");
        p4.setOriginalPrice(59000);
        save(p4);

        Product p5 = new Product();
        p5.setBrand("nike");
        p5.setName("에어 조던 1 엘리베이트 하이");
        p5.setCategory("러닝");
        p5.setBannerImagePath("/images/banner/에어조던1엘리베이트하이배너.jpeg");
        p5.setOriginalPrice(179000);
        save(p5);

    //     Product p = new Product();
    //     p.setBrand("");
    //     p.setName("");
    //     p.setCategory("");
    //     p.setDescription("");
    //     p.setOriginalPrice(0);
    //     save(p);

    //     Product p = new Product();
    //     p.setBrand("");
    //     p.setName("");
    //     p.setCategory("");
    //     p.setDescription("");
    //     p.setOriginalPrice(0);
    //     save(p);

    //     Product p = new Product();
    //     p.setBrand("");
    //     p.setName("");
    //     p.setCategory("");
    //     p.setDescription("");
    //     p.setOriginalPrice(0);
    //     save(p);
    }
    
}
