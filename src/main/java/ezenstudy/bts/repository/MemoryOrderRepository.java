package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import ezenstudy.bts.domain.Order;

public class MemoryOrderRepository implements OrderRepository {
    private static Map<Long, Order> store = new HashMap<>();
    private static Long sequence = 0L;

    /** 어느 하나의 주문 정보 저장 구현 메서드 */
    @Override
    public Order save(Order order) {
        order.setOrderId(++sequence);
        store.put(order.getOrderId(), order);
        return order;
    }

    /** 모든 주문 정보 조회 구현 메서드 */
    @Override
    public List<Order> findOrderAll() {
        return new ArrayList<>(store.values());
    }

    /** 멤버 고유 번호에 해당하는 모든 주문 조회 */
    @Override
    public List<Order> findAll_memberId(Long memberId) {
        return new ArrayList<>(store.values().stream().filter(order -> order.getMemberId().equals(memberId))
                .collect(Collectors.toList()));
    }

    /** 주문 정보조회 구현 메서드 */
    @Override
    public Optional<Order> findOrderOne(Long orderId) {
        return Optional.ofNullable(store.get(orderId));
    }

    /** 주문 정보 수정 구현 메서드 */
    @Override
    public Optional<Order> update(Long orderId, Order order) {
        order.setOrderId(orderId);
        store.put(orderId, order);
        return Optional.of(order);
    }

    /** 주문 정보 삭제 구현 메서드 */
    @Override
    public Long delete(Long orderId) {
        store.remove(orderId);
        return orderId;
    }

}
