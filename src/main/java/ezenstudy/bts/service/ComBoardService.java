package ezenstudy.bts.service;



import java.util.List;

import ezenstudy.bts.domain.ComBoard;
import ezenstudy.bts.repository.ComBoardRepository;


public class ComBoardService {

    private final ComBoardRepository comBoardRepository;

   
    public ComBoardService(ComBoardRepository comBoardRepository) {
        this.comBoardRepository = comBoardRepository;
    }

    public List<ComBoard> getAllComBoards() {
        return comBoardRepository.findAll();
    }

    public ComBoard getComBoardById(Long id) {
        return comBoardRepository.findById(id);
    }

    public ComBoard createComBoard(ComBoard comBoard) {
        return comBoardRepository.save(comBoard);
    }

    public ComBoard updateComBoard(Long id, ComBoard comBoard) {
        ComBoard existingComBoard = comBoardRepository.findById(id);
        if (existingComBoard == null) {
            return null;
        }
        existingComBoard.setTitle(comBoard.getTitle());
        existingComBoard.setContent(comBoard.getContent());
        existingComBoard.setCreateAt(comBoard.getCreateAt());
        existingComBoard.setCreatedBy(comBoard.getCreatedBy());
        existingComBoard.setBN(comBoard.getBN());
        return comBoardRepository.save(existingComBoard);
    }

    public boolean deleteComBoard(Long id) {
        ComBoard comBoard = comBoardRepository.findById(id);
        if (comBoard != null) {
            comBoardRepository.delete(comBoard);
            return true;
        }
        return false;
    }
}