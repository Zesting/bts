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
    public String createComcomment(Comcomment comcomment, @RequestParam("id") Long id) {
        comcommentService.saveComcomment(comcomment);
        return "redirect:/comboard/show/" + id;
    }


    @PostMapping("/comcomment/{id}")
    public ResponseEntity<Comcomment> createComcomment(@PathVariable Long id, @RequestBody Comcomment comcomment) {
        return null;
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


}
