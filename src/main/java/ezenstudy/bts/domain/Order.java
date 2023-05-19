package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class Order {
    /** 주문 고유 번호 */
    private long orderId;
    /** 회원 고유 번호 */
    private Long memberId;
    /** 공동 구매 고유 번호 */
    private Long groupPurchaseId;
    /** 공동 구매에서의 상품 고유 번호 */
    private Long groupPurchaseProductOptionId;
    /** 주문에 해당하는 결제 고유번호 */
    private Long paymentId;
    /** 주문에 해당하는 배송 고유번호 */
    private Long deliveryId;
}
