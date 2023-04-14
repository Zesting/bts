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
    //더미데이터 생성자
    public MemoryProductRepository(){
        Product p1 = new Product();
        p1.setBrand("nike");
        p1.setName("나이키 덩크 로우 레트로 SE");
        p1.setCategory("운동화");
        p1.setDescription("하드우드 코트를 위해 태어나 스트리트로 무대를 옮겨온 80년대의 농구 아이콘이 자수 디테일, 대조적인 스티치와 레트로 농구 스타일로 돌아옵니다. 패딩 처리된 로우컷 카라가 어디서든 편안한 발걸음을 이끌어 줍니다.");
        save(p1);

        Product p2 = new Product();
        p2.setBrand("drmartens");
        p2.setName("마일즈");
        p2.setCategory("샌들");
        save(p2);
        
    }
}
