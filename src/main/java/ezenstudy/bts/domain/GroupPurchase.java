package ezenstudy.bts.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GroupPurchase {
    private Long id;
    private Long productId; //Foreign Key
    private LocalDateTime saleStart; //판매 시작일
    private LocalDateTime saleEnd; //판매 종료일
    private String information; //공동구매에 대한 설명
    private Integer minQuantity; //공동구매에 필요한 주문 수
    private Long saleRequestId; //기업의 공동구매신청 Foreign Key
}
