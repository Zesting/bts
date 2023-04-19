package ezenstudy.bts.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ezenstudy.bts.domain.UserBoard;
import ezenstudy.bts.service.UserBoardService;

@Controller
public class UserBoardController {

  private final UserBoardService userBoardService;

  public UserBoardController(UserBoardService userBoardService) {
    this.userBoardService = userBoardService;
  }

    @GetMapping("/userBoard/Home")
    public String UserBoardHome() {
        
        return "userBoard/userBoardHome";
    }
    
    @GetMapping("/userBoard/Create") 
    public String userBoardCreate() {

      return "userBoard/userBoardCreate";
    }
    
    @PostMapping("/userBoard/Create")
    public String create( Model model, UserBoard userBoard) {
     /* userBoardService.save(userBoard);
      model.addAttribute("userBoardTitle", userBoard.getUserBoardTitle());*/
      // 모델에 userBoardTitle 데이터 추가
    model.addAttribute("userBoardTitle", userBoard.getUserBoardTitle());
    // 모델에 userBoardContent 데이터 추가
    model.addAttribute("userBoardContent", userBoard.getUserBoardContent());

      return "redirect:/userBoard/Create";
    }

    /*
     *  @PostMapping("/userBoard/Create")
    public String create(@RequestParam("userBoardTitle") String userBoardTitle,@RequestParam("userBoardContent") String userBoardContent) {
      UserBoard userBoard = new UserBoard(userBoardTitle, userBoardContent);
      userBoardService.save(userBoard);
      return "redirect:/userBoard/Create";
    }
     * 
     */

    @PostMapping("/userBoard/Home")
    public String save(@RequestParam("userBoardTitle") String userBoardTitle, @RequestParam("userBoardContent") String userBoardContent){
      UserBoard userBoard = new UserBoard();
      userBoard.setUserBoardTitle(userBoardTitle); // 사용자가 입력한 userBoardTitle 값을 userBoard 객체에 저장
      userBoard.setUserBoardContent(userBoardContent); // 사용자가 입력한 userBoardContent 값을 userBoard 객체에 저장
      // UserBoardService 클래스의 getUserBoardDateTime() 메서드 호출
      userBoard.setUserBoardDateTime(userBoardService.getUserBoardDateTime()); 
      userBoardService.save(userBoard); // userBoard 저장
      return "redirect:/userBoard/Home";// Home으로 리다이렉트
    }


}
 