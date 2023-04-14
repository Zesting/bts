package ezenstudy.bts.repository;


import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.GroupPurchase;

public interface GroupPurchaseRepository {
    public GroupPurchase save(GroupPurchase groupPurchase);
    public Optional<GroupPurchase> findbyId(Long id);
    public List<GroupPurchase> findAll();
    public List<GroupPurchase> findbyProductId(Long productId);
    public Long delete(Long id);
    public Optional<GroupPurchase> modify(Long id, GroupPurchase newGP);
}
