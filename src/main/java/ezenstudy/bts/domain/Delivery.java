package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class Delivery {
    private long id; //
    private long addrId; //받는사람주소
    private long orderId; //주문정보
    private String status; //배송상태

}
