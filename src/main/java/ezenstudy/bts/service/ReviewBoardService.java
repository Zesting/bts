package ezenstudy.bts.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ezenstudy.bts.domain.ReviewBoard;
import ezenstudy.bts.repository.ReviewBoardRepository;

@Service
public class ReviewBoardService {
    private final ReviewBoardRepository boardRepository;

    public ReviewBoardService(ReviewBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void save(ReviewBoard reviewBoard) {
        // 작성날짜
        reviewBoard.setCDate(LocalDateTime.now());
        boardRepository.save(reviewBoard);

    }

    public void update(ReviewBoard reviewBoard) {
        Long id = reviewBoard.getId();
        //업데이트시 cDate에 기존 값 다시넣어주기 (객체를 새로만들어 덮어씌우기 떄문에)
        reviewBoard.setCDate(boardRepository.findById(id).get().getCDate());
        reviewBoard.setUDate(LocalDateTime.now());
        boardRepository.update(reviewBoard);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public List<ReviewBoard> findAll() {
        return boardRepository.findAll();
    }

    public Optional<ReviewBoard> findOne(Long boardId) {
        return boardRepository.findById(boardId);
    }

    //조회수 증가
    public Optional<ReviewBoard> updateCount(Long id) {
        ReviewBoard reviewBoard = boardRepository.findById(id).get();
        if (reviewBoard.getViewCount() == null) {
            reviewBoard.setViewCount(0);
        } else {
            reviewBoard.setViewCount(reviewBoard.getViewCount() + 1);
        }
        return Optional.ofNullable(reviewBoard);
    }

    //상품별 평균 별점
    public double starAverage(Long productId){  
        return boardRepository.findAll().stream().
        filter(pi->pi.getProductId()== productId).
        mapToDouble(ReviewBoard::getStar).average().getAsDouble();
    }

    //리뷰 총 수
    public Integer reviewSize(){
        return boardRepository.findAllSize();
    }

    //save boardId
    public Long reviewBoardNum() {
        return boardRepository.reviewNum();
    }

}
