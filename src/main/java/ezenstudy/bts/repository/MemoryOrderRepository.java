package ezenstudy.bts.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    public MemoryOrderRepository() {
        Order o1 = new Order();
        o1.setMemberId(1l);
        o1.setGroupPurchaseId(1L);
        o1.setGroupPurchaseProductOptionId(1l);
        o1.setPaymentId(1l);
        o1.setOrder_completeDate(Timestamp.valueOf(LocalDateTime.of(2023, 4, 29, 15, 48, 0)));
        save(o1);

        Order o2 = new Order();
        o2.setMemberId(1l);
        o2.setGroupPurchaseId(1L);
        o2.setGroupPurchaseProductOptionId(2l);
        o2.setPaymentId(2l);
        o2.setOrder_completeDate(Timestamp.valueOf(LocalDateTime.of(2023, 4, 10, 15, 15, 0)));
        save(o2);

        Order o3 = new Order();
        o3.setMemberId(1l);
        o3.setGroupPurchaseId(3L);
        o3.setGroupPurchaseProductOptionId(1l);
        o3.setPaymentId(3l);
        o3.setOrder_completeDate(Timestamp.valueOf(LocalDateTime.of(2023, 3, 29, 15, 15, 0)));
        save(o3);

        Order o4 = new Order();
        o4.setMemberId(1l);
        o4.setGroupPurchaseId(2L);
        o4.setGroupPurchaseProductOptionId(2l);
        o4.setPaymentId(4l);
        o4.setOrder_completeDate(Timestamp.valueOf(LocalDateTime.of(2023, 2, 9, 15, 1, 0)));
        save(o4);

        Order o5 = new Order();
        o5.setMemberId(2l);
        o5.setGroupPurchaseId(3L);
        o5.setGroupPurchaseProductOptionId(2l);
        o5.setPaymentId(5l);
        o5.setOrder_completeDate(Timestamp.valueOf(LocalDateTime.of(2023, 1, 11, 15, 11, 0)));
        save(o5);

        Order o6 = new Order();
        o6.setMemberId(2l);
        o6.setGroupPurchaseId(2L);
        o6.setGroupPurchaseProductOptionId(2l);
        o6.setPaymentId(6l);
        o1.setOrder_completeDate(Timestamp.valueOf(LocalDateTime.of(2023, 4, 13, 11, 15, 0)));
        save(o6);
    }

}
