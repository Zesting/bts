package ezenstudy.bts.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ezenstudy.bts.SessionConstants;
import ezenstudy.bts.domain.Member;
import ezenstudy.bts.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final붙은 변수에 대해 생성자 생성 메서드
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setLogId(form.getLogId());
        member.setLogPwd(form.getLogPwd());
        member.setName(form.getName());
        member.setAge(form.getAge());
        member.setSocialNum(form.getSocialNum());
        member.setPhonNum(form.getPhonNum());
        member.setEmail(form.getEmail());
        member.setInnerDate(LocalDate.now());
        member.setLogTime(LocalTime.now());
        memberService.Join(member);
        System.out.println("Controller Create() 메서드 실행");
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findAllMembers();
        model.addAttribute("members", members);
        System.out.println("controller memberList() 실행");
        return "members/memberList";
    }

    /** 로그인 GetMapping */
    @GetMapping("/members/logIn")
    public String loginForm(@ModelAttribute LogInForm loginForm) {
        System.out.println("로그인 폼 이동");
        return "members/logInForm";
    }

    /** 로그인 PostMapping */
    @PostMapping("/members/logIn")
    public String logIn(@ModelAttribute @Validated LogInForm loginForm,
            BindingResult bindingResult,
            @RequestParam(defaultValue = "/") String redirectURL,
            HttpServletRequest request) {

        /** 타입 미스 매치 또는 바인딩된 로그인 정보로 조회가 불가능하면 로그인 폼으로 리턴 */

        if (bindingResult.hasErrors()) {
            return "members/logInForm";
        }

        Member logInMember = memberService.LonIn(loginForm.getLogId(), loginForm.getLogPwd());

        if (logInMember == null) {
            bindingResult.reject("logInFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/logInForm";
        }

        HttpSession session = request.getSession(); // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
        session.setAttribute(SessionConstants.LOGIN_MEMBER, logInMember);

        /** 로그인 성공하면 기본 화면으로 리턴 */
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/members/findId")
    public String findIdForm() {
        return "members/findIdForm";
    }

    @PostMapping("/members/findId")
    public String findId(Model model, @Validated FindMemberInfo findMemberInfo,
            BindingResult bindingResult) {

        Member findMember = memberService.FindMemberLogId(findMemberInfo.getName(),
                findMemberInfo.getSocialNum());
        if (findMember == null) {
            String errorMessage = "존재하지 않는 정보입니다. 다시 한번 입력해주세요.";
            model.addAttribute("errorMessage", errorMessage);
            return "members/findIdForm";
        }
        model.addAttribute("findMember", findMember);

        return "members/viewIdForm";
    }

    @GetMapping("/members/dropMember")
    public String DropForm() {
        return "members/dropForm";
    }

    @ResponseBody
    @PostMapping("/members/dropMember")
    public String dropMember(Model model, DropMemberForm dropMemberForm) {
        Optional<Member> dropMember = memberService.VerificationMember(dropMemberForm.getName(),
                dropMemberForm.getLogId(),
                dropMemberForm.getLogPwd());

        if (dropMember.isPresent()) {
            model.addAttribute("dropMember", dropMember.get().getName());
            memberService.DropMember(dropMember.get());
            return "<script>alert('" + dropMember.get().getName()
                    + "님의 회원 탈퇴가 정상적으로 수행되었습니다.');history.go(-2);</script>";
        } else {
            return "<script>alert('회원 정보를 조회하지 못했습니다. 다시 입력해주세요!');history.go(-1);</script>";
        }

        /*
         * if (!dropMember.isPresent()) {
         * model.addAttribute("dropMember", dropMember.get().getName());
         * return
         * "<script>alert('회원 정보를 조회하지 못했습니다. 다시 입력해주세요!');history.go(-1);</script>";
         * }
         * 
         * model.addAttribute("dropMember", dropMember.get().getName());
         * memberService.DropMember(dropMember.get());
         * return
         */

    }

}
