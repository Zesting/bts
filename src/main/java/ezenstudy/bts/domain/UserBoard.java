package ezenstudy.bts.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserBoard {
  private long userBoardNumber;   //게시물번호
  private String member_ID;       //회원ID
  private String newProductID;    //상품명
  private String writer;          //작성자
  private LocalDateTime userBoardDateTime;//작성일
  private long offerCount;        //상품 추천수
  private String userBoardTitle;      //게시물 제목
  private String userBoardContent;    //게시물 본문
  private String category;        //카테고리


  public UserBoard() {
    // 기본 생성자 구현
  }


  public UserBoard(String userBoardTitle, String userBoardContent) {
    this.userBoardTitle = userBoardTitle;
    this.userBoardContent = userBoardContent;
  }

}
