package ezenstudy.bts.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ezenstudy.bts.service.GroupPurchaseService;
import ezenstudy.bts.service.ProductImageService;
import ezenstudy.bts.service.ProductOptionService;
import ezenstudy.bts.service.ProductService;

@Controller
public class GroupPurchaseController {
    
    private final GroupPurchaseService groupPurchaseService;
    private final ProductService productService;
    private final ProductOptionService productOptionService;
    private final ProductImageService productImageService;


    public GroupPurchaseController(GroupPurchaseService groupPurchaseService, ProductService productService,
            ProductOptionService productOptionService, ProductImageService productImageService) {
        this.groupPurchaseService = groupPurchaseService;
        this.productService = productService;
        this.productOptionService = productOptionService;
        this.productImageService = productImageService;
    }


    @GetMapping("grouppurchase")
    public String grouppurchase(Model model){
        model.addAttribute("grouppurchases", groupPurchaseService.findGroupPurchases());
        model.addAttribute("category",productService.findCategory());
        model.addAttribute("products", productService.findProducts());
        model.addAttribute("options", productOptionService.findProductOptions());
        model.addAttribute("images", productImageService.findProductImages());
        return "group_purchase";
    }

}
