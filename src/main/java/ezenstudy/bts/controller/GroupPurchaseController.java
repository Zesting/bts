package ezenstudy.bts.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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


    @GetMapping("/grouppurchase")
    public String grouppurchase(Model model){
        model.addAttribute("grouppurchases", groupPurchaseService.findGroupPurchases());
        model.addAttribute("category",productService.findCategory());
        model.addAttribute("products", productService.findProducts());
        model.addAttribute("options", productOptionService.findProductOptions());
        model.addAttribute("images", productImageService.findProductImages());
        return "group_purchase/group_purchase";
    }
    @GetMapping("/grouppurchase/new")
    public String grouppurchaseRegister(Model model){
        model.addAttribute("products", productService.findProducts());
        return "group_purchase/group_purchase_register";
    }
    @GetMapping("/grouppurchase/new/{id}")
    public String grouppurchaseRegisterWithId(@PathVariable Long id, Model model){
        model.addAttribute("products", productService.findProducts());
        model.addAttribute("productId", id);
        model.addAttribute("sizes", productOptionService.findSizesbyProductId(id));
        model.addAttribute("colors", productOptionService.findColorsbyProductId(id));
        return "group_purchase/group_purchase_register";
    }
}
