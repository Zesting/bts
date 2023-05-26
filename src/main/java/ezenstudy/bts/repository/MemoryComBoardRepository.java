package ezenstudy.bts.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import ezenstudy.bts.domain.ComBoard;

public class MemoryComBoardRepository implements ComBoardRepository{
    private final Map<Long, ComBoard> comBoardMap = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);


    @Override
    public ComBoard findById(Long id) {
        return comBoardMap.get(id);
    }

    @Override
    public ComBoard save(ComBoard comBoard) {
        comBoard.setCreateAt(LocalDateTime.now());

        if (comBoard.getId() == null || !comBoardMap.containsKey(comBoard.getId())) {
            comBoard.setId(nextId.getAndIncrement());
        }
        
        comBoardMap.put(comBoard.getId(), comBoard);
        return comBoard;
    }

    @Override
    public List<ComBoard> findAll() {
        return new ArrayList<>(comBoardMap.values());
    }

    @Override
    public ComBoard update(Long id, ComBoard comBoard) {
        ComBoard existing = findById(id);
        if (existing != null) {
            existing.setTitle(comBoard.getTitle());
            existing.setContent(comBoard.getContent());
            existing.setCreatedBy(comBoard.getCreatedBy());
            existing.setBN(comBoard.getBN());
            // existing.setFilled(comBoard.isFilled());
            existing.setAnswer(comBoard.getAnswer());
        }
        comBoardMap.put(id,existing);
        return existing;
    }

    @Override
    public void delete(Long id) {
        comBoardMap.remove(id);
    }

    /* 더미 데이터 */
    public MemoryComBoardRepository() {
        ComBoard co0 = new ComBoard();
        co0.setId(1L);
        co0.setTitle("등록 요청합니다.");
        co0.setContent("콜라보 신발 신청합니다.");
        co0.setCreatedBy("김콜라");
        co0.setBN("123");
        co0.setCreateAt(LocalDateTime.now());
        save(co0);

        ComBoard co1 = new ComBoard();
        co1.setId(2L);
        co1.setTitle("등록 요청합니다.");
        co1.setContent("콜라보 신발 신청합니다.");
        co1.setCreatedBy("김콜라");
        co1.setBN("123");
        co1.setCreateAt(LocalDateTime.now());
        save(co1);

        ComBoard co2 = new ComBoard();
        co2.setId(3L);
        co2.setTitle("등록 요청합니다.");
        co2.setContent("콜라보 신발 신청합니다.");
        co2.setCreatedBy("김콜라");
        co2.setBN("123");
        co2.setCreateAt(LocalDateTime.now());
        save(co2);

    }

}
