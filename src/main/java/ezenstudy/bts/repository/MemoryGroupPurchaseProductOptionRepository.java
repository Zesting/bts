package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ezenstudy.bts.domain.GroupPurchaseProductOption;

public class MemoryGroupPurchaseProductOptionRepository implements GroupPurchaseProductOptionRepository {

    Map<Long, GroupPurchaseProductOption> store = new HashMap<>();
    private static long sequence = 0l;

    @Override
    public GroupPurchaseProductOption save(GroupPurchaseProductOption groupPurchaseProductOption) {
        groupPurchaseProductOption.setId(++sequence);
        store.put(groupPurchaseProductOption.getId(), groupPurchaseProductOption);
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
        store.put(newGPPO.getId(), newGPPO);
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Long amountDecrement(Long id) {
        GroupPurchaseProductOption gppo = store.get(id);
        gppo.setQuantity(gppo.getQuantity() - 1);
        store.put(id, gppo);
        return id;
    };

    public MemoryGroupPurchaseProductOptionRepository() {
        GroupPurchaseProductOption gppo1 = new GroupPurchaseProductOption();
        gppo1.setGroupPurchaseId(1l);
        gppo1.setProductOptionId(1l);
        gppo1.setQuantity(76);
        save(gppo1);

        GroupPurchaseProductOption gppo2 = new GroupPurchaseProductOption();
        gppo2.setGroupPurchaseId(1l);
        gppo2.setProductOptionId(2l);
        gppo2.setQuantity(152);
        save(gppo2);

        GroupPurchaseProductOption gppo3 = new GroupPurchaseProductOption();
        gppo3.setGroupPurchaseId(1l);
        gppo3.setProductOptionId(3l);
        gppo3.setQuantity(134);
        save(gppo3);

        GroupPurchaseProductOption gppo4 = new GroupPurchaseProductOption();
        gppo4.setGroupPurchaseId(2l);
        gppo4.setProductOptionId(4l);
        gppo4.setQuantity(34);
        save(gppo4);

        GroupPurchaseProductOption gppo5 = new GroupPurchaseProductOption();
        gppo5.setGroupPurchaseId(2l);
        gppo5.setProductOptionId(5l);
        gppo5.setQuantity(34);
        save(gppo5);
        // --
        GroupPurchaseProductOption gppo6 = new GroupPurchaseProductOption();
        gppo6.setGroupPurchaseId(3l);
        gppo6.setProductOptionId(6l);
        gppo6.setQuantity(31);
        save(gppo6);

        GroupPurchaseProductOption gppo7 = new GroupPurchaseProductOption();
        gppo7.setGroupPurchaseId(3l);
        gppo7.setProductOptionId(7l);
        gppo7.setQuantity(35);
        save(gppo7);

        GroupPurchaseProductOption gppo8 = new GroupPurchaseProductOption();
        gppo8.setGroupPurchaseId(3l);
        gppo8.setProductOptionId(8l);
        gppo8.setQuantity(42);
        save(gppo8);

        GroupPurchaseProductOption gppo9 = new GroupPurchaseProductOption();
        gppo9.setGroupPurchaseId(3l);
        gppo9.setProductOptionId(9l);
        gppo9.setQuantity(32);
        save(gppo9);

        GroupPurchaseProductOption gppo10 = new GroupPurchaseProductOption();
        gppo10.setGroupPurchaseId(3l);
        gppo10.setProductOptionId(10l);
        gppo10.setQuantity(37);
        save(gppo10);

        GroupPurchaseProductOption gppo11 = new GroupPurchaseProductOption();
        gppo11.setGroupPurchaseId(3l);
        gppo11.setProductOptionId(11l);
        gppo11.setQuantity(21);
        save(gppo11);

        GroupPurchaseProductOption gppo12 = new GroupPurchaseProductOption();
        gppo12.setGroupPurchaseId(3l);
        gppo12.setProductOptionId(12l);
        gppo12.setQuantity(2);
        save(gppo12);

        GroupPurchaseProductOption gppo13 = new GroupPurchaseProductOption();
        gppo13.setGroupPurchaseId(3l);
        gppo13.setProductOptionId(13l);
        gppo13.setQuantity(76);
        save(gppo13);

        GroupPurchaseProductOption gppo14 = new GroupPurchaseProductOption();
        gppo14.setGroupPurchaseId(2l);
        gppo14.setProductOptionId(5l);
        gppo14.setQuantity(342);
        save(gppo14);
    }
}
