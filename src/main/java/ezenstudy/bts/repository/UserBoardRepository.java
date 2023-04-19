package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.UserBoard;


public interface UserBoardRepository {
  //게시물 CRUD 
  UserBoard save(UserBoard userBoard);  //게시물 작성
  UserBoard update(UserBoard userBoard);//게시물 수정
  Optional<UserBoard> delete(UserBoard userBoard);     //게시물 삭제 >> 반환할 것이 없다면 void가 더 낫다.
  List<UserBoard> listAll();            //게시물 조회
  Optional<UserBoard> findById(Long id);//작성된 게시물 id로 찾기?
  Optional<UserBoard> findBynewProductID(String newProductID);//상품명으로 찾기 
  
}
