package ezenstudy.bts.service;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.Member;
import ezenstudy.bts.repository.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;

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

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    /* 로그인 기능 */
    /* 리턴이 null이면 로그인 실패 */
    public Member LonIn(String memberLogId, String memberPwd) {
        return memberRepository.findLogId(memberLogId).filter(m -> m.getLogPwd().equals(memberPwd)).orElse(null);
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
    // 종민이형 의견 로그인 리스트가 아예 없을 경우, members가 0인 경우 빠져나가는 거 포함.
    public Member FindMemberLogId(String memberName, String memberSocialNum) {
        List<Member> members = memberRepository.findName(memberName);
        if (members.size() == 0) {
            return null;
        } else {
            for (Member member : members) {
                if (member.getSocialNum().equals(memberSocialNum)) {
                    return member;
                }
            }
        }
        return null;

    }

    /** 회원 로그인 비밀번호 찾기 기능 */
    public String FindMemberLogPwd(String memberLogId, String memberSocialNum) {
        Optional<Member> result = memberRepository.findLogId(memberLogId);
        if (result.isPresent()) {
            Member member = result.get();
            if (member.getSocialNum().equals(memberSocialNum)) {
                memberRepository.update(member.getId(), member);
                // 주민번호가 일치하는 메서드 1개.-> true 나오면 수정 페이지로 넘어가겠음.(수정 페이지에서 변경 메서드 따로 작성)
                return member.getName() + "님의 비밀번호 변경이 완료되었습니다.";
            } else {
                return "회원님의 주민등록번호가 다릅니다.";
            }
        } else {
            return "존재하지 않는 회원 ID입니다.";
        }
    }

    /** 회원 탈퇴 기능 */
    // 1. 검증 + 드랍 기능을 나눌 것 2. 컨트롤러에서 객체가져와서 검증하고 서비스는 드랍만.
    public void DropMember(Member member) {
        memberRepository.delete(member.getId());
    }

    /** 회원 탈퇴에 필요한 회원 검증 */
    public Optional<Member> VerificationMember(String memberName, String memberLogId, String memberLogPwd) {
        List<Member> members = memberRepository.findName(memberName);
        for (Member member : members) {
            if (member.getLogId().equals(memberLogId)) {
                if (member.getLogPwd().equals(memberLogPwd)) {
                    return Optional.of(member);
                } else {
                    return Optional.empty();
                }
            }
            return Optional.empty();
        }
        return Optional.empty();

    };
}
