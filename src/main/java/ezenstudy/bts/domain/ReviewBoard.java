package ezenstudy.bts.domain;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ReviewBoard {
    private Long id;                // 고유번호 pk
    private Long productId;         // 상품 고유번호 fk
    private Long memberId;          // 멤버 고유번호 fk
    private String title;           // 글제목
    private String content;         // 글내용
    private LocalDateTime cDate;    // 글작성일자
    private LocalDateTime uDate;    // 최종수정일자
    private Byte star;              // 상품별점
    private Integer viewCount;      // 글조회수
    private String writer;          // 글작성자
    private byte fileAttached;      // 파일첨부 여부 (1있음 0없음)
}
