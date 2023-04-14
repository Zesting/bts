package ezenstudy.bts.domain;

import java.time.LocalDateTime;

public class UserBoard {
  private long userBoardNumber;
  private String member_ID;
  private String newProductID;
  private String writer;
  private LocalDateTime boardDate;
  private long offerCount;
  private String boardTitle;
  private String boardContent;
  private String category;


  public long getUserBoardNumber() {
    return this.userBoardNumber;
  }

  public void setUserBoardNumber(long userBoardNumber) {
    this.userBoardNumber = userBoardNumber;
  }

  public String getMember_ID() {
    return this.member_ID;
  }

  public void setMember_ID(String member_ID) {
    this.member_ID = member_ID;
  }

  public String getNewProductName() {
    return this.newProductID;
  }

  public void setNewProductName(String newProductName) {
    this.newProductID = newProductName;
  }

  public String getWriter() {
    return this.writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public LocalDateTime getBoardDate() {
    return this.boardDate;
  }

  public void setBoardDate(LocalDateTime boardDate) {
    this.boardDate = boardDate;
  }

  public long getOfferCount() {
    return this.offerCount;
  }

  public void setOfferCount(int offerCount) {
    this.offerCount = offerCount;
  }

  public String getBoardTitle() {
    return this.boardTitle;
  }

  public void setBoardTitle(String boardTitle) {
    this.boardTitle = boardTitle;
  }

  public String getBoardContent() {
    return this.boardContent;
  }

  public void setBoardContent(String boardContent) {
    this.boardContent = boardContent;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

}
