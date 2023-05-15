package ezenstudy.bts.repository;

import ezenstudy.bts.domain.ComBoard;
import ezenstudy.bts.service.ComBoardService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryComBoardRepository implements ComBoardRepository{
    private final Map<Long, ComBoard> comBoardMap = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);


    @Override
    public ComBoard findById(Long id) {
        return comBoardMap.get(id);
    }

    @Override
    public ComBoard save(ComBoard comBoard) {
        if (comBoard.getId() == null) {
            comBoard.setId(nextId.getAndIncrement());
        }
        comBoard.setCreateAt(LocalDateTime.now());
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
            existing.setFilled(comBoard.isFilled());
        }
        comBoardMap.put(id,existing);
        return existing;
    }

    @Override
    public void delete(Long id) {
        comBoardMap.remove(id);
    }



}
