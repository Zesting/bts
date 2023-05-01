package ezenstudy.bts.repository;



import java.util.List;

import ezenstudy.bts.domain.ComBoard;


public interface ComBoardRepository {
    ComBoard findById(Long id);

    ComBoard save(ComBoard comBoard);

    List<ComBoard> findAll();

    ComBoard update(Long id, ComBoard comBoard);

    void delete(Long id);
}



