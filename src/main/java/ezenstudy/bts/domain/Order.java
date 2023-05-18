package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class Order {
    /** 주문 고유 번호 */
    private long orderId;
    /** 회원 고유 번호 */
    private Long memberId;
    /** 찜 고유 번호 */
    private Long selectId;
    /** 상품 고유 번호 */
    private Long groupPurchaseId;
    /** 주문한 신발 총 수량(찜 + 상품) */
    private byte orderCount;
    /** 주문한 신발 총 가격 */
    private int orderPrice;
}
