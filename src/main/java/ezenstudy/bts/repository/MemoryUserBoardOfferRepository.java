package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoardOffer;

public class MemoryUserBoardOfferRepository implements UserBoardOfferRepository{
    private static Map<Long, UserBoardOffer> store = new HashMap<>();
    private static long sequence = 0L;   //sequence와 같다.
  

    @Override
    public List<UserBoardOffer> findAll() {
    return new ArrayList<>(store.values());
    }

    @Override
    public Optional<UserBoardOffer> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Integer offerCount(Integer userBoardOfferCount) {
        //사용안해서 삭제
        return userBoardOfferCount;
    }

    @Override
    public UserBoardOffer save(UserBoardOffer userBoardOffer) {
        userBoardOffer.setId(++sequence);
        store.put(userBoardOffer.getId(), userBoardOffer);
        return userBoardOffer;
    }

    @Override
    public Optional<UserBoardOffer> delete(Long id) {
       return Optional.ofNullable(store.remove(id));
    }

    @Override
    public Long findIdByFields(Long memberId, Long userBoardId) {
        Optional<UserBoardOffer> target = store.values().stream()
        .filter(offer -> offer.getMemberId() == memberId 
        && offer.getUserBoardId() == userBoardId).findAny();
        if(target.isPresent()){
            return target.get().getId();
        }
        return -1l;
    }

    
      
}
