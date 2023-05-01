package ezenstudy.bts.controller;

import lombok.Data;

@Data
public class ReviewBoardRequest {
    private Long id;                // 글번호 PK
    private String title;           // 글제목
    private String content;         // 글내용
    private String writer;          // 글작성자
    private byte star;              // 상품별점
    
}



