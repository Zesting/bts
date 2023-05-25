package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class UserBoardOffer {
    private Long id;
         //유저별 1번만 추천 >> true / false로 
         //유저 추천 유무 검증  >> 
         //테이블을 따로 아이디/ 상품/ 상품 추천 유무 3가지 
    // private Byte offer;  // 상품 추천수 
    private Long memberId; //회원 아이디
    private Long userBoardId;   //상품 아이디
    
    //하트를 눌렀을때만 데이터 생성 , 해제하면 삭제
  //리스트 맵으로 꺼내기

}
 