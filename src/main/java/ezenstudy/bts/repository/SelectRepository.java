package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.Select;

public interface SelectRepository {

    /** 어느 회원 한 명의 찜 정보 저장 */
    Select save(Select select);

    /** 찜 레파지토리에 존재하는 모든 찜 정보 조회 */
    List<Select> findSelectAll();

    /** 입력된 찜 고유 번호를 통해 어느 하나의 찜 정보 조회 */
    Optional<Select> findSelectOne(Long SelectId);

    /** 입력된 회원 번호를 통해 해당 회원의 모든 찜 정보 조회 */
    List<Select> findSelectList(Long memberId);

    /** 찜 정보 수정 */
    Select update(Select select);

    /* 입력된 상품 고유 번호에 해당하는 찜 정보 삭제 */
    Optional<Integer> deleteOne(Long productId);
}
