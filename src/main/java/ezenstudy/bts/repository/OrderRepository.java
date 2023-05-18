package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.Order;

public interface OrderRepository {
    /** 어느 하나의 주문 정보 저장 */
    Order save(Order order);

    /** 모든 주문 정보 조회 */
    List<Order> findOrderAll();

    /** 멤버 고유 번호에 해당하는 모든 주문 정보 조회 */
    List<Order> findAll_memberId(Long memberId);

    /** 주문 정보조회 */
    Optional<Order> findOrderOne(Long orderId);

    /** 주문 정보 수정 */
    Optional<Order> update(Long orderId, Order order);

    /** 주문 정보 삭제 */
    Long delete(Long orderId);

}
