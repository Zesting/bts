package ezenstudy.bts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ezenstudy.bts.domain.Member;
import ezenstudy.bts.domain.UserBoard;
import ezenstudy.bts.domain.UserBoardComment;
import ezenstudy.bts.domain.UserBoardOffer;
import ezenstudy.bts.service.UserBoardCommentService;
import ezenstudy.bts.service.UserBoardOfferService;
import ezenstudy.bts.service.UserBoardService;
import jakarta.servlet.http.HttpSession;

// import lombok.extern.slf4j.Slf4j;
// @Slf4j
@Controller
public class UserBoardController {
  private final UserBoardService userBoardService;
  private final UserBoardCommentService userBoardCommentService;
  private final UserBoardOfferService userBoardOfferService;

  public UserBoardController(UserBoardService userBoardService, UserBoardCommentService userBoardCommentService,
      UserBoardOfferService userBoardOfferService) {
    this.userBoardService = userBoardService;
    this.userBoardCommentService = userBoardCommentService;
    this.userBoardOfferService = userBoardOfferService;
  }

  @GetMapping("/userBoard/Home")
  public String UserBoardHome(Model model) {
    model.addAttribute("userBoards", userBoardService.findUserBoard());
    return "userBoard/userBoardHome";
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
  @PostMapping("/userBoard/Home")
  public String save(UserBoard userBoard, HttpSession session, Model model) throws Exception {
    userBoard.setUpdateDateTime(userBoardService.FormDateTime());
    if (session.getAttribute("logInMember") instanceof Member member) {
      userBoard.setMemberId(member.getId());
    }
    if(userBoard.getUserBoardTitle() !="" && userBoard.getUserBoardContent()!="") {
      //게시물 저장
      userBoardService.save(userBoard);// 3~4줄을 객체로 하나로 묶어서 줄여줌
    } else if(userBoard.getUserBoardContent()==""){
      model.addAttribute("msg", "Content을 입력해주세요");
      return "exception/goback_with_message";
    } else{
      model.addAttribute("msg", "Title을 입력해주세요");
      return "exception/goback_with_message";
    }
    return "redirect:/userBoard/Home";// 주소로 이동(겟)
  }

  // 게시물 등록
  @GetMapping("/userBoard/Create")
  public String userBoardCreate(Model model, HttpSession session) {
    // 로그인 상태 유무 확인
    if (session.getAttribute("logInMember") == null) {
      return "redirect:/members/logIn";
    } else {
      return "userBoard/userBoardCreate";
    }
  }

  @GetMapping("/userBoard/Read/{id}")
  public String Read(@PathVariable("id") Long boardId, Model model, HttpSession session,
      UserBoardComment userBoardComment) {
    // 로그인이 되어있지않으면 로그인 창으로 보내기
    if (session.getAttribute("logInMember") == null) {
      return "redirect:/members/logIn";
    } else {
      // 1. id로 데이터를 가져옴
      Optional<UserBoard> userBoard = userBoardService.findOne(boardId);
      List<UserBoardComment> comment = userBoardCommentService.findListByBoardId(boardId);

      // 2. 가져온 데이터를 모델에 등록
      model.addAttribute("userBoardRead", userBoard.get());
      model.addAttribute("comments", comment);// 게시물 아이디로 리스트
      // 3. 보여줄 페이지를 설정
      return "userBoard/userBoardRead";
    }
  }

  // 댓글등록
  @PostMapping("/userBoard/comment/{id}")
  public String saveComment(@PathVariable("id") Long boardId, UserBoardComment userBoardComment,
      HttpSession session, Model model) {
    userBoardComment.setBoardId(boardId);
    // 멤버 ID로 바꿔주기. 세션에서 값이 있으면 멤버객체에서 아이디 가져옴.
    if (session.getAttribute("logInMember") instanceof Member member) {
      userBoardComment.setMemberId(member.getId());
    }
    // 맞는 문법인지 확인하기
    if (userBoardComment.getCommentContent() != "") {
      userBoardCommentService.saveComment(userBoardComment);
    } else {
      model.addAttribute("msg", "내용을 입력해주세요");
      return "exception/goback_with_message";
    }
    return "redirect:/userBoard/Read/" + boardId;

  }

  // 댓글 수정
  @PostMapping("/userBoard/comment/update/{id}")
  public String updateComment(@PathVariable("id") Long boardId,
  UserBoardComment userBoardComment) throws Exception {
    UserBoardComment newUserBoardComment = new UserBoardComment();
    // 뷰에서 disabled로 textarea에서 막고 있어서 값이 null로 나왔었음..
    newUserBoardComment.setCommentContent(userBoardComment.getCommentContent());
    newUserBoardComment.setId(userBoardComment.getId());
    userBoardCommentService.update(newUserBoardComment);

    return "redirect:/userBoard/Read/" + boardId;
  }
  // 현재 문제점이 보드id의 기준으로 선택되어 저장이 커맨트id로 수정되고잇음

  // 게시물 지우기
  @GetMapping("/userBoard/Delete/{id}") 
  public String delete(@PathVariable Long id) {
    userBoardService.delete(id);
    return "redirect:/userBoard/Home";
  }

  // 댓글지우기
  @GetMapping("/userBoard/Delete/{bId}/comment/{cId}") 
  public String deleteomment(@PathVariable("bId") Long boardId, @PathVariable("cId") Long commentId) {
    userBoardCommentService.delete(commentId);
    return "redirect:/userBoard/Read/" + boardId;
  }

  // 게시물 수정
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

  @ResponseBody
  @PostMapping("/userBoard/offer/save")
  public String offerSave(Model model, HttpSession session, @ModelAttribute("userBoardId") Long userBoardId) {
    if (session.getAttribute("logInMember") instanceof Member member) {
      UserBoardOffer userBoardOffer = new UserBoardOffer();
      userBoardOffer.setMemberId(member.getId());
      userBoardOffer.setUserBoardId(userBoardId);
      return "" + userBoardOfferService.save(userBoardOffer).getId();
    } else {
      model.addAttribute("msg", "로그인 후에 이용해주세요");
      return "exception/goback_with_message";
    }
  }
}
