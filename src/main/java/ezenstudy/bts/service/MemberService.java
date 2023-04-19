package ezenstudy.bts.service;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.Member;
import ezenstudy.bts.repository.MemberRepository;

public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원가입 기능 */
    public Long Join(Member member) {
        VDmemberLogId(member);
        memberRepository.save(member);
        return member.getId();
    }

    /* 회원 로그인 아이디 중복 검사 기능 */
    public void VDmemberLogId(Member member) {
        memberRepository.findLogId(member.getLogId()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        });
    }

    /* 로그인 기능 */
    public Member LonIn(String memberLogId, String memberPwd) {
        Optional<Member> result = memberRepository.findLogId(memberLogId);
        if (result.isPresent()) {
            Member member = result.get();
            if (member.getLogPwd().equals(memberPwd)) {
                return member;
            }
        }
        return null;
    }

    /** 회원 정보 수정 기능 로그인 유지 기능 만들고 만들 것 */
    /*
     * public Member ModifyMember(String memberLogPwd, String memberInfo) {
     * 
     * return null;
     * 
     * }
     */

    /** 회원 로그인 아이디 찾기 기능 */
    public String FindMemberLogId(String memberName, Long memberSocialNum) {
        List<Member> members = memberRepository.findName(memberName);
        for (Member member : members) {
            if (member.getSocialNum() == memberSocialNum) {
                return member.getLogId();
            }
        }
        return "존재하지 않는 회원입니다.";
    }

    /** 회원 로그인 비밀번호 찾기 기능 */
    public String FindMemberLogPwd(String memberLogId, Long memberSocialNum) {
        Optional<Member> result = memberRepository.findLogId(memberLogId);
        if (result.isPresent()) {
            Member member = result.get();
            if (member.getSocialNum() == memberSocialNum) {
                memberRepository.update(member.getId(), member);
                return member.getName() + "님의 비밀번호 변경이 완료되었습니다.";
            } else {
                return "회원님의 주민등록번호가 다릅니다.";
            }
        } else {
            return "존재하지 않는 회원 ID입니다.";
        }
    }

    /** 회원 탈퇴 기능 */
    public String DropMember(String memberName, String memberLogId, String memberLogPwd) {
        List<Member> members = memberRepository.findName(memberName);
        for (Member member : members) {
            if (member.getLogId().equals(memberLogId)) {
                if (member.getLogPwd().equals(memberLogPwd)) {
                    memberRepository.delete(member.getId());
                    return "회원 탈퇴가 정상적으로 수행되었습니다.";
                } else {
                    return "회원님의 비밀번호가 정확하지 않습니다.";
                }
            } else {
                return "회원님의 아이디가 정확하지 않습니다.";
            }

        }
        return "존재하지 않는 회원입니다.";
    }

}
