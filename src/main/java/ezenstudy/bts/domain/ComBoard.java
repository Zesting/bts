package ezenstudy.bts.domain;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ComBoard {

    private Long id; // 아이디
    private String title;  // 제목
    private String content; // 내용
    private LocalDateTime createAt;  // 생성일자
    private String createdBy;  // 생성자
    private String BN;  // 사업자 번호
    private String answer; // 답변

    private String filename; // 파일이름
    private String filepath; // 파일경로

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return this.filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    // private boolean filled; // new property
    //private MultipartFile file;


    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

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

    public String getBN() {
        return BN;
    }

    public void setBN(String BN) {
        this.BN = BN;
    }

    // public boolean isFilled() {
    //     return filled;
    // }

    // public void setFilled(boolean filled) {
    //     this.filled = filled;
    // }

    public ComBoard orElse(Object object) {
        return null;
    }
}
