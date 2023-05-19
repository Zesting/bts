package ezenstudy.bts.service;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.Order;
import ezenstudy.bts.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /* 주문 추가 기능 */
    public Long orderJoin(Order order) {
        orderRepository.save(order);
        return order.getOrderId();
    }

    /* 모든 주문 조회 기능 */
    public List<Order> findAllOrder() {
        return orderRepository.findOrderAll();
    }

    /* 주문 조회 기능_주문 고유 번호 */
    public Optional<Order> findOneOrder(Long orderId) {
        return orderRepository.findOrderOne(orderId);
    }

    /** 주문 조회 기능_멤버 고유 번호 */
    public List<Order> findAllByMember(Long memberId) {
        return orderRepository.findAll_memberId(memberId);
    }

    /** 주문 리스트에 존재하는 공동 구매Id 카운팅 */
    public int countByGroupPurchaseId(Long groupPurchaseId) {
        return (int) orderRepository.findOrderAll().stream().filter(o -> o.getGroupPurchaseId() == groupPurchaseId)
                .count();
    }

    /** 주문 삭제(취소) 기능 */
    public void dropOrder(Order order) {
        orderRepository.delete(order.getOrderId());
    }

}
