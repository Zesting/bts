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

    public ReviewBoardService(ReviewBoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public void save(ReviewBoard reviewBoard) throws Exception{
        //작성날짜
        reviewBoard.setCDate(LocalDateTime.now());

        
        boardRepository.save(reviewBoard);

    }

    public void update(ReviewBoard reviewBoard){
        Long id = reviewBoard.getId();
        reviewBoard.setCDate(boardRepository.findById(id).get().getCDate());
        reviewBoard.setUDate(LocalDateTime.now()); // 수정날짜
        boardRepository.update(reviewBoard);
    }

    public void delete(Long id){
        boardRepository.delete(id);
    }

    public List<ReviewBoard> findAll(){
        return boardRepository.findAll();
    }

    public Optional<ReviewBoard> findOne(Long boardId){
        return boardRepository.findById(boardId);
    }
    
    public Optional<ReviewBoard> updateCount(Long id) {
        ReviewBoard reviewBoard = boardRepository.findById(id).get();
        if(reviewBoard.getViewCount() == null){
            reviewBoard.setViewCount(0);
        }else{
            reviewBoard.setViewCount(reviewBoard.getViewCount()+1);
        }
        return Optional.ofNullable(reviewBoard);
    }

}
