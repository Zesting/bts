package ezenstudy.bts.repository;

import ezenstudy.bts.domain.Comcomment;

import java.util.List;
import java.util.Optional;

public interface ComcommentRepository{
    // Comcomment 객체를 데이터베이스에 저장하는 메서드
    Comcomment save(Comcomment comcomment);

    Optional<Comcomment> findById(Long id);

    List<Comcomment> findAll();

    void deleteById(Long id);

    long getSequence();
}
