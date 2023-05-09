package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.ReviewBoard;

public interface ReviewBoardRepository {
    List<ReviewBoard> findAll();                   // 전체확인
    ReviewBoard save(ReviewBoard board);           // 저장기능
    Optional<ReviewBoard> findById(Long id);       // 아이디로 객체 불러오기
    ReviewBoard delete(Long id);                   // 삭제
    ReviewBoard update(ReviewBoard newBoard);      // 수정
    Integer findAllSize();                         // 리뷰 전체 수
    Long reviewNum();                              //save boardId
}
