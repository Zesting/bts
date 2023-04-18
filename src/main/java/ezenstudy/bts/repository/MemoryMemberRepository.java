package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.Member;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    /** 어느 하나의 멤버 객체 저장 구현 객체 */
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /** 멤버 레파지토리에 존재하는 모든 멤버 조회 구현 객체 */
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());

    }

    /* 멤버 레파지토리에 존재하는 멤버 조회(파라미터 = 멤버 고유번호) 구현 객체 */
    @Override
    public Optional<Member> findId(Long memberId) {
        return Optional.ofNullable(store.get(memberId));
    }

    /* 멤버 레파지토리에 존재하는 멤버 조회(파라미터 = 멤버 이름) 구현 객체 */
    @Override
    public Optional<Member> findName(String memberName) {
        return store.values().stream().filter(m -> m.getName().equals(memberName)).findAny();
    }

    /** 회원 정보 수정(업데이트) 구현 객체 */
    @Override
    public Optional<Member> update(Long memberId, Member member) {
        member.setId(memberId);
        store.put(member.getId(), member);
        return Optional.of(member);
    }

    /** 회원 정보 삭제(delete) 구현 객체 */
    @Override
    public Long delete(long memberId) {
        store.remove(memberId);
        return memberId;
    }

}
