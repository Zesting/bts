package ezenstudy.bts.repository;

import java.time.LocalDateTime;
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

  /* =============== 게시물 더미 데이터 ===================== */
  public MemoryUserBoardRepository() {
    UserBoard u0 = new UserBoard();
    u0.setUserBoardNumber(1);
    u0.setUserBoardTitle("제목1 입니다.");
    u0.setUserBoardContent("내용1 입니다.");
    u0.setMemberId(1);
    u0.setDateTime(LocalDateTime.now());
    u0.setUpdateDateTime(LocalDateTime.now());
    u0.setOfferCount(0);
    save(u0);

    UserBoard u1 = new UserBoard();
    u1.setUserBoardNumber(2);
    u1.setUserBoardTitle("제목2 입니다.");
    u1.setUserBoardContent("내용2 입니다.");
    u1.setMemberId(2);
    u1.setDateTime(LocalDateTime.now());
    u1.setUpdateDateTime(LocalDateTime.now());
    u1.setOfferCount(0);
    save(u1);

    UserBoard u2 = new UserBoard();
    u2.setUserBoardNumber(3);
    u2.setUserBoardTitle("제목3 입니다.");
    u2.setUserBoardContent("내용3 입니다.");
    u2.setMemberId(3);
    u2.setDateTime(LocalDateTime.now());
    u2.setUpdateDateTime(LocalDateTime.now());
    u2.setOfferCount(0);
    save(u2);

    UserBoard u3 = new UserBoard();
    u3.setUserBoardNumber(4);
    u3.setUserBoardTitle("제목4 입니다.");
    u3.setUserBoardContent("내용4 입니다.");
    u3.setMemberId(4);
    u3.setDateTime(LocalDateTime.now());
    u3.setUpdateDateTime(LocalDateTime.now());
    u3.setOfferCount(0);
    save(u3);
  }






}
