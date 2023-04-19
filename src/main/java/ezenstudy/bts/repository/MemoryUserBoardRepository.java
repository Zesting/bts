package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoard;

public class MemoryUserBoardRepository implements UserBoardRepository {

  private static Map<Long, UserBoard> store = new HashMap<>();
  private static long userBoardNumber = 0L;   //sequence와 같다.

  @Override
  public UserBoard save(UserBoard board) {
    board.setUserBoardNumber(++userBoardNumber);
    store.put(board.getUserBoardNumber(),board);
    System.out.println(board); //터미널에서 데이터가 입력되는지 확인하기 위한 프린트문
    return board;
  }

  @Override
  public UserBoard update(UserBoard board) {
    UserBoard newBoard = store.get(board.getUserBoardNumber());//객체 가져와서 저장
    newBoard.setUserBoardTitle(board.getUserBoardTitle());//변경하여 저장
    newBoard.setUserBoardContent(board.getUserBoardContent());//변경하여 저장
    return newBoard;//변경된 객체 반환
  } //확인 필요

  @Override
  public  Optional<UserBoard> delete(UserBoard board) {
    Long board1 = board.getUserBoardNumber();
    UserBoard removeUserBoard = store.remove(board1);
    return Optional.ofNullable(removeUserBoard);
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
  public Optional<UserBoard> findBynewProductID(String newProductID) {
    return store.values().stream()
    .filter(board ->board.getNewProductID().equals(newProductID)).findAny();
  }
  
}
