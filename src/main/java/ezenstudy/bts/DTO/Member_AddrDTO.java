package ezenstudy.bts.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class Member_AddrDTO {
    /** 회원 고유 번호 */
    private long MemberId;
    /** 회원 아이디 */
    private String logId;
    /** 회원 비밀번호 */
    private String logPwd;
    /** 회원 이름 */
    private String name;
    /** 회원 나이 */
    private byte age;
    /** 회원 주민번호 앞자리 6개 */
    private String socialNum;
    /** 회원 전화번호 */
    private String phonNum;
    /** 회원 이메일 */
    private String email;
    /** 회원 가입한 날짜 */
    private Date innerDate;
    /** 로그인 한 날짜 */
    private Date logTime;

    /** 회원 주소 고유 번호 */
    private long addrId;
    /** 우편 번호 */
    private String zipCode;
    /** 도로명 주소 */
    private String streetAddr;
    /** 상세 주소 */
    private String detailAddr;
}
