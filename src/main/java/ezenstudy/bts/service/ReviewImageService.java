package ezenstudy.bts.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ezenstudy.bts.domain.ReviewImage;
import ezenstudy.bts.repository.ReviewImageRepository;

@Service
public class ReviewImageService {
    private final ReviewImageRepository reviewImageRepository;

    public ReviewImageService(ReviewImageRepository reviewImageRepository) {
        this.reviewImageRepository = reviewImageRepository;
    }

    // 파일첨부가 안된 경우
    public void nullSave(ReviewImage reviewImage){
        reviewImage.setFile(null);
        reviewImage.setFileName(null);
        reviewImage.setFilePath(null);
        reviewImageRepository.save(reviewImage);
    }

    // 파일첨부가 된 경우
    public void fileSave(ReviewImage reviewImage) {

        // 랜덤 파일명(중복안되게)
        UUID uuid = UUID.randomUUID();
        String fileName;
        // 파일경로 설정 (user.dir = 현재 디렉토리)
        String savePath = System.getProperty("user.dir")+"/src/main/resources/static/files/";
        // 저장될 파일 이름
        fileName = uuid + "_" + reviewImage.getFile().getOriginalFilename();
        // 파일 저장 경로,이름
        File saveFile = new File(savePath, fileName);

        // 파일 변환 후 저장
        try {
            reviewImage.getFile().transferTo(saveFile);
        } catch (IOException e) {
            System.out.println("파일 저장 실패");
            e.printStackTrace();
        }
        reviewImage.setFileName(fileName);
        reviewImage.setFilePath("/files/"+fileName);
        reviewImageRepository.save(reviewImage);
    }

    public List<ReviewImage> findAll(){
        return reviewImageRepository.findAll();
    }

    public List<ReviewImage> findReviewImages() {
        return reviewImageRepository.findAll();
    }

    public List<ReviewImage> findByReviewImages(Long boardId) {
        return reviewImageRepository.findByReviewBoardId(boardId);
    }

    public ReviewImage findOneImage(Long boardId){
        return reviewImageRepository.findByOneReviewBoardId(boardId);
    }


}
