package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.Addr;

public class MemoryAddrRepository implements AddrRepository {
    private static Map<Long, Addr> store = new HashMap<>();
    private static Long sequence = 0L;

    /** 어느 하나의 주소 저장 구현 메서드 */
    @Override
    public Addr save(Addr addr) {
        addr.setAddrId(++sequence);
        store.put(addr.getAddrId(), addr);
        return addr;
    }

    /** 모든 주소 조회 (회원의 모든 주소) 구현 메서드 */
    @Override
    public List<Addr> findAddrAll() {
        return new ArrayList<>(store.values());
    }

    /* 주소 레파지토리에 존재하는 주소 정보 조회(파라미터 = 주소 고유번호) 구현 메서드 */
    @Override
    public Optional<Addr> findAddrId(Long addrId) {
        return Optional.ofNullable(store.get(addrId));
    }

    /* 주소 레파지토리에 존재하는 주소 정보 조회(파라미터 = 멤버 고유번호) 구현 메서드 */
    @Override
    public Optional<Addr> findAddr_memberId(Long memberId) {
        return Optional.ofNullable(store.get(memberId));
    };

    /** 주소 정보 수정 구현 메서드 */
    @Override
    public Optional<Addr> update(Addr addr) {
        Addr updateAddr = new Addr();
        updateAddr.setAddrId(addr.getAddrId());
        store.put(updateAddr.getAddrId(), addr);
        return Optional.of(addr);
    }

    /** 주소 삭제 구현 메서드 */
    @Override
    public Long delete(Long addrId) {
        store.remove(addrId);
        return addrId;
    }

    /* =============== 더미 데이터 ===================== */
    public MemoryAddrRepository() {
        Addr a0 = new Addr();
        a0.setMemberId(1);
        a0.setMemberName("이젠");
        a0.setZipCode("12345");
        a0.setStreetAddr("서울특별시 강남구 역삼동 123-45");
        a0.setDetailAddr("상세주소 1층");
        save(a0);

        Addr a1 = new Addr();
        a1.setMemberId(2);
        a1.setMemberName("이순신");
        a1.setZipCode("54321");
        a1.setStreetAddr("서울특별시 강북구 번동 543-21");
        a1.setDetailAddr("상세주소 2층");
        save(a1);

        Addr a2 = new Addr();
        a2.setMemberId(3);
        a2.setMemberName("김유신");
        a2.setZipCode("67890");
        a2.setStreetAddr("서울특별시 강서구 가양동 678-90");
        a2.setDetailAddr("상세주소 3층");
        save(a2);

        Addr a3 = new Addr();
        a3.setMemberId(4);
        a3.setMemberName("박지성");
        a3.setZipCode("13579");
        a3.setStreetAddr("서울특별시 송파구 잠실동 135-79");
        a3.setDetailAddr("상세주소 4층");
        save(a3);

        Addr a4 = new Addr();
        a4.setMemberId(5);
        a4.setMemberName("최민식");
        a4.setZipCode("24680");
        a4.setStreetAddr("서울특별시 관악구 봉천동 246-80");
        a4.setDetailAddr("상세주소 5층");
        save(a4);

        Addr a5 = new Addr();
        a5.setMemberId(6);
        a5.setMemberName("김유신");
        a5.setZipCode("98765");
        a5.setStreetAddr("서울특별시 성동구 행당동 987-65");
        a5.setDetailAddr("상세주소 6층");
        save(a5);

        Addr a6 = new Addr();
        a6.setMemberId(7);
        a6.setMemberName("박지원");
        a6.setZipCode("44587");
        a6.setStreetAddr("강원도 춘천시 대로면 구나읍 창쿠리 64-88");
        a6.setDetailAddr("논밭 지나 전원주택");
        save(a6);
    }

}
