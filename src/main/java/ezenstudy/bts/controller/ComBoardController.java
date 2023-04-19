package ezenstudy.bts.controller;




import ezenstudy.bts.domain.ComBoard;
import ezenstudy.bts.service.ComBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ComBoardController {

    private final ComBoardService comBoardService;

    @Autowired
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
