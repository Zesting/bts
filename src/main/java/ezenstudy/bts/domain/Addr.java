package ezenstudy.bts.domain;

import lombok.Data;

@Data
public class Addr {
    /** 회원 주소 고유 번호 */
    private long addrId;
    /** 회원 고유 번호 */
    private long memberId;
    /** 회원 이름 = 메인 주소의 수령인 */
    private String memberName;
    /** 메인 주소 */
    private String mainAddr;
    /** 메인 주소의 수령인 */
    private String mainName;
    /** 서브 주소1 */
    private String subAddr1;
    /** 서브 주소1의 수령인 */
    private String subName1;
    /** 서브 주소2 */
    private String subAddr2;
    /** 서브 주소2의 수령인 */
    private String subName2;
}
