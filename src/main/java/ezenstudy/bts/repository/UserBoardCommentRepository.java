package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoardComment;

public interface UserBoardCommentRepository {
   //댓글 CRUD 
   UserBoardComment saveComment(UserBoardComment comment);//댓글 작성
   UserBoardComment updateComment(UserBoardComment comment);//댓글 수정
   Optional<UserBoardComment> delete(UserBoardComment comment);//댓글 삭제
   //게시글 별 댓글 리스트 조회
   List<UserBoardComment> getCommentListByPostId(Long postId);
   //댓글 작성자 별 댓글 리스트 조회
   List<UserBoardComment> getCommentListByWriter(String writerName);
}
