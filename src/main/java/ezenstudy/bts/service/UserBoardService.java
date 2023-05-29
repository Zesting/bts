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

  public UserBoardService() {
    this.userBoardRepository= null;
  }

  // 게시판 등록
  public UserBoard save(UserBoard userBoard) {//넘버/ 제목/ 내용/ 멤버아이디/ 작성시간/수정시간/ 추천수
    userBoard.setDateTime(FormDateTime());//작성 시간
    return userBoardRepository.save(userBoard);
  }

  // 게시판 조회
  public List<UserBoard> findUserBoard() {
    return userBoardRepository.listAll();
  }

  // id로 1개 찾기
  public Optional<UserBoard> findOne(Long id) {
    return userBoardRepository.findById(id);
  }

  public Optional<UserBoard> findBoardOne(Long userBoardNumber){
    return userBoardRepository.findBoardOne(userBoardNumber);
  }

  // 게시판 수정
  public UserBoard update(UserBoard userBoard) {
    userBoard.setUpdateDateTime(FormDateTime());
    return userBoardRepository.update(userBoard);
  }

  // 게시판 삭제
  public Optional<UserBoard> delete(Long userBoardNumber) { //삭제할때 검증해서 본인꺼만 삭제 할 수 있는기능.
    return userBoardRepository.delete(userBoardNumber);  //아이디를 받아서 아이디랑 일치하면 삭제가능하게
  }

  //날짜와 시간 포멧팅(DataTimeFormatter)
  public LocalDateTime FormDateTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    return LocalDateTime.parse(now.format(formatter), formatter);
  }

 
}
 