package ezenstudy.bts.service;

import ezenstudy.bts.domain.Comcomment;
import ezenstudy.bts.repository.ComcommentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class ComcommentService {

    private final ComcommentRepository comcommentRepository;

    public ComcommentService(ComcommentRepository comcommentRepository) {
        this.comcommentRepository = comcommentRepository;
    }

    public Comcomment saveComcomment(Comcomment comcomment) {
        if (comcomment.getId() == null) {
            comcomment.setId(comcommentRepository.getSequence() + 1);
        }
        comcomment.setCreateAt(LocalDateTime.now());
        return comcommentRepository.save(comcomment);
    }

    public Optional<Comcomment> getComcommentById(Long id) {
        return comcommentRepository.findById(id);
    }

    public List<Comcomment> getAllComcomments() {
        return comcommentRepository.findAll();
    }
}