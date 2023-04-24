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

        // //파일경로 설정 (user.dir = 현재 디렉토리)
        // String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        // //랜덤 파일명(중복안되게)
        // UUID uuid = UUID.randomUUID();
        // MultipartFile file = reviewBoard.getFile();
        // //저장될 파일 이름
        // String fileName = uuid + "_" + file.getOriginalFilename();
        // //파일 저장 경로,이름
        // File saveFile = new File(filePath,fileName);
        // //파일 변환 후 저장
        // file.transferTo(saveFile);
        // //데이
        // reviewBoard.setFileName(fileName);
        // reviewBoard.setFilePath("/files/"+filePath);

    }

    public void update(ReviewBoard reviewBoard){
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
