package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoardComment;

public class MemoryUserBoardCommentRepository implements UserBoardCommentRepository {
  private static Map<Long, UserBoardComment> store = new HashMap<>();
  private static long sequence = 0L;   

  @Override
  public UserBoardComment saveComment(UserBoardComment comment) {
    comment.setId(++sequence);
    store.put(comment.getId(), comment);  
    return comment;
  }

  @Override
  public UserBoardComment updateComment(UserBoardComment comment) { 
    return store.put(comment.getId(),comment);
  }
 
  @Override
  public Optional<UserBoardComment> delete(Long commentId) {
    return Optional.ofNullable(store.remove(commentId));
  }

  @Override
  public Optional<UserBoardComment> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public List<UserBoardComment> findByMemberId(Long memberId) {
    return store.values()
          .stream()
          .filter(comment -> comment.getMemberId()== memberId)
          .toList();
  }
  
  @Override
  public List<UserBoardComment> listAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public List<UserBoardComment> findbyBoardId(Long boardId) {
    
    return store.values()
          .stream()
          .filter(comment -> comment.getBoardId()== boardId)
          .toList();
  }

  



 
}
