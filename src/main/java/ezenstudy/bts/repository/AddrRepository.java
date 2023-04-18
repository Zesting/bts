package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.Addr;

public interface AddrRepository {
    /** 어느 하나의 주소 저장 */
    Addr save(Addr addr);

    /** 모든 주소 조회 (회원의 모든 주소) */
    List<Addr> findAddrAll();

    /* 주소 레파지토리에 존재하는 주소 정보 조회(파라미터 = 주소 고유번호) */
    Optional<Addr> findAddrId(Long addrId);

    /* 주소 레파지토리에 존재하는 주소 정보 조회(파라미터 = 메인 주소 이름) */
    Optional<Addr> findAddrName(String addrName);

    /** 주소 정보 수정 */
    Optional<Addr> update(Long addrId, Addr addr);

    /** 모든 주소 삭제 */
    Long deleteAll(Long addrId);

}
