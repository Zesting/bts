package ezenstudy.bts.service;



import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import ezenstudy.bts.domain.ComBoard;
import ezenstudy.bts.repository.ComBoardRepository;

public class ComBoardService {

    private final ComBoardRepository comBoardRepository;

    public ComBoardService(ComBoardRepository comBoardRepository) {
        this.comBoardRepository = comBoardRepository;
    }

    public ComBoard getComBoardById(Long id) {
        Optional<ComBoard> comBoard = Optional.ofNullable(comBoardRepository.findById(id));
        if (comBoard.isPresent()) {
            return comBoard.get();
        } else {
            throw new NoSuchElementException( id + "를 찾지못했습니다.");
        }
    }

    public List<ComBoard> getAllComBoards() {
        return comBoardRepository.findAll();
    }

    public ComBoard save(ComBoard comBoard) {
        return comBoardRepository.save(comBoard);
    }

    public ComBoard update(Long id, ComBoard updatedComBoard) {
        ComBoard comBoard = getComBoardById(id);
        comBoard.setTitle(updatedComBoard.getTitle());
        comBoard.setContent(updatedComBoard.getContent());
        comBoard.setBN(updatedComBoard.getBN());
        return comBoardRepository.save(comBoard);
    }

    public void delete(Long id) {
        comBoardRepository.delete(id);
    }
}
