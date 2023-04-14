package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.Addr;
import ezenstudy.bts.domain.Member;

public interface MemberRepository {

    /** 어느 하나의 멤버 객체 저장 */
    Member save(Member member);

    /** 멤버 레파지토리에 존재하는 모든 멤버 조회 */
    List<Member> findAll();

    /** 멤버에 저장된 모든 주소 조회 */
    List<Addr> findAddr(Long memberId);

    /** 회원 고유 번호를 통해 멤버 조회(관리자 측면) */
    Optional<Member> findMember(Long memberId);

    /** 회원 이름 및 주민번호(앞자리)를 통해 멤버의 ID 조회(사용자 측면) */
    Optional<Member> findLogId(String memberName, long memberSocialNum);

    /** 회원 로그인 아이디 및 주민번호(앞자리)를 통해 회원의 로그인 비밀번호 조회(사용자 측면) */
    Optional<Member> findLogPwd(String memberLogID, long memberSocialNum);

    /** 회원 정보 수정(업데이트) */
    Member update(Member member);

    /** 회원 정보 삭제(delete) */
    int delete(long memberId);
}
