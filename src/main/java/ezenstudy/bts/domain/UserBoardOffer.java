package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class UserBoardOffer {
         //유저별 1번만 추천 >> true / false로 
         //유저 추천 유무 검증  >> 
         //테이블을 따로 아이디/ 상품/ 상품 추천 유무 3가지 
    private Integer offerCount;  // 상품 추천수 
    private String memberId; //회원 아이디
    private String productId;   //상품 아이디
 
}
 