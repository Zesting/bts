package ezenstudy.bts.DTO;

import lombok.Data;

@Data
public class AmountVO {
    // 전체 결제금액 / 비과세 금액 / 부가세 금액 / 사용한 포인트금액 / 할인금액
    private Integer total, tax_free, vat, point, discount;
}
