package ezenstudy.bts.service;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoardOffer;
import ezenstudy.bts.repository.UserBoardOfferRepository;

public class UserBoardOfferService {
  private final UserBoardOfferRepository userBoardOfferRepository;

  public UserBoardOfferService(UserBoardOfferRepository userBoardOfferRepository){
    this.userBoardOfferRepository = userBoardOfferRepository;
  }

  public UserBoardOffer save(UserBoardOffer userBoardOffer){
    return userBoardOfferRepository.save(userBoardOffer);
  }

  public Integer offerCount(Integer userBoardOfferCount){
    
    return userBoardOfferRepository.offerCount(userBoardOfferCount);
  }

  public Optional<UserBoardOffer> findById(Long memberId){
    return userBoardOfferRepository.findById(memberId);
  }

  public List<UserBoardOffer> findAll(){
    return userBoardOfferRepository.findAll();
  }
}
