package ezenstudy.bts.repository;

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

    //더미데이터
    public MemoryReviewBoardRepository(){

    }
}
