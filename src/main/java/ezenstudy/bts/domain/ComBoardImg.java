package ezenstudy.bts.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ComBoardImg {
    private Long id;
    private Long comBoardId;
    private String fileName; // 파일이름
    private String filePath; // 파일경로
    private MultipartFile file; //파일
}
