package ezenstudy.bts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ezenstudy.bts.domain.UserBoard;
import ezenstudy.bts.service.UserBoardCommentService;
import ezenstudy.bts.service.UserBoardService;

@Controller
public class UserBoardController {
  private final UserBoardService userBoardService;
  private final UserBoardCommentService userBoardCommentService;

  public UserBoardController(UserBoardService userBoardService, UserBoardCommentService userBoardCommentService) {
    this.userBoardService = userBoardService;
    this.userBoardCommentService = userBoardCommentService;
  }

    @GetMapping("/userBoard/Home")
    public String UserBoardHome(Model model){
     model.addAttribute("userBoards", userBoardService.findUserBoard());
      
        return "userBoard/userBoardHome";
    }
    
    @GetMapping("/userBoard/Create") //조회
    public String userBoardCreate(Model model) {
      //model.addAttribute("userBoardComments", userBoardCommentService.findById(id));
      return "userBoard/userBoardCreate";
    }


    @PostMapping("/userBoard/Create")//전송
    public String create( Model model, UserBoard userBoard) throws Exception {

      return "redirect:/userBoard/Create";
    }


    @PostMapping("/userBoard/Home")
    public String save(@RequestParam("userBoardTitle") String userBoardTitle,  //입력받은 게시물 저장
                        @RequestParam("userBoardContent") String userBoardContent) throws Exception{
      UserBoard userBoard = new UserBoard();
      userBoard.setUserBoardTitle(userBoardTitle); 
      userBoard.setUserBoardContent(userBoardContent); 
      userBoard.setUpdateDateTime(userBoardService.FormDateTime()); 
      userBoardService.save(userBoard); 
      return "redirect:/userBoard/Home";
    }

    
    @GetMapping("/userBoard/Update")
    public String update(Model model) {
      model.addAttribute("userBoardUpdates", userBoardService.findUserBoard()); 
      model.addAttribute("userBoardCommentUpdates", userBoardCommentService.findAllUserBoardComment());
      return "userBoard/userBoardUpdate";
    }

    @PostMapping("/userBoard/Update")
    public String update(){

      return "";
    }

}
 