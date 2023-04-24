package ezenstudy.bts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.domain.ReviewBoard;
import ezenstudy.bts.service.ReviewBoardService;

@Controller
public class BoardController {
    private final ReviewBoardService reviewBoardService;

    public BoardController(ReviewBoardService reviewBoardService) {
        this.reviewBoardService = reviewBoardService;
    }

    /*
     * @GetMapping("/")
     * public String index(){
     * return "index";
     * }
     */

    @GetMapping("/board/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/board/save")
    public String reviewBoardPost(ReviewBoard reviewBoard, MultipartFile file) throws Exception {
        reviewBoardService.save(reviewBoard);
        return "redirect:/";
    }

    @GetMapping("/board/")
    public String findAll(Model model) {
        // db전체 게시글 데이터를 가져와서 보여줌
        List<ReviewBoard> boardList = reviewBoardService.findAll();
        model.addAttribute("boardAllList", boardList);
        return "list";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id, Model model) {
        // 게시글의 조회수를 하나 올리고 게시글 데이터를 가져와서 detail.html에 출력
        reviewBoardService.updateCount(id);
        Optional<ReviewBoard> reviewBoard = reviewBoardService.findOne(id);
        model.addAttribute("board", reviewBoard.get());
        return "detail";
    }

    @GetMapping("/board/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Optional<ReviewBoard> reviewBoard = reviewBoardService.findOne(id);
        model.addAttribute("boardUpdate", reviewBoard.get());
        return "update";
    }

    @PostMapping("/board/update")
    public String update(@ModelAttribute ReviewBoard reviewBoard, Model model) {
        reviewBoardService.update(reviewBoard);
        model.addAttribute("board", reviewBoard);
        return "detail";
    }

    @GetMapping("/board/delete/{id}")
    public String delete(@PathVariable Long id) {
        reviewBoardService.delete(id);
        return "redirect:/board/";
    }

}
