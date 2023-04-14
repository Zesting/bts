package ezenstudy.bts.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ezenstudy.bts.domain.GroupPurchase;

public class MemoryGroupPurchaseRepository implements GroupPurchaseRepository{
    
    private Map<Long, GroupPurchase> store = new HashMap<>();
    private static long sequence = 0l;

    @Override
    public GroupPurchase save(GroupPurchase groupPurchase) {
        groupPurchase.setId(++sequence);
        store.put(groupPurchase.getId(), groupPurchase);
        return groupPurchase;
    }
    @Override
    public Optional<GroupPurchase> findbyId(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public List<GroupPurchase> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public List<GroupPurchase> findbyProductId(Long productId) {
        return store.values().stream().filter(gp -> gp.getId() == productId).collect(Collectors.toList());
    }
    @Override
    public Long delete(Long id) {
        store.remove(id);
        return id;
    }
    @Override
    public Optional<GroupPurchase> modify(Long id, GroupPurchase groupPurchase) {
        groupPurchase.setId(id);
        store.put(groupPurchase.getId(),groupPurchase);
        return Optional.of(groupPurchase);
    }
    //더미파일
    public MemoryGroupPurchaseRepository(){
        GroupPurchase gp1 = new GroupPurchase();
        gp1.setProductId(1l);
        gp1.setInformation("나이키의 신상 농구화 판매개시");
        gp1.setSaleStart(LocalDateTime.of(2023, 4, 1, 15, 0, 0));
        gp1.setSaleEnd(LocalDateTime.of(2023, 5, 1, 0, 0, 0));
        gp1.setMinQuantity(10);
        gp1.setSaleRequestId(32l);
        save(gp1);
        GroupPurchase gp2 = new GroupPurchase();
        gp2.setProductId(2l);
        gp2.setInformation("봄맞이 닥터마틴 단 10일간 특가판매이벤트");
        gp2.setSaleStart(LocalDateTime.of(2023, 4, 7, 12, 0, 0));
        gp2.setSaleEnd(LocalDateTime.of(2023, 4, 17, 12, 0, 0));
        gp2.setMinQuantity(20);
        gp2.setSaleRequestId(157l);
        save(gp2);
    }
}
