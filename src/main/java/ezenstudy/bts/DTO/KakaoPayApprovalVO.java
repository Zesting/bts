package ezenstudy.bts.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class KakaoPayApprovalVO {
    // response

    //aid 요청고유번호 / cid 가맹점코드 / tid 결제고유변호 / sid 정기결제용 id
    private String aid, tid, cid, sid;
    //가맹점 주문번호 / 가망점 회원id / 결제수단 카드or현금
    private String partner_order_id, partner_user_id, payment_method_type;
    // 결제 금액 정보
    private AmountVO amount;
    // 결제 상세 정보(결제수단이 카드일 경우만 포함)
    private CardVO card_info;
    // 상풍이름 / 상품 코드 / 결제승인 요청에 대해 저장한 값,요청 시 전달된 내용
    private String item_name, item_code, payload;
    // 상품수량 / 상품 비과세 금액 / 상품 부가세 금액
    private Integer quantity, tax_free_amount, vat_amount;
    // 결제준비 요청 시각 / 결제승인 시각
    private Date created_at, approved_at;
}
