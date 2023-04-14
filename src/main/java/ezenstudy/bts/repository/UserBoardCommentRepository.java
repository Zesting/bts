package ezenstudy.bts.repository;

import ezenstudy.bts.domain.UserBoardComment;

public interface UserBoardCommentRepository {
   //댓글 CRUD
   UserBoardComment save(UserBoardComment comment);//댓글 작성
   UserBoardComment update(UserBoardComment comment);//댓글 수정
   void delete(UserBoardComment comment);//댓글 삭제
}
