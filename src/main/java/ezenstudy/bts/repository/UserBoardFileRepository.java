package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoardFile;

public interface UserBoardFileRepository {
  List<UserBoardFile> findAll();                    //전체 조회
  Optional<UserBoardFile> findById(Long id);        //아이디 조회
  UserBoardFile save(UserBoardFile userBoardFile);  //저장
  UserBoardFile update(UserBoardFile userBoardFile);//수정
  Optional<UserBoardFile> delete(Long id);                     //삭제
  
}
