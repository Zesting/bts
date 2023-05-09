package ezenstudy.bts.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserBoardComment { //게시물에 회원들이 댓글 작성
  private Long id;         // 댓글 ID (댓글 순서)
  private Long boardId;            // 게시글 번호(외래키)
  private Long MemberId;   //댓글 작성자 (외래키) 멤버 아이디
  private String commentContent;  // 댓글 내용
  private LocalDateTime commentDate;       //댓글 작성일
} 
 