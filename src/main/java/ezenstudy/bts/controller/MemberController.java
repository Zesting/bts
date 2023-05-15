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

import ezenstudy.bts.DTO.DropMemberDTO;
import ezenstudy.bts.DTO.FindMemberInfoDTO;
import ezenstudy.bts.DTO.LogInDTO;
import ezenstudy.bts.DTO.Member_AddrDTO;
import ezenstudy.bts.DTO.UpdateMemberDTO;
import ezenstudy.bts.domain.Addr;
import ezenstudy.bts.domain.Member;
import ezenstudy.bts.service.AddrService;
import ezenstudy.bts.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final붙은 변수에 대해 생성자 생성 메서드
@Controller
public class MemberController {
    private final MemberService memberService;
    private final AddrService addrService;

    /** ================================================================= */
    /** 회원 가입 기능. */

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createForm";
    }

    @PostMapping("/members/new")
    public String create(Member_AddrDTO member_addrDTO) {
        Member member = new Member();
        Addr addr = new Addr();

        member.setLogId(member_addrDTO.getLogId());
        member.setLogPwd(member_addrDTO.getLogPwd());
        member.setName(member_addrDTO.getName());
        member.setAge(member_addrDTO.getAge());
        member.setSocialNum(member_addrDTO.getSocialNum());
        member.setPhonNum(member_addrDTO.getPhonNum());
        member.setEmail(member_addrDTO.getEmail());
        member.setInnerDate(LocalDate.now());
        member.setLogTime(LocalTime.now());
        memberService.Join(member);
        System.out.println("Controller Create() 메서드 실행");

        addr.setMemberId(member.getId());
        addr.setZipCode(member_addrDTO.getZipCode());
        addr.setMemberName(member.getName());
        addr.setStreetAddr(member_addrDTO.getStreetAddr());
        addr.setDetailAddr(member_addrDTO.getDetailAddr());
        addrService.AddrJoin(addr);

        return "redirect:/";
    }

    /** ================================================================= */
    /** 회원 리스트 출력 기능 */

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findAllMembers();
        model.addAttribute("members", members);
        System.out.println("controller memberList() 실행");
        return "members/memberList";
    }

    /** ================================================================= */
    /** 주소 리스트 출력 기능 */
    @GetMapping("/addr")
    public String AddrList(Model model) {
        List<Addr> addr = addrService.findAllAddr();
        model.addAttribute("addr", addr);
        System.out.println("controller addrList() 실행");
        return "members/addrList";
    }

    /** ================================================================= */
    /* 로그인 기능 */

    /** 로그인 GetMapping */
    @GetMapping("/members/logIn")
    public String loginForm(@ModelAttribute LogInDTO logInDTO) {
        System.out.println("로그인 폼 이동");
        return "members/logInForm";
    }

    /** 로그인 PostMapping */
    @PostMapping("/members/logIn")
    public String logIn(@ModelAttribute @Validated LogInDTO logInDTO,
            BindingResult bindingResult,
            @RequestParam(defaultValue = "/") String redirectURL,
            HttpSession session) {

        /** 타입 미스 매치 또는 바인딩된 로그인 정보로 조회가 불가능하면 로그인 폼으로 리턴 */

        if (bindingResult.hasErrors()) {
            return "members/logInForm";
        }

        Member logInMember = memberService.LonIn(logInDTO.getLogId(), logInDTO.getLogPwd());

        if (logInMember == null) {
            bindingResult.reject("logInFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/logInForm";
        }
        session.setAttribute("logInMember", logInMember);

        /** 로그인 성공하면 기본 화면으로 리턴 */
        return "redirect:" + redirectURL;
    }

    /** 로그아웃 기능 */

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.getAttribute("logInMember");

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    /** ================================================================= */
    /** 회원 데이터 수정 기능 */

    @GetMapping("/members/updateInfo")
    public String updateForm(Model model, HttpSession session) {
        Member originalMember = (Member) session.getAttribute("logInMember");
        Addr originalAddr = addrService.findAddr(originalMember.getId()).get();
        model.addAttribute("originalMember", originalMember);
        model.addAttribute("originalAddr", originalAddr);
        return "members/updateForm";

    }

    @PostMapping("/members/updateInfo")
    public String updateMember(Model model, @Validated UpdateMemberDTO updateMemberDTO) {
        Optional<Member> coverOriginalMember = memberService.findById(updateMemberDTO.getId());
        Optional<Addr> coverOriginalAddr = addrService.findAddr(updateMemberDTO.getId());
        if (coverOriginalMember.isPresent() && coverOriginalAddr.isPresent()) {
            Member original = coverOriginalMember.get();
            Member updateMember = memberService.memberConverter(updateMemberDTO);

            Addr originalAddr = coverOriginalAddr.get();
            Addr updateAddr = addrService.addrConverter(updateMemberDTO);

            updateMember.setId(original.getId());
            updateMember.setLogId(original.getLogId());
            updateMember.setSocialNum(original.getSocialNum());
            updateAddr.setAddrId(originalAddr.getAddrId());
            updateAddr.setMemberId(originalAddr.getMemberId());

            if (updateMember.getLogPwd() == null) {
                updateMember.setLogPwd(original.getLogPwd());
            }
            if (updateMember.getName() == null) {
                updateMember.setName(original.getName());
            }
            if (updateMember.getAge() == 0) {
                updateMember.setAge(original.getAge());
            }
            if (updateMember.getPhonNum() == null) {
                updateMember.setPhonNum(original.getPhonNum());
            }
            if (updateMember.getEmail() == null) {
                updateMember.setEmail(original.getEmail());
            }

            if (updateAddr.getZipCode() == null) {
                updateAddr.setZipCode(originalAddr.getZipCode());
            }
            if (updateAddr.getStreetAddr() == null) {
                updateAddr.setStreetAddr(originalAddr.getStreetAddr());
            }
            if (updateAddr.getDetailAddr() == null) {
                updateAddr.setDetailAddr(originalAddr.getDetailAddr());
            }

            memberService.UpdateMember(updateMember);
            addrService.updateAddr(updateAddr);
            model.addAttribute("updateMember", updateMember);
            model.addAttribute("updateAddr", updateAddr);
            return "members/viewUpdate";
        }
        return "redirect:/";
    }

    @GetMapping("/members/viewMemberInfo")
    public String viewMemberInfo(Model model, HttpSession session) {
        Member originalMember = (Member) session.getAttribute("logInMember");
        model.addAttribute("updateMember", originalMember);
        return "members/viewUpdate";
    }

    /** ================================================================= */
    /** 회원 아이디 조회 기능 */

    @GetMapping("/members/findId")
    public String findIdForm() {
        return "members/findIdForm";
    }

    @PostMapping("/members/findId")
    public String findId(Model model, @Validated FindMemberInfoDTO findMemberInfoDTO) {

        Member findMember = memberService.FindMemberLogId(findMemberInfoDTO.getName(),
                findMemberInfoDTO.getSocialNum());
        if (findMember == null) {
            String errorMessage = "존재하지 않는 정보입니다. 다시 한번 입력해주세요.";
            model.addAttribute("errorMessage", errorMessage);
            return "members/findIdForm";
        }
        model.addAttribute("findMember", findMember);

        return "members/viewIdForm";
    }

    /** ================================================================= */
    /** 회원 비밀번호 조회 기능. */

    @GetMapping("/members/findPwd")
    public String findPwdForm() {
        return "members/findPwdForm";
    }

    @PostMapping("/members/findPwd")
    public String findPwd(Model model, @Validated FindMemberInfoDTO findMemberInfoDTO) {
        Optional<Member> findMember = memberService.FindMemberLogPwd(findMemberInfoDTO.getLogId(),
                findMemberInfoDTO.getName(),
                findMemberInfoDTO.getSocialNum());

        if (findMember.isPresent()) {
            Member useMember = findMember.get();
            model.addAttribute("useMember", useMember);
            return "members/viewPwdForm";

        } else {
            String errorMessage = "존재하지 않는 정보입니다. 다시 한번 입력해주세요.";
            model.addAttribute("errorMessage", errorMessage);
            return "members/findPwdForm";

        }
    }

    /** ================================================================= */
    /** 회원 탈퇴 기능 */

    @GetMapping("/members/dropMember")
    public String DropForm() {
        return "members/dropForm";
    }

    @ResponseBody
    @PostMapping("/members/dropMember")
    public String dropMember(Model model, DropMemberDTO dropMemberDTO) {
        Optional<Member> dropMember = memberService.VerificationMember(dropMemberDTO.getName(),
                dropMemberDTO.getLogId(),
                dropMemberDTO.getLogPwd());

        if (dropMember.isPresent()) {
            model.addAttribute("dropMember", dropMember.get().getName());
            memberService.DropMember(dropMember.get());
            addrService.DropAddr(dropMember.get().getId());
            return "<script>alert('" + dropMember.get().getName()
                    + "님의 회원 탈퇴가 정상적으로 수행되었습니다.');history.go(-2);</script>";
        } else {
            return "<script>alert('회원 정보를 조회하지 못했습니다. 다시 입력해주세요!');history.go(-1);</script>";
        }

    }

}
