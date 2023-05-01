package ezenstudy.bts.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.domain.ReviewBoard;
import lombok.Data;

@Data
public class ReviewBoardDTO {
    private String title;                   // 글제목
    private String writer;                  // 글작성자
    private String content;                 // 글내용
    private Byte star;                      // 상품별점


    private List<MultipartFile> File;       // 첨부 파일
    private String originalFileName;        // 원본 파일명
    private String fileName;                // 서버 저장용 파일명
    private String filePath;                // 파일 저장 경로
    private byte fileAttached;              // 파일 첨부 여부(첨부 1 , 미첨부 0) 플래그값
                                            // boolean 타입으로 하는 것 보다 다루기 편함



    

    

    public ReviewBoard toSaveReviewBaord(){
        ReviewBoard reviewBoard = new ReviewBoard();

        reviewBoard.setTitle(title);
        reviewBoard.setWriter(writer);
        reviewBoard.setContent(content);

        return reviewBoard;
    }
}


