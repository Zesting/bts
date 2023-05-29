package ezenstudy.bts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ezenstudy.bts.DTO.mainGalleryDTO;
import ezenstudy.bts.domain.GroupPurchase;
import ezenstudy.bts.domain.Member;
import ezenstudy.bts.domain.Product;
import ezenstudy.bts.service.GroupPurchaseService;
import ezenstudy.bts.service.OrderService;
import ezenstudy.bts.service.ProductImageService;
import ezenstudy.bts.service.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final GroupPurchaseService groupPurchaseService;
    private final ProductImageService productImageService;
    private final ProductService productService;
    private final OrderService orderService;

    public HomeController(GroupPurchaseService groupPurchaseService, ProductImageService productImageService,
            ProductService productService, OrderService orderService) {
        this.groupPurchaseService = groupPurchaseService;
        this.productImageService = productImageService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {

        List<mainGalleryDTO> galleryList = new ArrayList<>(); 
        List<GroupPurchase> gpList = groupPurchaseService.findGroupPurchases();

        gpList.stream().forEach(gp ->{
            mainGalleryDTO dto = new mainGalleryDTO();
            Product product = productService.findOnebyId(gp.getProductId()).get();
            dto.setProductName(product.getName());
            dto.setBrand(product.getBrand());
            dto.setCategory(product.getCategory());
            dto.setOriginalPrice(product.getOriginalPrice());

            dto.setOrderCount(orderService.countByGroupPurchaseId(gp.getId()));

            dto.setImagePath(productImageService.findListbyProductId(gp.getProductId()).get(0).getImagePath());

            dto.setGroupPurchaseId(gp.getId());
            dto.setSalePrice(gp.getPrice());
            dto.setInformation(gp.getInformation());
            dto.setSaleEndTime(gp.getSaleEnd());
            dto.setMinQuantity(gp.getMinQuantity());
            galleryList.add(dto);
        });

        model.addAttribute("galleryList", galleryList);

        Member logInMember = (Member) session.getAttribute("logInMember");
        if (logInMember == null) {
            return "home";
        }

        model.addAttribute("member", logInMember);
        return "home";
    }

}
