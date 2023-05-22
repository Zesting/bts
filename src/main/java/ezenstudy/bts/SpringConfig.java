package ezenstudy.bts;

import ezenstudy.bts.repository.*;
import ezenstudy.bts.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public GroupPurchaseProductOptionService groupPurchaseProductOptionService() {
        return new GroupPurchaseProductOptionService(groupPurchaseProductOptionRepository());
    }

    @Bean
    public GroupPurchaseProductOptionRepository groupPurchaseProductOptionRepository() {
        return new MemoryGroupPurchaseProductOptionRepository();
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

    @Bean
    public AddrRepository addrRepository() {
        return new MemoryAddrRepository();
    }

    @Bean
    public AddrService addrService() {
        return new AddrService(addrRepository());
    }

    @Bean
    public OrderRepository orderRepository() {
        return new MemoryOrderRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderService(orderRepository());
    }

    @Bean
    public UserBoardCommentService userBoardCommentService() {
        return new UserBoardCommentService(userBoardCommentRepository());
    }

    @Bean
    public UserBoardCommentRepository userBoardCommentRepository() {
        return new MemoryUserBoardCommentRepository();
    }

    @Bean
    public ComBoardRepository comBoardRepository() {
        return new MemoryComBoardRepository();
    }

    @Bean
    public ComBoardService comBoardService() {
        return new ComBoardService(comBoardRepository());
    }

    @Bean
    public TrackingRepository trackingRepository() {
        return new MemoryTrackingRepository();
    }

    @Bean
    public TrackingService trackingService() {
        return new TrackingService(trackingRepository());
    }

}
