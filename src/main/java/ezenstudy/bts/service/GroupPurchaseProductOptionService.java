package ezenstudy.bts.service;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.GroupPurchaseProductOption;
import ezenstudy.bts.repository.GroupPurchaseProductOptionRepository;

public class GroupPurchaseProductOptionService {

    private final GroupPurchaseProductOptionRepository groupPurchaseProductOptionRepository;

    public GroupPurchaseProductOptionService(
            GroupPurchaseProductOptionRepository groupPurchaseProductOptionRepository) {
        this.groupPurchaseProductOptionRepository = groupPurchaseProductOptionRepository;
    }

    public Long register(GroupPurchaseProductOption groupPurchaseProductOption){
        return groupPurchaseProductOptionRepository.save(groupPurchaseProductOption).getId();
    }
    public Optional<GroupPurchaseProductOption> findOnebyId(Long id){
        return groupPurchaseProductOptionRepository.findbyId(id);
    }
    public List<GroupPurchaseProductOption> findGroupPurchases(){
        return groupPurchaseProductOptionRepository.findAll();
    }
    public List<GroupPurchaseProductOption> findListbyGroupPurchaseId(Long groupPurchaseId){
        return groupPurchaseProductOptionRepository.findbyGroupPurchaseId(groupPurchaseId);
    }
    public List<GroupPurchaseProductOption> findListbyProductOptionId(Long productOptionId){
        return groupPurchaseProductOptionRepository.findbyProductOptionId(productOptionId);
    }
    public Long remove(Long id){
        return groupPurchaseProductOptionRepository.delete(id);
    }
    public Optional<GroupPurchaseProductOption> modify(Long id, GroupPurchaseProductOption newGPPO){
        return groupPurchaseProductOptionRepository.modify(id, newGPPO);
    }
    
}
