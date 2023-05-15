package ezenstudy.bts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ezenstudy.bts.domain.Member;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpSession session, Model model) {

        Member logInMember = (Member) session.getAttribute("logInMember");
        if (logInMember == null) {
            return "home";
        }

        model.addAttribute("member", logInMember);
        return "loginHome";
    }

}
