package ezenstudy.bts.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Payment {
    private Long id; // pk auto_increment
    private String tid; // 카카오페이 고유번호
    private Long orderId; // 주문번호 (FK)
    private Integer amount;   // 결제금액
    private Integer discount; // 할인금액
    private String paymentType; // 결제방법 카드or현금
    private String cardName; // 카드이름
    private Date approvalDate; // 결제승인완료 시간

}
