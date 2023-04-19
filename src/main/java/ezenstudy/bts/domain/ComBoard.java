package ezenstudy.bts.domain;


import java.time.LocalDateTime;

public class ComBoard {

    private String title; // 제목
    private String content; // 내용
    private LocalDateTime createAt; // 생성일자
    private String createdBy; // 생성자
    private Long id; // 아이디
    private String BN; // 사업자 번호

    public ComBoard(String title, String content, LocalDateTime createAt, String createdBy, Long id, String BN) {
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.createdBy = createdBy;
        this.id = id;
        this.BN = BN;
    }

    // Getters and Setters
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBN() {
        return BN;
    }

    public void setBN(String BN) {
        this.BN = BN;
    }

    @Override
    public String toString() {
        return "ComBoard{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", createdBy='" + createdBy + '\'' +
                ", id=" + id +
                ", BN='" + BN + '\'' +
                '}';
    }
}
