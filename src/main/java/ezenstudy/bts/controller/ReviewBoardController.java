package ezenstudy.bts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.DTO.ReviewBoardDTO;
import ezenstudy.bts.domain.Member;
import ezenstudy.bts.domain.ReviewBoard;
import ezenstudy.bts.domain.ReviewImage;
import ezenstudy.bts.service.GroupPurchaseService;
import ezenstudy.bts.service.ReviewBoardService;
import ezenstudy.bts.service.ReviewImageService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewBoardController {
    private final ReviewBoardService reviewBoardService;
    private final ReviewImageService reviewImageService;
    private final GroupPurchaseService gpService;

    public ReviewBoardController(ReviewBoardService reviewBoardService, ReviewImageService reviewImageService,
            GroupPurchaseService gpService) {
        this.reviewBoardService = reviewBoardService;
        this.reviewImageService = reviewImageService;
        this.gpService = gpService;
    }

    @GetMapping("/reviewboard")
    public String index() {
        return "reviewboard/reviewhome";
    }

    @GetMapping("/reviewboard/save")
    public String saveForm(@RequestParam("gpid")Long gpid,Model model) {
        model.addAttribute("gpid", gpid);
        return "reviewboard/reviewsave";
    }

    @PostMapping("/reviewboard/save")
    public String save(ReviewBoardDTO reviewBoardDTO,HttpSession session,@RequestParam("gpid")Long gpid) throws Exception {
        System.out.println("dddddddddddddddddddd"+gpid);

        String imgfile = reviewBoardDTO.getFile().get(0).getOriginalFilename(); 
        Member member = (Member)session.getAttribute("logInMember");
        Long productId = gpService.findOnebyId(gpid).get().getProductId();
        
        if (imgfile != null) {
            if (imgfile.equals("")) {
                reviewBoardService.save(reviewBoardDTO.toSaveReviewBaord(member,productId));
                ReviewImage reviewImage = new ReviewImage();
                Long reviewBoardId = reviewBoardService.reviewBoardNum();
                reviewImage.setReviewBoardId(reviewBoardId);
                reviewImageService.nullSave(reviewImage);
            } else {
                reviewBoardService.save(reviewBoardDTO.toSaveFileReviewBaord(member,productId));
                ReviewImage reviewImage;
                Long reviewBoardId = reviewBoardService.reviewBoardNum();
                for (MultipartFile file : reviewBoardDTO.getFile()) {
                    reviewImage = new ReviewImage();
                    reviewImage.setReviewBoardId(reviewBoardId);
                    reviewImage.setFile(file);
                    reviewImageService.fileSave(reviewImage);
                }
            }
        }
        return "redirect:/reviewboard";
    }

    // 전체리뷰
    @GetMapping("/reviewboardlist")
    public String findAll(Model model) {
        // db전체 게시글 데이터를 가져와서 보여줌
        int reviewSize = reviewBoardService.reviewSize();
        List<ReviewBoard> boardList = reviewBoardService.findAll();
        List<ReviewImage> imageList = reviewImageService.findAll();
        
        model.addAttribute("reviewSize", reviewSize);
        model.addAttribute("boardList", boardList);
        model.addAttribute("imageList", imageList);
        return "reviewboard/reviewlist";
    }

    // 공동구매컨트롤러로 이동
    /*상품별 리뷰
    @GetMapping("/productReview")
    public String productList(@RequestParam("productId") Long productId, Model model) {
        model.addAttribute("reviewSize", reviewBoardService.findByProductId(productId).size());
        List<ReviewBoard> reviewList = reviewBoardService.findByProductId(productId);
        model.addAttribute("reviewList", reviewList);
        List<ReviewImage> totalImageList = new ArrayList<>();
        for(ReviewBoard reviewBoard : reviewList){
            Long boardId = reviewBoard.getId();
            List<ReviewImage> imgList = reviewImageService.findByReviewImages(boardId);
            imgList.stream().forEach(img -> totalImageList.add(img));
        }
        model.addAttribute("imageList", totalImageList);

        return "reviewboard/productReview";
    }*/

    @GetMapping("/reviewboard/{id}")
    public String findById(@PathVariable Long id, Model model) {
        // 게시글의 조회수를 하나 올리고 게시글 데이터를 가져와서 detail.html에 출력
        reviewBoardService.updateCount(id);
        Optional<ReviewBoard> reviewBoard = reviewBoardService.findOne(id);
        List<ReviewImage> reviewImage = reviewImageService.findByReviewImages(id);

        model.addAttribute("board", reviewBoard.get());
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
