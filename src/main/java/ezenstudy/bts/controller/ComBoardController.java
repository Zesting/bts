package ezenstudy.bts.controller;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ezenstudy.bts.domain.ComBoard;
import ezenstudy.bts.service.ComBoardService;
import org.springframework.web.server.ResponseStatusException;


@Controller
public class ComBoardController {

    private final ComBoardService comBoardService;

    public ComBoardController(ComBoardService comBoardService) {
        this.comBoardService = comBoardService;
    }

    @GetMapping("/comboard/view/{id}")
    public String getcomBoard(@PathVariable("id") Long id, Model model) {
        Optional<ComBoard> comBoard = Optional.ofNullable(comBoardService.getComBoardById(id));
        model.addAttribute("comBoard", comBoard);
        return "comBoard";
    }

    @GetMapping("/comboard/list")
    public String listComBoards(Model model) {
        List<ComBoard> comBoardList = comBoardService.getAllComBoards();
        model.addAttribute("comBoardList", comBoardList);
        return "comboard/comlist";
    }

    @GetMapping("/comboard/create")
    public String showCreateForm() {
        return "comboard/ComBoardwrite";
    }

    @PostMapping("/show")
    public String createComBoard(ComBoard comBoard) {
        ComBoard savedComBoard = comBoardService.save(comBoard);
        Long id = savedComBoard.getId();
        return "redirect:/comboard/show/" + id;
    }

    @GetMapping("/comboard/show/{id}")
    public String showComBoard(@PathVariable("id") Long id, Model model) {
        Optional<ComBoard> comBoard = Optional.ofNullable(comBoardService.getComBoardById(id));
        if (comBoard.isPresent()) {
            model.addAttribute("comBoard", comBoard.get());
            return "comboard/comshow";
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ComBoard not found");
        }
    }
}
