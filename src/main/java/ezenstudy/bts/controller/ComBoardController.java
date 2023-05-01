package ezenstudy.bts.controller;




import java.util.List;
import java.util.Optional;

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



    @GetMapping("/comboard/list")
    public String listComBoards(Model model) {
        List<ComBoard> comBoardList = comBoardService.getAllComBoards();
        model.addAttribute("comBoardList", comBoardList);
        return "comboard/comlist";
    }

    @PostMapping("/comboard/list")
    public String listComBoardsPost(Model model) {
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ComBoard no");
        }
    }

    /** 수정 */
    @GetMapping("/comboard/modify/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<ComBoard> comBoard = Optional.ofNullable(comBoardService.getComBoardById(id));
        if (comBoard.isPresent()) {
            model.addAttribute("comBoard", comBoard.get());
            return "comboard/comupdate";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ComBoard no");
        }
    }

    @PostMapping("/comboard/modify/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("comBoard") ComBoard comBoard) {
        comBoardService.update(id, comBoard);
        return "redirect:/comboard/show/" + id;
    }


    /** 삭제 */

    @PostMapping(value = "/comboard/delete/{boardId}", params = "_method=DELETE")
    public String deletePost(@PathVariable("boardId") Long boardId) {
        comBoardService.delete(boardId);
        return "redirect:/comboard/list";
    }


}
