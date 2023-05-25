package ezenstudy.bts.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoardComment;
import ezenstudy.bts.repository.UserBoardCommentRepository;

public class UserBoardCommentService {
  private final UserBoardCommentRepository userBoardCommentRepository;

  public UserBoardCommentService(UserBoardCommentRepository userBoardCommentRepository) {
    this.userBoardCommentRepository = userBoardCommentRepository;
  }

  // 댓글 등록
  public UserBoardComment saveComment(UserBoardComment comment) {
    comment.setCommentDate(FormDateTime());
    return userBoardCommentRepository.saveComment(comment);
  }

  // 댓글 삭제
  public void delete(Long commentId) {
    userBoardCommentRepository.delete(commentId); // 아이디를 받아서 아이디랑 일치하면 삭제가능하게
  }

  // 아이디로 찾기
  public Optional<UserBoardComment> findById(Long id) {
    return userBoardCommentRepository.findById(id);
  }
  // 모든 댓글 조회
  public List<UserBoardComment> findAllUserBoardComment() {
    return userBoardCommentRepository.listAll();
  }

  // 댓글 조회 - 아이디
  public List<UserBoardComment> findListByMemberId(Long memberId) {
    List<UserBoardComment> list = userBoardCommentRepository.findByMemberId(memberId);
    if (list.size() == 0) {
      return null;
    }
    return list; // nul 값은 컨트롤러에서 받앗을경우를 처리해줘야한다.
  }
  public List<UserBoardComment> findListByBoardId(Long boardId) {
    List<UserBoardComment> list = userBoardCommentRepository.findbyBoardId(boardId);
    if (list.size() == 0) {
      return null;
    }
    return list; // nul 값은 컨트롤러에서 받앗을경우를 처리해줘야한다.
  }

  // 날짜와 시간 포멧팅(DataTimeFormatter)
  public LocalDateTime FormDateTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    return LocalDateTime.parse(now.format(formatter), formatter);
  }

  public Long update(UserBoardComment userBoardComment){
    UserBoardComment original = userBoardCommentRepository.findById(userBoardComment.getId()).get();
    userBoardComment.setBoardId(original.getBoardId());
    // userBoardComment.setCommentContent(userBoardComment.getCommentContent()); 
    userBoardComment.setMemberId(original.getMemberId());
    userBoardComment.setCommentDate(FormDateTime());
    userBoardCommentRepository.updateComment(userBoardComment);
    return userBoardComment.getId();
  }
}
