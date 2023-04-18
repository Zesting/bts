package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ezenstudy.bts.domain.Select;

public class MemorySelectRepository implements SelectRepository {
    private static Map<Long, Select> store = new HashMap<>();
    private static Long sequence = 0L;

    /** 어느 회원 한 명의 찜 정보 저장 구현 메서드 */
    @Override
    public Select save(Select select) {
        select.setSelectId(++sequence);
        store.put(select.getSelectId(), select);
        return select;
    }

    /** 찜 레파지토리에 존재하는 모든 찜 정보 조회 구현 메서드 */
    @Override
    public List<Select> findSelectAll() {
        return new ArrayList<>(store.values());
    }

    /** 입력된 찜 고유 번호를 통해 어느 하나의 찜 정보 조회 구현 메서드 */
    @Override
    public Optional<Select> findSelectOne(Long SelectId) {
        return Optional.ofNullable(store.get(SelectId));
    }

    /** 입력된 회원 번호를 통해 해당 회원의 모든 찜 정보 조회 구현 메서드 */
    @Override
    public List<Select> findSelectList(Long memberId) {
        return new ArrayList<>(
                store.values().stream().filter(s -> s.getMemberID().equals(memberId)).collect(Collectors.toList()));
    }

    /** 찜 정보 수정 구현 메서드 */
    @Override
    public Optional<Select> update(Long selectId, Select select) {
        select.setSelectId(selectId);
        store.put(select.getSelectId(), select);
        return Optional.of(select);

    }

    /* 입력된 상품 고유 번호에 해당하는 찜 정보 삭제 구현 메서드 */
    @Override
    public Long deleteOne(Long orderId) {
        store.remove(orderId);
        return orderId;
    }

}
