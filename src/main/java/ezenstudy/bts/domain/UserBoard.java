package ezenstudy.bts.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserBoard { //오퍼의 리스트를 찾는 메서드 테이블을 따로 뺴면 레파지토리랑 서비스에 따로 있어야한다.
  private long userBoardNumber;   //게시물번호
  private long MemberId;       //회원ID
  private long wirter; //작성자(삭제할때 비교)
  private LocalDateTime DateTime;//작성일
  private LocalDateTime UpdateDateTime;//수정일
  private long offerCount;        //상품 추천수
  private String userBoardTitle;      //게시물 제목
  private String userBoardContent;    //게시물 본문
  private byte[] userBoardImg;  //이미지 
 
}
