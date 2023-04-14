package ezenstudy.bts.service;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.GroupPurchase;
import ezenstudy.bts.repository.GroupPurchaseRepository;

public class GroupPurchaseService {
    
    private GroupPurchaseRepository groupPurchaseRepository;

    public GroupPurchaseService(GroupPurchaseRepository groupPurchaseRepository) {
        this.groupPurchaseRepository = groupPurchaseRepository;
    }

    public Long register(GroupPurchase groupPurchase){
        return groupPurchaseRepository.save(groupPurchase).getId();
    }
    public Optional<GroupPurchase> findOnebyId(Long id){
        return groupPurchaseRepository.findbyId(id);
    }
    public List<GroupPurchase> findGroupPurchases(){
        return groupPurchaseRepository.findAll();
    }
    public List<GroupPurchase> findListbyProductId(Long productId){
        return groupPurchaseRepository.findbyProductId(productId);
    }
    public Long remove(Long id){
        return groupPurchaseRepository.delete(id);
    }
    public Optional<GroupPurchase> modify(Long id, GroupPurchase groupPurchase){
        return groupPurchaseRepository.modify(id, groupPurchase);
    }
}
