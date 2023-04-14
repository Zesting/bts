package project.study.repository;

import java.util.List;
import java.util.Optional;

import project.study.domain.Addr;

public interface AddrRepository {
    /** 어느 하나의 주소 저장 */
    Addr save(Addr addr);

    /** 모든 주소 조회(메인 주소, 서브 주소(1,2)) */
    List<Addr> findAddrAll();

    /** 수령인 이름으로 메인 또는 서브 주소 조회 */
    Optional<String> findAddr(String addrName);

    /** 주소 정보로 수령인 조회 */
    Optional<String> findName(String addr);

    /** 주소 정보 수정 */
    Addr update(Addr addr);

    /** 모든 주소 삭제 */
    Optional<Integer> deleteAll(Long addrId);

    /** 입력된 수령인 이름의 주소 삭제 */
    Optional<Integer> deleteOne(String addrName);

    /** 입력된 주소 정보로 해당 주소 삭제 */
    Optional<Integer> deleteAddr(String addr);

}
