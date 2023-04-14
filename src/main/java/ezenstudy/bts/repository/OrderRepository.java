package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.Member;
import ezenstudy.bts.domain.Order;

public interface OrderRepository {
    /** 어느 하나의 주문 정보 저장 */
    Order save(Order order);

    /** 모든 주문 정보 조회 */
    List<Order> findOrderAll();

    /** 회원 고유 번호로 주문 정보조회 */
    Optional<Order> findOrderOne(Long memberId);

    /** 주문 고유 번호로 회원 정보 조회 */
    Optional<Member> findMember(Long orderId);

    /** 주문 정보 수정 */
    Optional<Order> update(Order order);

    /** 입력된 회원 고유 번호의 주문 정보 삭제 */
    Optional<Integer> delete(Long memberId);

}
