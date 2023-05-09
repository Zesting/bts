package ezenstudy.bts.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReviewImage {
    private Long id;                        // 이미지 고유번호
    private Long reviewBoardId;             // 게시판 고유번호
    private String fileName;                // 서버 저장용 파일명
    private String filePath;                // 파일경로
    private MultipartFile file;             // 파일
}
