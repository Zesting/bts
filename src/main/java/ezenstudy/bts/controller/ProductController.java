package ezenstudy.bts.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.DTO.ProductDTO;
import ezenstudy.bts.domain.ProductImage;
import ezenstudy.bts.service.ProductImageService;
import ezenstudy.bts.service.ProductService;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;

    public ProductController(ProductService productService, ProductImageService productImageService) {
        this.productService = productService;
        this.productImageService = productImageService;
    }
    @GetMapping("/product/new")
    public String prouductRegister(){
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
    @GetMapping("/product/option/new")
    public String productOptionRegister(){
        return "product/product_option_register";
    }
}
