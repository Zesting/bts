package ezenstudy.bts.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserBoardComment {
  private Long commentID;         // 댓글 ID (댓글 순서)
  private Long postID;            // 게시글 번호(외래키)
  private String commentContent;  // 댓글 내용
  private String commentWriter;   //댓글 작성자
  private LocalDateTime commentDate;       //댓글 작성일
  private LocalDateTime updateDate;        //댓글 수정일

}
