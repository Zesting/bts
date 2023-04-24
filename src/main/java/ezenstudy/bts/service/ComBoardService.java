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
            throw new NoSuchElementException("ComBoard with ID " + id + " not found");
        }
    }

    public List<ComBoard> getAllComBoards() {
        return comBoardRepository.findAll();
    }

    public ComBoard save(ComBoard comBoard) {
        return comBoardRepository.save(comBoard);
    }

}
