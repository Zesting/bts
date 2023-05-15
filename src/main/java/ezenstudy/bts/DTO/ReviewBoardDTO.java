package ezenstudy.bts.DTO;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.domain.ReviewBoard;
import lombok.Data;

@Data
public class ReviewBoardDTO {
    private String title; // 글제목
    private String content; // 글내용
    private LocalDateTime cDate; // 글작성일자
    private Byte star; // 상품별점
    private String writer; // 글작성자


    private List<MultipartFile> file; // 첨부 파일

    // 파일 X
    public ReviewBoard toSaveReviewBaord() {
        ReviewBoard reviewBoard = new ReviewBoard();
        reviewBoard.setTitle(title);
        reviewBoard.setWriter(writer);
        reviewBoard.setContent(content);
        reviewBoard.setCDate(cDate);
        reviewBoard.setStar(star);
        reviewBoard.setFileAttached((byte) 0);
        return reviewBoard;
    }

    // 파일 O
    public ReviewBoard toSaveFileReviewBaord() {
        ReviewBoard reviewBoard = new ReviewBoard();
        reviewBoard.setTitle(title);
        reviewBoard.setWriter(writer);
        reviewBoard.setContent(content);
        reviewBoard.setCDate(cDate);
        reviewBoard.setStar(star);
        reviewBoard.setFileAttached((byte) 1);
        return reviewBoard;
    }

}
