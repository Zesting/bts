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

    /* 주소 레파지토리에 존재하는 주소 정보 조회(파라미터 = 메인 주소 이름) 구현 메서드 */
    @Override
    public Optional<Addr> findAddrName(String memberName) {
        return store.values().stream().filter(a -> a.getMemberName().equals(memberName)).findAny();
    }

    /** 주소 정보 수정 구현 메서드 */
    @Override
    public Optional<Addr> update(Long addrId, Addr addr) {
        addr.setAddrId(addrId);
        store.put(addr.getAddrId(), addr);
        return Optional.of(addr);
    }

    /** 주소 삭제 구현 메서드 */
    @Override
    public Long deleteAll(Long addrId) {
        store.remove(addrId);
        return addrId;
    }

}
