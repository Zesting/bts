package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoardOffer;

public interface UserBoardOfferRepository {
    public Integer offerCount(Integer userBoardOfferCount);
    public Optional<UserBoardOffer> findById(Long id);
    public List<UserBoardOffer> findAll();
    public UserBoardOffer save(UserBoardOffer userBoardOffer);
    public Optional<UserBoardOffer> delete(Long id);
    public Long findIdByFields(Long memberId,Long userBoardId);
    
}
  