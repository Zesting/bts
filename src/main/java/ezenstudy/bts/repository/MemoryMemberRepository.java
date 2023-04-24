package ezenstudy.bts.repository;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public List<Member> findName(String memberName) {
        List<Member> memberList = new ArrayList<>();
        for (Member member : store.values()) {
            if (member.getName().equals(memberName)) {
                memberList.add(member);
            }
        }
        return memberList;
    }

    /** 멤버 레파지토리에 존재하는 멤버 조회(파라미터 = 멤버 로그인 아이디) 구현 객체 */
    // 종민이형 의견 .collect.aslist();
    @Override
    public Optional<Member> findLogId(String memberLogId) {
        return store.values().stream().filter(m -> m.getLogId().equals(memberLogId)).findAny();
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

    /* =============== 더미 데이터 ===================== */
    public MemoryMemberRepository() {
        Member m0 = new Member();
        m0.setLogId("ez");
        m0.setLogPwd("1234");
        m0.setName("이젠");
        m0.setAge((byte) 25);
        m0.setSocialNum("001122");
        m0.setPhonNum("010-1234-1234");
        m0.setEmail("LEE@naver.com");
        m0.setInnerDate(LocalDate.now());
        m0.setLogTime(LocalTime.now());
        save(m0);

        Member m1 = new Member();
        m1.setLogId("Lee");
        m1.setLogPwd("1234");
        m1.setName("이순신");
        m1.setAge((byte) 78);
        m1.setSocialNum("771123");
        m1.setPhonNum("010-1234-1234");
        m1.setEmail("LEE@naver.com");
        m1.setInnerDate(LocalDate.now());
        m1.setLogTime(LocalTime.now());
        save(m1);

        Member m2 = new Member();
        m2.setLogId("Kim");
        m2.setLogPwd("4321");
        m2.setName("김유신");
        m2.setAge((byte) 66);
        m2.setSocialNum("661123");
        m2.setPhonNum("010-4321-4321");
        m2.setEmail("KIM@naver.com");
        m2.setInnerDate(LocalDate.now());
        m2.setLogTime(LocalTime.now());
        save(m2);

        Member m3 = new Member();
        m3.setLogId("Park");
        m3.setLogPwd("0000");
        m3.setName("박지성");
        m3.setAge((byte) 41);
        m3.setSocialNum("410101");
        m3.setPhonNum("010-0000-0000");
        m3.setEmail("PARK@naver.com");
        m3.setInnerDate(LocalDate.now());
        m3.setLogTime(LocalTime.now());
        save(m3);

        Member m4 = new Member();
        m4.setLogId("Choi");
        m4.setLogPwd("5678");
        m4.setName("최민식");
        m4.setAge((byte) 54);
        m4.setSocialNum("541123");
        m4.setPhonNum("010-5678-5678");
        m4.setEmail("CHOI@naver.com");
        m4.setInnerDate(LocalDate.now());
        m4.setLogTime(LocalTime.now());
        save(m4);

        Member m5 = new Member();
        m5.setLogId("Kim");
        m5.setLogPwd("5678");
        m5.setName("김유신");
        m5.setAge((byte) 66);
        m5.setSocialNum("660222");
        m5.setPhonNum("010-5678-5678");
        m5.setEmail("KIM@naver.com");
        m5.setInnerDate(LocalDate.now());
        m5.setLogTime(LocalTime.now());
        save(m5);

        Member m6 = new Member();
        m6.setLogId("Park");
        m6.setLogPwd("9012");
        m6.setName("박지원");
        m6.setAge((byte) 32);
        m6.setSocialNum("880312");
        m6.setPhonNum("010-9012-9012");
        m6.setEmail("PARK@naver.com");
        m6.setInnerDate(LocalDate.now());
        m6.setLogTime(LocalTime.now());
        save(m6);
    }

}
