package ezenstudy.bts.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ezenstudy.bts.domain.ReviewBoard;

@Repository
public class MemoryReviewBoardRepository implements ReviewBoardRepository {

    private static final Map<Long, ReviewBoard> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public ReviewBoard delete(Long id) {
        return store.remove(id);
    }

    @Override
    public List<ReviewBoard> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<ReviewBoard> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public ReviewBoard update(ReviewBoard newBoard) {
        return store.put(newBoard.getId(), newBoard);
    }

    @Override
    public ReviewBoard save(ReviewBoard board) {
        board.setId(++sequence);
        store.put(board.getId(), board);
        return board;
    }

    @Override
    public Long reviewNum() {
        return sequence;
    }

    @Override
    public Integer findAllSize() {
        return store.size();
    }

    public void clearStore() {
        store.clear();
    }

    // 더미데이터
    public MemoryReviewBoardRepository() {
        ReviewBoard dm1 = new ReviewBoard();
        dm1.setCDate(LocalDateTime.of(2023, 5, 1, 17, 30, 0));
        dm1.setContent("생각했던 가격보다 너무저렴해서 좋았어요!");
        dm1.setFileAttached((byte) 1);
        dm1.setMemberId(1l);
        dm1.setProductId(1l);
        dm1.setStar((byte) 4);
        dm1.setTitle("bts 앞으로 자주이용해요!");
        dm1.setViewCount(30);
        dm1.setWriter("이종민");
        save(dm1);

        ReviewBoard dm2 = new ReviewBoard();
        dm2.setCDate(LocalDateTime.of(2023, 3, 1, 17, 30, 0));
        dm2.setContent("공동구매가 이렇게좋은줄 몰랐어요!");
        dm2.setFileAttached((byte) 1);
        dm2.setMemberId(1l);
        dm2.setProductId(1l);
        dm2.setStar((byte) 5);
        dm2.setTitle("님들여기진짜무조건이용하셈짱임");
        dm2.setViewCount(80);
        dm2.setWriter("조정래");
        save(dm2);

        ReviewBoard dm3 = new ReviewBoard();
        dm3.setCDate(LocalDateTime.of(2023, 4, 1, 17, 30, 0));
        dm3.setContent("공동구매가 이렇게좋은줄 몰랐어요!");
        dm3.setFileAttached((byte) 1);
        dm3.setMemberId(1l);
        dm3.setProductId(1l);
        dm3.setStar((byte) 5);
        dm3.setTitle("님들여기진짜무조건이용하셈짱임");
        dm3.setViewCount(80);
        dm3.setWriter("박준희");
        save(dm3);

        ReviewBoard dm4 = new ReviewBoard();
        dm4.setCDate(LocalDateTime.of(2023, 7, 1, 20, 30, 0));
        dm4.setContent("공동구매가 이렇게좋은줄 몰랐어요!");
        dm4.setFileAttached((byte)1);
        dm4.setMemberId(1l);
        dm4.setProductId(1l);
        dm4.setStar((byte)5);
        dm4.setTitle("님들여기진짜무조건이용하셈짱임");
        dm4.setViewCount(44);
        dm4.setWriter("이재영");
        save(dm4);

        ReviewBoard dm5 = new ReviewBoard();
        dm5.setCDate(LocalDateTime.of(2023, 1, 30, 17, 30, 0));
        dm5.setContent("공동구매가 이렇게좋은줄 몰랐어요!");
        dm5.setFileAttached((byte)1);
        dm5.setMemberId(1l);
        dm5.setProductId(1l);
        dm5.setStar((byte)5);
        dm5.setTitle("님들여기진짜무조건이용하셈짱임");
        dm5.setViewCount(8550);
        dm5.setWriter("김정수");
        save(dm4);
    }
}
