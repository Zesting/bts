package ezenstudy.bts.repository;



import java.util.List;

import ezenstudy.bts.domain.ComBoard;


public interface ComBoardRepository {
    ComBoard findById(Long id);

    List<ComBoard> findAll();

    ComBoard save(ComBoard comBoard);

    void delete(ComBoard comBoard);

    void deleteById(Long id);


    // Add any custom query methods here
}
