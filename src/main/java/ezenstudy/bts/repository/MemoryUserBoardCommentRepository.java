package ezenstudy.bts.repository;

import java.time.LocalDateTime;
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
    return store.put(comment.getId(), comment);
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
        .filter(comment -> comment.getMemberId() == memberId)
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
        .filter(comment -> comment.getBoardId() == boardId)
        .toList();
  }

  /* =============== 댓글 더미 데이터 ===================== */
  public MemoryUserBoardCommentRepository() {
    UserBoardComment uc0 = new UserBoardComment();
    uc0.setBoardId(1L);
    uc0.setId(1L);
    uc0.setMemberId(1L);
    uc0.setCommentContent("좋아요.");
    uc0.setCommentDate(LocalDateTime.now());
    saveComment(uc0);

    UserBoardComment uc1 = new UserBoardComment();
    uc1.setBoardId(1L);
    uc1.setId(2L);
    uc1.setMemberId(4L);
    uc1.setCommentContent("저도 사고싶어요.");
    uc1.setCommentDate(LocalDateTime.now());
    saveComment(uc1);

    UserBoardComment uc2 = new UserBoardComment();
    uc2.setBoardId(1L);
    uc2.setId(3L);
    uc2.setMemberId(3L);
    uc2.setCommentContent("제발~~");
    uc2.setCommentDate(LocalDateTime.now());
    saveComment(uc2);

    UserBoardComment uc3 = new UserBoardComment();
    uc3.setBoardId(2L);
    uc3.setId(4L);
    uc3.setMemberId(8L);
    uc3.setCommentContent("드디어 올라왔!!!!!!");
    uc3.setCommentDate(LocalDateTime.now());
    saveComment(uc3);

    UserBoardComment uc4 = new UserBoardComment();
    uc4.setBoardId(2L);
    uc4.setId(5L);
    uc4.setMemberId(7L);
    uc4.setCommentContent("댓글1 입니다.");
    uc4.setCommentDate(LocalDateTime.now());
    saveComment(uc4);

    UserBoardComment uc5 = new UserBoardComment();
    uc5.setBoardId(2L);
    uc5.setId(6L);
    uc5.setMemberId(888L);
    uc5.setCommentContent("댓글2 입니다.");
    uc5.setCommentDate(LocalDateTime.now());
    saveComment(uc5);

    UserBoardComment uc6 = new UserBoardComment();
    uc6.setBoardId(2L);
    uc6.setId(7L);
    uc6.setMemberId(9L);
    uc6.setCommentContent("댓글3 입니다.");
    uc6.setCommentDate(LocalDateTime.now());
    saveComment(uc6);

    UserBoardComment uc7 = new UserBoardComment();
    uc7.setBoardId(1L);
    uc7.setId(8L);
    uc7.setMemberId(4L);
    uc7.setCommentContent("댓글4 입니다.");
    uc7.setCommentDate(LocalDateTime.now());
    saveComment(uc7);

    UserBoardComment uc8 = new UserBoardComment();
    uc8.setBoardId(3L);
    uc8.setId(9L);
    uc8.setMemberId(1L);
    uc8.setCommentContent("댓글1 입니다.");
    uc8.setCommentDate(LocalDateTime.now());
    saveComment(uc8);

    UserBoardComment uc9 = new UserBoardComment();
    uc9.setBoardId(4L);
    uc9.setId(10L);
    uc9.setMemberId(1L);
    uc9.setCommentContent("댓글2 입니다.");
    uc9.setCommentDate(LocalDateTime.now());
    saveComment(uc9);

    UserBoardComment uc10 = new UserBoardComment();
    uc10.setBoardId(3L);
    uc10.setId(11L);
    uc10.setMemberId(2L);
    uc10.setCommentContent("댓글3 입니다.");
    uc10.setCommentDate(LocalDateTime.now());
    saveComment(uc10);

    UserBoardComment uc11 = new UserBoardComment();
    uc11.setBoardId(6L);
    uc11.setId(12L);
    uc11.setMemberId(4L);
    uc11.setCommentContent("댓글4 입니다.");
    uc11.setCommentDate(LocalDateTime.now());
    saveComment(uc11);

    UserBoardComment uc12 = new UserBoardComment();
    uc12.setBoardId(3L);
    uc12.setId(13L);
    uc12.setMemberId(3L);
    uc12.setCommentContent("댓글3 입니다.");
    uc12.setCommentDate(LocalDateTime.now());
    saveComment(uc12);

    UserBoardComment uc13 = new UserBoardComment();
    uc13.setBoardId(5L);
    uc13.setId(14L);
    uc13.setMemberId(8L);
    uc13.setCommentContent("댓글4 입니다.");
    uc13.setCommentDate(LocalDateTime.now());
    saveComment(uc13);

    UserBoardComment uc14 = new UserBoardComment();
    uc14.setBoardId(5L);
    uc14.setId(15L);
    uc14.setMemberId(5L);
    uc14.setCommentContent("댓글5 입니다.");
    uc14.setCommentDate(LocalDateTime.now());
    saveComment(uc14);

    UserBoardComment uc15 = new UserBoardComment();
    uc15.setBoardId(7L);
    uc15.setId(16L);
    uc15.setMemberId(6L);
    uc15.setCommentContent("댓글6 입니다.");
    uc15.setCommentDate(LocalDateTime.now());
    saveComment(uc15);

    UserBoardComment uc16 = new UserBoardComment();
    uc16.setBoardId(4L);
    uc16.setId(17L);
    uc16.setMemberId(7L);
    uc16.setCommentContent("댓글7 입니다.");
    uc16.setCommentDate(LocalDateTime.now());
    saveComment(uc16);

    UserBoardComment uc17 = new UserBoardComment();
    uc17.setBoardId(3L);
    uc17.setId(18L);
    uc17.setMemberId(8L);
    uc17.setCommentContent("댓글5 입니다.");
    uc17.setCommentDate(LocalDateTime.now());
    saveComment(uc17);

    UserBoardComment uc18 = new UserBoardComment();
    uc18.setBoardId(7L);
    uc18.setId(19L);
    uc18.setMemberId(9L);
    uc18.setCommentContent("댓글6 입니다.");
    uc18.setCommentDate(LocalDateTime.now());
    saveComment(uc18);

    UserBoardComment uc19 = new UserBoardComment();
    uc19.setBoardId(6L);
    uc19.setId(20L);
    uc19.setMemberId(10L);
    uc19.setCommentContent("댓글7 입니다.");
    uc19.setCommentDate(LocalDateTime.now());
    saveComment(uc19);

    // BoardId별 댓글 수량 설정 (각각 3개씩)
    int[] commentCounts = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };

    // 댓글 생성 반복문
    int currentId = 50; // 시작 Id
    int memberId = 1; // 시작 MemberId
    for (int boardId = 1; boardId <= 10; boardId++) {
      int commentCount = commentCounts[boardId - 1]; // 해당 BoardId의 댓글 수량

      for (int i = 0; i < commentCount; i++) {
        UserBoardComment comment = new UserBoardComment();
        comment.setBoardId((long) boardId);
        comment.setId((long) currentId);
        comment.setMemberId((long) memberId);
        comment.setCommentContent("댓글" + (i + 1) + " 입니다.");
        comment.setCommentDate(LocalDateTime.now());
        saveComment(comment);

        currentId++; // 다음 Id로 증가
        memberId = (memberId % 10) + 1; // MemberId 변경 (1 ~ 10까지 순환)
      }
    }//자동반복문
  }//댓글더미 //public MemoryUserBoardCommentRepository()

}
