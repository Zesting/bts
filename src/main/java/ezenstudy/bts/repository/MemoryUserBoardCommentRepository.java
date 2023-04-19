package ezenstudy.bts.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoardComment;
import ezenstudy.bts.service.UserBoardService;

public class MemoryUserBoardCommentRepository implements UserBoardCommentRepository {
  private static Map<String, UserBoardComment> store = new HashMap<>();
  private static long CommentID = 0L;   //sequence와 같다.
  UserBoardService userBoardService;

  @Override
  public UserBoardComment saveComment(UserBoardComment comment) {
    comment.setCommentID(++CommentID);
    store.put(comment.getCommentWriter(), comment);
    comment.setCommentDate(userBoardService.getUserBoardDateTime());//userBoardService 시간 저장메서드 호출
    System.out.println(comment);//데이터 저장확인 
    return comment;
  }

  @Override
  public UserBoardComment updateComment(UserBoardComment comment) {
    UserBoardComment boardComment = store.get(comment.getCommentWriter());
    boardComment.setCommentContent(comment.getCommentContent());//댓글 수정
    boardComment.setCommentDate(comment.getCommentDate());//작성시간을 불러와서 새로운 시간으로 업뎃
    return boardComment;
  }

  @Override
  public Optional<UserBoardComment> delete(UserBoardComment comment) {
    UserBoardComment deleteComment = store.remove(comment.getCommentWriter());
    return Optional.ofNullable(deleteComment);
  }

  @Override
  public List<UserBoardComment> getCommentListByPostId(Long postId) {//게시글 별 댓글 리스트 조회

    return null;
  }

  @Override
  public List<UserBoardComment> getCommentListByWriter(String writerName) {//댓글 작성자 별 댓글 리스트 조회

    return null;
  }
  
}
