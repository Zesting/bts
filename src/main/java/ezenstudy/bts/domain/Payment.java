package ezenstudy.bts.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Payment {

    private Long id; // pk auto_increment

    //컨트롤러에서 request로 받기
    private Long groupPurchaseId; // 공동구매id (FK)
    private Long memberId; // 멤버id(FK)
    private String productName; // 제품명

    //카카오결제api 에서 받아오는 거
    private Integer amount;   // 결제금액
    private Date approvalDate; // 결제승인완료 시간
    private String cardName; // 카드이름
    private String paymentType; // 결제방법 카드or현금
    private String tid; // 카카오페이 고유번호

}
