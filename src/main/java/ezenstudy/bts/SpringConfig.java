package ezenstudy.bts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ezenstudy.bts.repository.GroupPurchaseRepository;
import ezenstudy.bts.repository.MemberRepository;
import ezenstudy.bts.repository.MemoryGroupPurchaseRepository;
import ezenstudy.bts.repository.MemoryMemberRepository;
import ezenstudy.bts.repository.MemoryProductImageRepository;
import ezenstudy.bts.repository.MemoryProductOptionRepository;
import ezenstudy.bts.repository.MemoryProductRepository;
import ezenstudy.bts.repository.MemoryUserBoardRepository;
import ezenstudy.bts.repository.ProductImageRepository;
import ezenstudy.bts.repository.ProductOptionRepository;
import ezenstudy.bts.repository.ProductRepository;
import ezenstudy.bts.repository.UserBoardRepository;
import ezenstudy.bts.service.GroupPurchaseService;
import ezenstudy.bts.service.MemberService;
import ezenstudy.bts.service.ProductImageService;
import ezenstudy.bts.service.ProductOptionService;
import ezenstudy.bts.service.ProductService;
import ezenstudy.bts.service.UserBoardService;

@Configuration
public class SpringConfig {

    @Bean
    public GroupPurchaseService groupPurchaseService() {
        return new GroupPurchaseService(groupPurchaseRepository());
    }

    @Bean
    public GroupPurchaseRepository groupPurchaseRepository() {
        return new MemoryGroupPurchaseRepository();
    }

    @Bean
    public ProductService productService() {
        return new ProductService(productRepository());
    }

    @Bean
    public ProductRepository productRepository() {
        return new MemoryProductRepository();
    }

    @Bean
    public ProductOptionService productOptionService() {
        return new ProductOptionService(productOptionRepository());
    }

    @Bean
    public ProductOptionRepository productOptionRepository() {
        return new MemoryProductOptionRepository();
    }

    @Bean
    public ProductImageService productImageService() {
        return new ProductImageService(productImageRepository());
    }

    @Bean
    public ProductImageRepository productImageRepository() {
        return new MemoryProductImageRepository();
    }

    @Bean
    public UserBoardService userBoardService() {
        return new UserBoardService(userBoardRepository());
    }

    @Bean
    public UserBoardRepository userBoardRepository() {
        return new MemoryUserBoardRepository();
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}
