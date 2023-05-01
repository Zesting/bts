package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

/* import ezenstudy.bts.domain.Addr; */
import ezenstudy.bts.domain.Member;

public interface MemberRepository {

    /** 어느 하나의 멤버 객체 저장 */
    Member save(Member member);

    /** 멤버 레파지토리에 존재하는 모든 멤버 조회 */
    List<Member> findAll();

    /** 멤버 레파지토리에 존재하는 멤버 조회(파라미터 = 멤버 고유번호) */
    Optional<Member> findId(Long memberId);

    /** 멤버 레파지토리에 존재하는 멤버 조회(파라미터 = 멤버 이름) */
    List<Member> findName(String memberName);

    /** 멤버 레파지토리에 존재하는 멤버 조회(파라미터 = 멤버 로그인 아이디) */
    Optional<Member> findLogId(String memberLogId);

    /** 회원 정보 수정(업데이트) */
    Optional<Member> update(Long memberId);

    /** 회원 정보 삭제(delete) */
    Long delete(long memberId);
}
