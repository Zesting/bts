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
    comment.setCommentID(++sequence);
    store.put(comment.getCommentID(), comment);  
    return comment;
  }

  @Override
  public UserBoardComment updateComment(UserBoardComment comment) { 
    return store.put(comment.getCommentID(),comment);
  }
 
  @Override
  public Optional<UserBoardComment> delete(Long id) {
    return Optional.ofNullable(store.remove(id));
  }

  @Override
  public Optional<UserBoardComment> findById(Long userBoardID) {
    return Optional.ofNullable(store.get(userBoardID));
  }

  @Override
  public List<UserBoardComment> findByMemberID(Long id) {
    return store.values()
          .stream()
          .filter(comment -> comment.getId() == id)
          .toList();
  }

  @Override
  public List<UserBoardComment> listAll() {
    return new ArrayList<>(store.values());
  }

  



 
}
