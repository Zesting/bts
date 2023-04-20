package ezenstudy.bts.controller;




import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ezenstudy.bts.domain.ComBoard;
import ezenstudy.bts.service.ComBoardService;


public class ComBoardController {

    private final ComBoardService comBoardService;

    
    public ComBoardController(ComBoardService comBoardService) {
        this.comBoardService = comBoardService;
    }

    @GetMapping("/comboards")
    public String showComBoards(Model model) {
        List<ComBoard> comBoards = comBoardService.getAllComBoards();
        model.addAttribute("comBoards", comBoards);
        return "comboard-list";
    }

    // ...
}
