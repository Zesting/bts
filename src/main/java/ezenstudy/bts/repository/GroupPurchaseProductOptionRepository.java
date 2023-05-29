package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.GroupPurchaseProductOption;

public interface GroupPurchaseProductOptionRepository {

    public GroupPurchaseProductOption save(GroupPurchaseProductOption groupPurchaseProductOption);

    public Optional<GroupPurchaseProductOption> findbyId(Long id);

    public List<GroupPurchaseProductOption> findAll();

    public List<GroupPurchaseProductOption> findbyGroupPurchaseId(Long id);

    public List<GroupPurchaseProductOption> findbyProductOptionId(Long id);

    public Long delete(Long id);

    public Optional<GroupPurchaseProductOption> modify(Long id, GroupPurchaseProductOption newGPPO);

    public Long amountDecrement(Long id);
}