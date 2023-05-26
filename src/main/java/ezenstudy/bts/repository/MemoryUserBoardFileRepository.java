package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoardFile;

public class MemoryUserBoardFileRepository implements UserBoardFileRepository{

  private static Map<Long, UserBoardFile> store = new HashMap<>();
  private static long sequence = 0L;   //sequence와 같다.

  @Override
  public List<UserBoardFile> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public Optional<UserBoardFile> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public UserBoardFile save(UserBoardFile userBoardFile) {
    userBoardFile.setUserBoardFileId(sequence++);
    store.put(userBoardFile.getUserBoardFileId(), userBoardFile);
    return null;
    
  }

  @Override
  public UserBoardFile update(UserBoardFile userBoardFile) {
    store.put(userBoardFile.getUserBoardFileId(), userBoardFile);
    return userBoardFile;

  }

  @Override
  public Optional<UserBoardFile> delete(Long id) {
    return Optional.ofNullable(store.remove(id));
  }

  
  
}
