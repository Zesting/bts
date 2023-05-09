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

import ezenstudy.bts.DTO.ReviewBoardDTO;
import ezenstudy.bts.domain.ReviewBoard;
import ezenstudy.bts.domain.ReviewImage;
import ezenstudy.bts.service.ReviewBoardService;
import ezenstudy.bts.service.ReviewImageService;

@Controller
public class ReviewBoardController {
    private final ReviewBoardService reviewBoardService;
    private final ReviewImageService reviewImageService;

    public ReviewBoardController(ReviewImageService reviewImageService, ReviewBoardService reviewBoardService) {
        this.reviewImageService = reviewImageService;
        this.reviewBoardService = reviewBoardService;
    }


    @GetMapping("/nav")
    public String menu(){
        return "menu";
    };

    @GetMapping("/reviewboard")
    public String index() {
        return "reviewboard/reviewhome";
    }

    @GetMapping("/reviewboard/save")
    public String saveForm() {
        return "reviewboard/reviewsave";
    }

    @PostMapping("/reviewboard/save")
    public String save(ReviewBoardDTO reviewBoardDTO) throws Exception {
        if (reviewBoardDTO.getFile().isEmpty()) {
            reviewBoardService.save(reviewBoardDTO.toSaveReviewBaord());
        } else {
            reviewBoardService.save(reviewBoardDTO.toSaveFileReviewBaord());
            ReviewImage reviewImage;
            Long reviewBoardId = reviewBoardService.reviewBoardNum();
            for (MultipartFile file : reviewBoardDTO.getFile()) {
                reviewImage = new ReviewImage();
                reviewImage.setReviewBoardId(reviewBoardId);
                reviewImage.setFile(file);
                reviewImageService.fileSave(reviewImage);
            }
        }
        return "redirect:/reviewboard";
    }

    @GetMapping("/reviewboardlist")
    public String findAll(Model model) {
        // db전체 게시글 데이터를 가져와서 보여줌
        int reviewSize = reviewBoardService.reviewSize();
        model.addAttribute("reviewSize", reviewSize);
        List<ReviewBoard> boardList = reviewBoardService.findAll();
        model.addAttribute("boardList", boardList);
        List<ReviewImage> imageList = reviewImageService.findAll();
        model.addAttribute("imageList", imageList);
        return "reviewboard/reviewlist";
    }

    // @GetMapping("/reviewboard/reviewpaging")
    // public String paging(Model model,@RequestParam(defaultValue = "1")){

    // }

    @GetMapping("/reviewboard/{id}")
    public String findById(@PathVariable Long id, Model model) {
        // 게시글의 조회수를 하나 올리고 게시글 데이터를 가져와서 detail.html에 출력
        reviewBoardService.updateCount(id);
        Optional<ReviewBoard> reviewBoard = reviewBoardService.findOne(id);
        model.addAttribute("board", reviewBoard.get());
        List<ReviewImage> reviewImage = reviewImageService.findByReviewImages(id);
        model.addAttribute("images", reviewImage);
        return "reviewboard/reviewdetail";
    }   

    @GetMapping("/reviewboard/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Optional<ReviewBoard> reviewBoard = reviewBoardService.findOne(id);
        model.addAttribute("boardUpdate", reviewBoard.get());
        return "reviewboard/reviewupdate";
    }

    @PostMapping("/reviewboard/reviewupdate")
    public String update(@ModelAttribute ReviewBoard reviewBoard, Model model) {
        reviewBoardService.update(reviewBoard);
        model.addAttribute("board", reviewBoard);
        return "reviewboard/reviewdetail";
    }

    @GetMapping("/reviewboard/delete/{id}")
    public String delete(@PathVariable Long id) {
        reviewBoardService.delete(id);
        return "redirect:/reviewboard";
    }

}
