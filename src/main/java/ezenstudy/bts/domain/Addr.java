package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class Addr {
    /** 회원 주소 고유 번호 */
    private long addrId;
    /** 회원 고유 번호 */
    private long memberId;
    /** 회원 이름(받는 사람) */
    private String memberName;
    /** 우편 번호 */
    private String zipCode;
    /** 도로명 주소 */
    private String streetAddr;
    /** 상세 주소 */
    private String detailAddr;

}