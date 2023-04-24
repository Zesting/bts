package ezenstudy.bts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ezenstudy.bts.SessionConstants;
import ezenstudy.bts.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }
        Member logInMember = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
        if (logInMember == null) {
            return "home";
        }

        model.addAttribute("member", logInMember);
        return "loginHome";
    }

}
