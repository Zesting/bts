package ezenstudy.bts.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoard;
import ezenstudy.bts.repository.UserBoardRepository;

public class UserBoardService {
  private final UserBoardRepository userBoardRepository;

  public UserBoardService(UserBoardRepository userBoardRepository) {
    this.userBoardRepository = userBoardRepository;
  }
//타이틀이랑 컨텐츠를 무조건 받기위해서 만들었었으나 필터링을 자바스크립트에서 하기로 결정하여 필요없어짐
//   public String save(String userBoardTitle, String userBoardContent) {
//     UserBoard userBoard = new UserBoard(userBoardTitle, userBoardContent);
//     userBoardRepository.save(userBoard);
//     return userBoard.getUserBoardTitle();
// }

  // 게시판 생성
  public UserBoard save(UserBoard userBoard) {
    return userBoardRepository.save(userBoard);
  }

  // 게시판 조회
  public List<UserBoard> findUserBoard() {
    return userBoardRepository.listAll();
  }

  // 1개 찾기
  public Optional<UserBoard> findOne(Long member_ID) {
    return userBoardRepository.findById(member_ID);
  }

  // 게시판 수정
  public void update(UserBoard board) {
    userBoardRepository.update(board);
  }

  // 게시판 삭제
  public void delete(UserBoard board) {
    userBoardRepository.delete(board);
  }

  //날짜와 시간 포멧팅(DataTimeFormatter)
  public LocalDateTime getUserBoardDateTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String writeDateTime = now.format(formatter);
    return LocalDateTime.parse(writeDateTime, formatter);
  }
}
