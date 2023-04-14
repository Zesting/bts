package ezenstudy.bts.domain;

import java.sql.Date;

public class UserBoardComment {
  private String commentWriter;
  private Date commentDate;

  public Date getCommentDate() {
    return this.commentDate;
  }

  public void setCommentDate(Date commentDate) {
    this.commentDate = commentDate;
  }

  public String getCommentWriter() {
    return this.commentWriter;
  }

  public void setCommentWriter(String commentWriter) {
    this.commentWriter = commentWriter;
  }
  
}
