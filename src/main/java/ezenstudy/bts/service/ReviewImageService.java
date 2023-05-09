package ezenstudy.bts.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.DTO.ReviewBoardDTO;

@Service
public class ReviewImageService {
    // private ReviewImageRepository reviewImageRepository;

    // public ReviewImageService(ReviewImageRepository reviewImageRepository) {
    //     this.reviewImageRepository = reviewImageRepository;
    // }

    public void save(ReviewBoardDTO reviewBoardDTO) throws IOException {

        if(reviewBoardDTO.getFile().isEmpty()){
            
        }

        // 파일경로 설정 (user.dir = 현재 디렉토리)
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        // 랜덤 파일명(중복안되게)
        UUID uuid = UUID.randomUUID();
        for(MultipartFile file : reviewBoardDTO.getFile()){
        // 저장될 파일 이름
        String fileName = uuid + "_" + file.getOriginalFilename();
        // 파일 저장 경로,이름
        File saveFile = new File(filePath, fileName);
        // 파일 변환 후 저장
        file.transferTo(saveFile);
        reviewBoardDTO.setFileName(fileName);
        reviewBoardDTO.setFilePath("/files/" + filePath);
        }

    }

}
