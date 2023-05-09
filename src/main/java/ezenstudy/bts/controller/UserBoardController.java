package ezenstudy.bts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ezenstudy.bts.domain.UserBoard;
import ezenstudy.bts.domain.UserBoardComment;
import ezenstudy.bts.service.UserBoardCommentService;
import ezenstudy.bts.service.UserBoardService;

// import lombok.extern.slf4j.Slf4j;
// @Slf4j
@Controller
public class UserBoardController {
  private final UserBoardService userBoardService;
  private UserBoardCommentService userBoardCommentService;

  public UserBoardController(UserBoardService userBoardService, UserBoardCommentService userBoardCommentService) {
    this.userBoardService = userBoardService;
    this.userBoardCommentService = userBoardCommentService;
  }

  @GetMapping("/userBoard/Home") //주소 // 정상
  public String UserBoardHome(Model model) {
    model.addAttribute("userBoards", userBoardService.findUserBoard());
    return "userBoard/userBoardHome";//뷰 파일 
  }

  // @PostMapping("/userBoard/Home")//리스트//정상 //아래코드 객체로 바꾸어 코드 줄임
  // public String save(@RequestParam("userBoardTitle") String userBoardTitle,
  // //입력받은 게시물 저장
  // @RequestParam("userBoardContent") String userBoardContent) throws Exception{
  // UserBoard userBoard = new UserBoard();
  // userBoard.setUserBoardTitle(userBoardTitle);
  // userBoard.setUserBoardContent(userBoardContent);
  // userBoard.setUpdateDateTime(userBoardService.FormDateTime());
  // userBoardService.save(userBoard);
  // return "redirect:/userBoard/Home";
  // }
  @PostMapping("/userBoard/Home") //정상
  public String save(UserBoard userBoard) throws Exception {
    userBoard.setUpdateDateTime(userBoardService.FormDateTime());
    userBoardService.save(userBoard);// 3~4줄을 객체로 하나로 묶어서 줄여줌
    return "redirect:/userBoard/Home";//주소로 이동(겟)
  }

  @GetMapping("/userBoard/Create") // 저장//정상
  public String userBoardCreate(Model model) {
    return "userBoard/userBoardCreate";
  }

  @GetMapping("/userBoard/Read/{id}") // 테스트 OK >정상
  public String Read(@PathVariable Long id, Model model) {
    // 1. id로 데이터를 가져옴
    Optional<UserBoard> userBoard = userBoardService.findOne(id);
    List<UserBoardComment> comment = userBoardCommentService.findListByBoardId(id);
    // 2. 가져온 데이터를 모델에 등록
    model.addAttribute("userBoardRead", userBoard.get());
    model.addAttribute("comments", comment);// 게시물 아이디로 리스트
    // 3. 보여줄 페이지를 설정
    return "userBoard/userBoardRead";
  }

  @PostMapping("/userBoard/comment/{id}")
  public String saveComment(@PathVariable("id") Long boardId, UserBoardComment userBoardComment) {
    userBoardComment.setBoardId(boardId);
    userBoardComment.setMemberId(777L);//임시로 멤버 아이디를 777로 줌
    userBoardCommentService.saveComment(userBoardComment);
    return "redirect:/userBoard/Read/"+boardId;
  }

  


  //게시물 지우기
  @GetMapping("/userBoard/Delete/{id}") // ok 정상 //겟보다는 포스트하는게 좋다 주소창으로 접근해서 지울수잇음
  public String delete(@PathVariable Long id) {
    userBoardService.delete(id);
    return "redirect:/userBoard/Home";
  }
  //댓글지우기
  @GetMapping("/userBoard/Delete/{bId}/comment/{cId}") // ok 정상 //겟보다는 포스트하는게 좋다 주소창으로 접근해서 지울수잇음
  public String deleteomment(@PathVariable("bId") Long boardId, @PathVariable("cId") Long commentId) {
    userBoardCommentService.delete(commentId);
    return "redirect:/userBoard/Read/"+boardId;
  }

  // @PostMapping("/userBoard/Delete/{id}/comment")
  // public String delete(UserBoardComment userBoardComment,@PathVariable("id") Long commentId) {
  //   userBoardCommentService.delete(commentId);
  //   return "redirect:/userBoard/Read/"+commentId;
  // }


  @PostMapping("/userBoard/Update/{id}")
  public String update(@PathVariable Long id, UserBoard userBoard) {
    userBoard.setUserBoardNumber(id);
    userBoardService.update(userBoard);
    return "redirect:/userBoard/Read/" + id;
  }

  @GetMapping("/userBoard/Update/{id}")
  public String update2(@PathVariable("id") Long id, Model model) {
    model.addAttribute("userBoardRead", userBoardService.findOne(id).get());
    return "userBoard/userBoardUpdate";
  }

}
