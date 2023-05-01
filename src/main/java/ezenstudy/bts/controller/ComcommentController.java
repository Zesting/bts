package ezenstudy.bts.controller;

import ezenstudy.bts.domain.Comcomment;
import ezenstudy.bts.service.ComcommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class ComcommentController {

    private final ComcommentService comcommentService;

    public ComcommentController(ComcommentService comcommentService) {
        this.comcommentService = comcommentService;
    }

    @PostMapping("/comcomment")
    public ResponseEntity<Comcomment> createComcomment(@RequestBody Comcomment comcomment) {
        Comcomment createdComcomment = comcommentService.saveComcomment(comcomment);
        return ResponseEntity.ok(createdComcomment);
    }

    @GetMapping("/comcomment/{id}")
    public ResponseEntity<Optional<Comcomment>> getComcommentById(@PathVariable Long id) {
        Optional<Comcomment> comcomment = comcommentService.getComcommentById(id);
        return ResponseEntity.ok(comcomment);
    }

    @GetMapping("/comcomment")
    public ResponseEntity<List<Comcomment>> getAllComcomments() {
        List<Comcomment> comcomments = comcommentService.getAllComcomments();
        return ResponseEntity.ok(comcomments);
    }


    @DeleteMapping("/comcomment/{id}")
    public ResponseEntity<Void> deleteComcomment(@PathVariable Long id) {
        comcommentService.deleteComcomment(id);
        return ResponseEntity.noContent().build();
    }
}
