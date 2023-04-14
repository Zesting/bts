package ezenstudy.bts.domain;

import java.time.LocalDateTime;

public class ReviewBoard {
    private Long id;                // 글번호 PK
    private String title;           // 글제목
    private String content;         // 글내용
    private LocalDateTime cDate;    // 글작성일자
    private LocalDateTime uDate;    // 최종수정일자
    private Byte star;              // 상품별점
    
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

    public LocalDateTime getcDate() {
        return cDate;
    }
    public void setcDate(LocalDateTime cDate) {
        this.cDate = cDate;
    }
    public LocalDateTime getuDate() {
        return uDate;
    }
    public void setuDate(LocalDateTime uDate) {
        this.uDate = uDate;
    }
    public Byte getStar() {
        return star;
    }
    public void setStar(Byte star) {
        this.star = star;
    }
    public Integer getViewCount() {
        return viewCount;
    }
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
    private Integer viewCount;      // 글조회수
    


}
