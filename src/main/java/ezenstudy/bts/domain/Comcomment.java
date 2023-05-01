package ezenstudy.bts.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comcomment {

    private Long id; // 아이디
    private String content; // 댓글 내용
    private LocalDateTime createAt;  // 등록일
    private String createdBy;  // 작성자

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}