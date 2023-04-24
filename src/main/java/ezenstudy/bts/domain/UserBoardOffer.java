package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class UserBoardOffer {
         //유저별 1번만 추천
         //유저 추천 유무 검증 
         //테이블을 따로 아이디/ 상품/ 상품 추천 유무 3가지
    private Long offerCount;  // 상품 추천수 
    private String id; //회원 아이디
    //private boolean isOfferCount; //상품 추천 유무  //클릭할때마다 true/fulse 

}
