package ezenstudy.bts.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import ezenstudy.bts.domain.ProductImage;
import ezenstudy.bts.repository.ProductImageRepository;

public class ProductImageService {
    
    private ProductImageRepository productImageRepository;

    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }
    
    public Long register(ProductImage productImage){
        return productImageRepository.save(productImage).getId();
    }
    public Optional<ProductImage> findOnebyId(Long id){
        return productImageRepository.findbyId(id);
    }
    public List<ProductImage> findProductImages(){
        return productImageRepository.findAll();
    }
    public List<ProductImage> findListbyProductId(Long productId){
        return productImageRepository.findbyProductId(productId);
    }
    public String saveFileAndReturnPath(MultipartFile file){
        String fileName = file.getOriginalFilename();
        long id = productImageRepository.storageSize()+1;
        Path path = Paths.get("src/main/resources/static/upload/productimage/"+id+"_"+fileName);
        try{
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("productImage 파일 저장에 실패하였습니다.");
        }
        return "/upload/productimage/"+id+"_"+fileName;
    }
}
