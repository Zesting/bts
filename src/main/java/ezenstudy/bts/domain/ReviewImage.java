package ezenstudy.bts.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReviewImage {
    private Long id;                        // 이미지 고유번호
    private Long boardId;                   // 게시판 고유번호
    private Long productId;                 // 상품 고유번호
    private String originalFileName;        // 원본파일명
    private String fileName;                // 서버 저장용 파일명
    private String filePath;                // 파일경로
    private MultipartFile file;             // reviewsave.html > controller 파일 담는 용도
    private byte fileAttached;              // 파일 첨부 여부(첨부 1 , 미첨부 0) 플래그값
                                            // boolean 타입으로 하는 것 보다 다루기 편함
}
