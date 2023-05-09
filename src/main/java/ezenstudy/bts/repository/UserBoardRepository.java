package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoard;


public interface UserBoardRepository {
  //게시물 CRUD 
  UserBoard save(UserBoard userBoard);  //게시물 작성
  UserBoard update(UserBoard userBoard);//게시물 수정
  Optional<UserBoard> delete(Long commentId);     //게시물 삭제 
  List<UserBoard> listAll();            //게시물 조회
  Optional<UserBoard> findById(Long id);//작성된 게시물 id로 찾기
  Optional<UserBoard> findBoardOne(Long userBoardNumber); //임시로 게시물 번호로 검색하기 (회원연결전)
}
  