package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoardOffer;

public interface UserBoardOfferRepository {
    public UserBoardOffer offerCount(UserBoardOffer userBoardOffer);
    public Optional<UserBoardOffer> findbyId(Long id);
    public List<UserBoardOffer> findAll();

}
