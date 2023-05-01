package ezenstudy.bts.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.DTO.ProductDTO;
import ezenstudy.bts.domain.ProductImage;
import ezenstudy.bts.domain.ProductOption;
import ezenstudy.bts.service.ProductImageService;
import ezenstudy.bts.service.ProductOptionService;
import ezenstudy.bts.service.ProductService;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;
    private final ProductOptionService productOptionService;

    public ProductController(ProductService productService, ProductImageService productImageService,
            ProductOptionService productOptionService) {
        this.productService = productService;
        this.productImageService = productImageService;
        this.productOptionService = productOptionService;
    }
    @GetMapping("/product/new")
    public String prouductRegister(Model model){
        model.addAttribute("products", productService.findProducts());
        return "product/product_register";
    }
    @PostMapping("/product/new")
    public String productRegisterPost(ProductDTO productDTO){

        productService.register(productDTO.convertToDomain());
        System.out.println(productDTO.convertToDomain());
        //사진 저장기능 미완성
        List<MultipartFile> imagelist = productDTO.getProduct_images();
        if(imagelist != null){
            imagelist.forEach( image ->{
                ProductImage pi = new ProductImage();
                pi.setProductId(productService.storageSize()+1);
                pi.setImagePath(productImageService.saveFileAndReturnPath(image));
                productImageService.register(pi);
            });
        }

        return "redirect:/product/new";
    }
    @ResponseBody
    @PostMapping("/product/option/new")
    public String productOptionRegister(ProductOption productOption){
        System.out.println("saved: " + productOptionService.register(productOption));
        return "";
    }
}
