package ezenstudy.bts.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoard;

public class MemoryUserBoardRepository implements UserBoardRepository {

  private static Map<Long, UserBoard> store = new HashMap<>();
  private static long userBoardNumber = 0L;

  @Override
  public UserBoard save(UserBoard board) {
    board.setUserBoardNumber(++userBoardNumber);
    store.put(board.getUserBoardNumber(),board);
    return board;
  }

  @Override
  public UserBoard update(UserBoard board) {
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(UserBoard board) {
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public List<UserBoard> listAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listAll'");
  }

  @Override
  public Optional<UserBoard> findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }


  
}
