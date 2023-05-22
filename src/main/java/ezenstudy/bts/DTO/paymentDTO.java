package ezenstudy.bts.DTO;

import lombok.Data;

@Data
public class PaymentDTO {
    private String tid; // 카카오페이 고유번호
    private String next_redirect_mobile_url; // 모바일 웹일 경우 받는 결제페이지 url
    private String next_redirect_pc_url; // pc 웹일 경우 받는 결제 페이지
    private String created_at; // 결제준비 요청시간
}
