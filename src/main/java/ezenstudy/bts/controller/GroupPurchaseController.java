package ezenstudy.bts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ezenstudy.bts.DTO.GroupPurchaseProductOptionDTO;
import ezenstudy.bts.domain.GroupPurchase;
import ezenstudy.bts.domain.GroupPurchaseProductOption;
import ezenstudy.bts.domain.Product;
import ezenstudy.bts.domain.ProductImage;
import ezenstudy.bts.domain.ReviewBoard;
import ezenstudy.bts.domain.ReviewImage;
import ezenstudy.bts.service.GroupPurchaseProductOptionService;
import ezenstudy.bts.service.GroupPurchaseService;
import ezenstudy.bts.service.ProductImageService;
import ezenstudy.bts.service.ProductOptionService;
import ezenstudy.bts.service.ProductService;
import ezenstudy.bts.service.ReviewBoardService;
import ezenstudy.bts.service.ReviewImageService;

@Controller
public class GroupPurchaseController {

    private final GroupPurchaseService groupPurchaseService;
    private final GroupPurchaseProductOptionService groupPurchaseProductOptionService;
    private final ProductService productService;
    private final ProductImageService productImageService;
    private final ReviewBoardService reviewBoardService;
    private final ReviewImageService reviewImageService;
    private final ProductOptionService productOptionService;

    public GroupPurchaseController(GroupPurchaseService groupPurchaseService,
            GroupPurchaseProductOptionService groupPurchaseProductOptionService, ProductService productService,
            ProductOptionService productOptionService, ProductImageService productImageService,
            ReviewBoardService reviewBoardService, ReviewImageService reviewImageService) {
        this.groupPurchaseService = groupPurchaseService;
        this.groupPurchaseProductOptionService = groupPurchaseProductOptionService;
        this.productService = productService;
        this.productOptionService = productOptionService;
        this.productImageService = productImageService;
        this.reviewBoardService = reviewBoardService;
        this.reviewImageService = reviewImageService;
    }

    @GetMapping("/grouppurchase")
    public String grouppurchase(Model model) {
        model.addAttribute("grouppurchases", groupPurchaseService.findGroupPurchases());
        model.addAttribute("category", productService.findCategory());
        model.addAttribute("products", productService.findProducts());
        model.addAttribute("options", productOptionService.findProductOptions());
        model.addAttribute("images", productImageService.findProductImages());
        return "group_purchase/group_purchase";
    }

    @GetMapping("/grouppurchase/new")
    public String grouppurchaseRegister(Model model) {
        model.addAttribute("products", productService.findProducts());
        return "group_purchase/group_purchase_register";
    }

    @GetMapping("/grouppurchase/new/{id}")
    public String grouppurchaseRegisterWithId(@PathVariable Long id, Model model) {
        model.addAttribute("products", productService.findProducts());
        model.addAttribute("productId", id);
        model.addAttribute("sizes", productOptionService.findSizesbyProductId(id));
        model.addAttribute("colors", productOptionService.findColorsbyProductId(id));
        return "group_purchase/group_purchase_register";
    }

    @ResponseBody
    @PostMapping("/grouppurchase/new")
    public String grouppurchaseRegisterPost(GroupPurchase groupPurchase, Model model) {
        Long result = groupPurchaseService.register(groupPurchase);
        System.out.println("new GP saved: " + groupPurchaseService.findOnebyId(result));
        model.addAttribute("id", result);
        return result.toString();
    }

    @ResponseBody
    @PostMapping("/groupurchase/option/new")
    public String grouppurchaseOptionRegisterPost(GroupPurchaseProductOptionDTO DTO) {
        Long productOptionId = productOptionService
                .findIdbyFields(DTO.getProductId(), DTO.getOptionSize(), DTO.getOptionColor());

        GroupPurchaseProductOption GPPO = new GroupPurchaseProductOption();
        GPPO.setGroupPurchaseId(DTO.getGroupPurchaseId());
        GPPO.setProductOptionId(productOptionId);
        GPPO.setQuantity(DTO.getQuantity());
        Long result = groupPurchaseProductOptionService.register(GPPO);
        System.out.println("new GPPO saved: " + groupPurchaseProductOptionService.findOnebyId(result));
        return "";
    }

    @GetMapping("/grouppurchase/view/{id}")
    public String grouppurchaseView(@PathVariable Long id, Model model) {

        if (groupPurchaseService.findOnebyId(id).isPresent()) {
            GroupPurchase gp = groupPurchaseService.findOnebyId(id).get();
            Product product = productService.findOnebyId(gp.getProductId()).get();
            List<ProductImage> images = productImageService.findListbyProductId(gp.getProductId());
            List<GroupPurchaseProductOption> GPPOList = groupPurchaseProductOptionService.findListbyGroupPurchaseId(id);
            // 옵션값을 꺼내서 대입해서 내보냄
            List<GroupPurchaseProductOptionDTO> gppos = new ArrayList<>();
            GPPOList.stream().forEach(gppo -> {
                GroupPurchaseProductOptionDTO dto = gppo.transferToDTO();
                dto.setOptionColor(productOptionService.findOnebyId(gppo.getProductOptionId()).get().getColor());
                dto.setOptionSize(productOptionService.findOnebyId(gppo.getProductOptionId()).get().getSize());
                gppos.add(dto);
            });
            List<ReviewBoard> reviewList = reviewBoardService.findByProductId(product.getId());
            List<ReviewImage> totalImageList = new ArrayList<>();
            for (ReviewBoard reviewBoard : reviewList) {
                Long boardId = reviewBoard.getId();
                List<ReviewImage> imgList = reviewImageService.findByReviewImages(boardId);
                imgList.stream().forEach(img -> totalImageList.add(img));
            }
            model.addAttribute("reviewSize", reviewBoardService.findByProductId(product.getId()).size());
            model.addAttribute("reviewList", reviewList);
            model.addAttribute("imageList", totalImageList);
            model.addAttribute("gp", gp);
            model.addAttribute("product", product);
            model.addAttribute("images", images);
            model.addAttribute("GPPOList", gppos);

        } else {
            model.addAttribute("msg", "해당 판매글을 찾지 못했습니다.");
            return "exception/goback_with_message";
        }

        return "group_purchase/group_purchase_view";
    }

}
