package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class UserBoardFile {
  private long userBoardNumber;   //게시물번호
  private Long userBoardFileId;   //파일고유번호
  private String fileName;        //파일 이름
	private String savePath;      //저장 경로
	private String uuid;            //범용 고유 식별자(Universally Unique IDentifier) >>중복하여 덮어쓰기되는것을 막기위함.
	private boolean isImage;          //이미지 파일 확인
}
