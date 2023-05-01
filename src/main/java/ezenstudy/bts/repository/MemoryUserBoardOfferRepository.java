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
    public Optional<UserBoardOffer> findbyId(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public UserBoardOffer offerCount(UserBoardOffer userBoardOffer) {
        userBoardOffer.setOfferCount(++sequence);
        store.put(userBoardOffer.getOfferCount(),userBoardOffer);
        return userBoardOffer;
    }
     
}
