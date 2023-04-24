package ezenstudy.bts.domain;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class ReviewBoard {
    private Long id;                // 글번호 PK
    private String title;           // 글제목
    private String content;         // 글내용
    private LocalDateTime cDate;    // 글작성일자
    private LocalDateTime uDate;    // 최종수정일자
    private Byte star;              // 상품별점
    private Integer viewCount;      // 글조회수
    private String fileName;        // 파일이름
    private String filePath;        // 파일경로
    private MultipartFile file;     // 파일

}
