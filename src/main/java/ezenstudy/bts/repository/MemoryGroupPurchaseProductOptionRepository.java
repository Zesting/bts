package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ezenstudy.bts.domain.GroupPurchaseProductOption;

public class MemoryGroupPurchaseProductOptionRepository implements GroupPurchaseProductOptionRepository{

    Map<Long,GroupPurchaseProductOption> store = new HashMap<>();
    private static long sequence = 0l;

    @Override
    public GroupPurchaseProductOption save(GroupPurchaseProductOption groupPurchaseProductOption) {
        groupPurchaseProductOption.setId(++sequence);
        store.put(groupPurchaseProductOption.getId(),groupPurchaseProductOption);
        return groupPurchaseProductOption;
    }

    @Override
    public Optional<GroupPurchaseProductOption> findbyId(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<GroupPurchaseProductOption> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<GroupPurchaseProductOption> findbyGroupPurchaseId(Long id) {
        return store.values().stream().filter(gppo -> gppo.getGroupPurchaseId() == id)
        .collect(Collectors.toList());
    }

    @Override
    public List<GroupPurchaseProductOption> findbyProductOptionId(Long id) {
        return store.values().stream().filter(gppo -> gppo.getProductOptionId() == id)
        .collect(Collectors.toList());
    }

    @Override
    public Long delete(Long id) {
        store.remove(id);
        return id;
    }

    @Override
    public Optional<GroupPurchaseProductOption> modify(Long id, GroupPurchaseProductOption newGPPO) {
        newGPPO.setId(id);
        store.put(newGPPO.getId(),newGPPO);
        return Optional.ofNullable(store.get(id));
    }
    
}
