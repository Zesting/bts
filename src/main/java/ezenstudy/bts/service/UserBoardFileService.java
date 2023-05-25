package ezenstudy.bts.service;

// import java.io.File;
import java.util.List;
import java.util.Optional;
// import java.util.UUID;

import ezenstudy.bts.domain.UserBoardFile;

public class UserBoardFileService {
  // private final UserBoardFileService userBoardFileService;

  public UserBoardFileService(UserBoardFileService userBoardFileService) {
    // this.userBoardFileService = userBoardFileService;
  }

  UserBoardFile save(UserBoardFile userBoardFile){
    //원래 파일이름 추출
    // String fileName = userBoardFile.getFileName();
    //파일 이름으로 쓸 UUID생성
    // String uuid = UUID.randomUUID().toString();
    //확장자 추출(ex: .png)
    // String extension = fileName.substring(fileName.lastIndexOf("."));
    //uuid와 확장자 결합
    // String saveName =  uuid + extension;
    //파일을 불러올때 사용할 경로
    // String savePath = /* + saveName; 경로 잡을 곳 정해서 적기*/ null+saveName;
    //파일 엔티티 생성 >>데이터베이스를 쓰지않기에 없음. 일단 패스

    //실제로 로컬에 uuid를 파일명으로 저장
    // fileName.transform(new File(savePath));


    return null;
  }
  
  UserBoardFile update(UserBoardFile userBoardFile){
    return null;
  }
  
  List<UserBoardFile> findAll(){
    return null;
  }
  
  Optional<UserBoardFile> findById(Long id){
    return null;
  }
  
  Optional<UserBoardFile> delete(Long id){
    return null;
  } 
}
