package ezenstudy.bts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserBoardController {
  @GetMapping("home")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "home";
    }
    
}
