package project.study.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Select {
    /** 찜 고유 번호 */
    private long selectId;
    /** 찜한 상품 고유 번호 */
    private Long productId;
    /** 상품을 찜한 회원 고유 번호 */
    private Long memberID;
    /** 상품을 찜한 시간 */
    private Date selectTime;
    /** 찜한 상품의 총 수량 */
    private byte selectCount;
    /** 찜한 상품의 총 가격 */
    private int selectPrice;

}
