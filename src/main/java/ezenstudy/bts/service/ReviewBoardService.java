package ezenstudy.bts.service;

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

    public void save(ReviewBoard reviewBoard){
        boardRepository.save(reviewBoard);
    }

    public void update(ReviewBoard reviewBoard){
        boardRepository.update(reviewBoard);
    }

    public void delete(ReviewBoard reviewBoard){
        boardRepository.delete(reviewBoard.getId());
    }

    public List<ReviewBoard> findAll(){
        return boardRepository.findAll();
    }

    public Optional<ReviewBoard> findOne(Long boardid){
        return boardRepository.findById(boardid);
    }
    public ReviewBoard getBoard(Long id) {
        ReviewBoard reviewBoard = boardRepository.findById(id).get();
        if(reviewBoard!= null){
            //조회수 증가
            reviewBoard.setViewCount(reviewBoard.getViewCount()+1);
        }
        return reviewBoard;
    }
}
