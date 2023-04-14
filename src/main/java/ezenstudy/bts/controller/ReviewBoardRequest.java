package ezenstudy.bts.controller;


public class ReviewBoardRequest {
    private Long id;                // 글번호 PK
    private String title;           // 글제목
    private String content;         // 글내용
    private String writer;          // 글작성자
    private byte star;              // 상품별점
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public byte getStar() {
        return star;
    }
    public void setStar(byte star) {
        this.star = star;
    }
}



