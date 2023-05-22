package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoardOffer;

public class MemoryUserBoardOfferRepository implements UserBoardOfferRepository{
    private static Map<Long, UserBoardOffer> store = new HashMap<>();
    // private static long sequence = 0L;   //sequence와 같다.
  

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
        //offerCount가 트루일때만 카운팅해주는 조건문 필요
        return userBoardOfferCount;
    }

    @Override
    public UserBoardOffer save(UserBoardOffer userBoardOffer) {
        //정보들 저장할 것 넣기
        return userBoardOffer;
    }

    @Override
    public Optional<UserBoardOffer> delete(Long memberId) {
       return null;
    }

    
      
}
