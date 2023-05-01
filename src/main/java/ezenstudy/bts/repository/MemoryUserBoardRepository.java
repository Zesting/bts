package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoard;

public class MemoryUserBoardRepository implements UserBoardRepository {

  private static Map<Long, UserBoard> store = new HashMap<>();
  private static long sequence = 0L;   //sequence와 같다.

  @Override
  public UserBoard save(UserBoard userBoard) {
    userBoard.setUserBoardNumber(++sequence);
    store.put(userBoard.getUserBoardNumber(),userBoard);
    return userBoard;
  }

  @Override
  public UserBoard update(UserBoard userBoard) {    
    return store.put(userBoard.getUserBoardNumber(), userBoard);
  } 
 
  @Override
  public Optional<UserBoard> delete(Long id) {
    return Optional.ofNullable(store.remove(id));
  }
 
  @Override
  public List<UserBoard> listAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public Optional<UserBoard> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<UserBoard> findBoardOne(Long userBoardNumber) {
    return Optional.ofNullable((store.get(userBoardNumber)));
  }

  
}
