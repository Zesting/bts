package ezenstudy.bts.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ezenstudy.bts.domain.ReviewBoard;
import ezenstudy.bts.service.ReviewBoardService;

@Controller
public class ReviewBoardController {
    private final ReviewBoardService reviewBoardService;
    
    public ReviewBoardController(ReviewBoardService reviewBoardService) {
        this.reviewBoardService = reviewBoardService;
    }


    @PostMapping("/reviewboard")
    public String reviewBoardPost(ReviewBoard reviewBoard)throws Exception{
        reviewBoardService.save(reviewBoard);
        return "redirect:/reviewboard";
    }

    @GetMapping("/reviewboard")
    public String reviewBoard(Model model){
        model.addAttribute("boards",reviewBoardService.findAll());
        return "reviewBoard";
    }
}
