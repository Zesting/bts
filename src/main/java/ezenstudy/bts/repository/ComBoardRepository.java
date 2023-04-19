package ezenstudy.bts.repository;



import ezenstudy.bts.domain.ComBoard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComBoardRepository {
    ComBoard findById(Long id);

    List<ComBoard> findAll();

    ComBoard save(ComBoard comBoard);

    void delete(ComBoard comBoard);

    void deleteById(Long id);


    // Add any custom query methods here
}
