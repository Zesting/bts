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

    /* 멤버 고유 번호로 주소 조회 */
    Optional<Addr> findAddr_memberId(Long memberId);

    /** 주소 정보 수정 */
    Optional<Addr> update(Addr addr);

    /** 모든 주소 삭제 */
    Long delete(Long addrId);

}
