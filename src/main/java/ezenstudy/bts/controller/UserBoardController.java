package ezenstudy.bts.controller;


import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ezenstudy.bts.domain.UserBoard;
import ezenstudy.bts.service.UserBoardCommentService;
import ezenstudy.bts.service.UserBoardService;
// import lombok.extern.slf4j.Slf4j;
// @Slf4j
@Controller
public class UserBoardController {
  private final UserBoardService userBoardService;
  // private final UserBoardCommentService userBoardCommentService;

  public UserBoardController(UserBoardService userBoardService, UserBoardCommentService userBoardCommentService) {
    this.userBoardService = userBoardService;
    // this.userBoardCommentService = userBoardCommentService;
  }

    @GetMapping("/userBoard/Home")//정상
    public String UserBoardHome(Model model){
     model.addAttribute("userBoards", userBoardService.findUserBoard());
        return "userBoard/userBoardHome";
    }

    // @GetMapping("/userBoard/Home")//테스트
    // public String UserBoardHome(@PathVariable Long id, Model model){
    // Optional<UserBoard> userBoard = userBoardService.findOne(id);
    //  model.addAttribute("userBoards", userBoardService.findUserBoard());
    //  model.addAttribute("BoardId",userBoard.get().getUserBoardNumber());
    //   //log.info();
    //     return "userBoard/userBoardHome";
    // }


    // @PostMapping("/userBoard/Home")//리스트//정상 //아래코드 객체로 바꾸어 코드 줄임
    // public String save(@RequestParam("userBoardTitle") String userBoardTitle,  //입력받은 게시물 저장
    //                     @RequestParam("userBoardContent") String userBoardContent) throws Exception{
    //   UserBoard userBoard = new UserBoard();
    //   userBoard.setUserBoardTitle(userBoardTitle); 
    //   userBoard.setUserBoardContent(userBoardContent); 
    //   userBoard.setUpdateDateTime(userBoardService.FormDateTime()); 
    //   userBoardService.save(userBoard); 
    //   return "redirect:/userBoard/Home";
    // }
    @PostMapping("/userBoard/Home") // 리스트//정상
    public String save(UserBoard userBoard) throws Exception {
      userBoard.setUpdateDateTime(userBoardService.FormDateTime());
      userBoardService.save(userBoard);//3~4줄을 객체로 하나로 묶어서 줄여줌 
      return "redirect:/userBoard/Home";
    }//크레이트가 안쓰고있다.



    @GetMapping("/userBoard/Create") //저장//정상
    public String userBoardCreate(Model model) {
      //model.addAttribute("userBoardComments", userBoardCommentService.findById(id));
      return "userBoard/userBoardCreate";
    }

    @PostMapping("/userBoard/Create")//전송//정상
    public String create( Model model, UserBoard userBoardCreate) throws Exception {
      //UserBoard userBoard = userBoardCreate.
      return "redirect:/userBoard/Create";
    }

    // @GetMapping("/userBoard/Read")//정상 >> 아이디 부여 없이 리스트만 뽑는 코드 
    // public String update(Model model) {//@PathVariable Long boardNumber ,
    //   model.addAttribute("userBoardRead", userBoardService.findUserBoard()); 
    //   //model.addAttribute("userBoardCommentUpdates", userBoardCommentService.findById(id));//comment꺼 수정하기
    //   // model.addAttribute("title", userBoardService.findBoardOne(boardNumber));
    //   return "userBoard/userBoardRead";
    // }

    @GetMapping("/userBoard/Read/{id}")//테스트 OK >정상
    public String Read(@PathVariable Long id, Model model){
      //1. id로 데이터를 가져옴
      Optional<UserBoard> userBoard = userBoardService.findOne(id);
      //2. 가져온 데이터를 모델에 등록
      model.addAttribute("userBoardRead",userBoard.get());
      //3. 보여줄 페이지를 설정
      return "userBoard/userBoardRead";
    }

    @GetMapping("/userBoard/Delete/{id}")//ok 정상
    public String delete(@PathVariable Long id, Model model){
      userBoardService.delete(id);
      return "redirect:/userBoard/Home";
    }
    
    @PostMapping("/userBoard/Update") //테스트
    public String update(UserBoard userBoard){
      // 1. 수정할 게시글 가져오기
      // Optional<UserBoard> ub = userBoardService.findOne(userBoard.getId());
        userBoardService.update(userBoard);
        // model.addAttribute("board", userBoard);
      // 2. 객체 저장
      // model.addAttribute("userBoardUpdate", userBoard);
      return "redirect:/userBoard/Update";
    }

    @GetMapping("/userBoard/Update/{id}")
    public String update2(UserBoard userBoard){
      userBoardService.update(userBoard);

      return "userBoard/userBoardRead";
    }



   
}
 