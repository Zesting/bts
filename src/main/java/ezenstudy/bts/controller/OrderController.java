package ezenstudy.bts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ezenstudy.bts.domain.GroupPurchase;
/* import ezenstudy.bts.domain.Member; */
import ezenstudy.bts.domain.Order;
import ezenstudy.bts.domain.ProductOption;
import ezenstudy.bts.service.GroupPurchaseProductOptionService;
import ezenstudy.bts.service.GroupPurchaseService;
/* import ezenstudy.bts.service.OrderService; */
import ezenstudy.bts.service.ProductImageService;
import ezenstudy.bts.service.ProductOptionService;
import ezenstudy.bts.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class OrderController {

    /* private final OrderService orderService; */
    private final GroupPurchaseService groupPurchaseService;
    private final GroupPurchaseProductOptionService groupPurchaseProductOptionService;
    private final ProductImageService productImageService;
    private final ProductOptionService productOptionService;
    private final ProductService productService;

    @GetMapping("/order/myOrder")
    public String listForm(Model model, HttpSession session) {
        /* Member member = (Member) session.getAttribute("logInMember"); */
        // List<Order> orderList = orderService.findAllByMember(member.getId());
        Order testod = new Order();
        testod.setGroupPurchaseId(1l);
        testod.setGroupPurchaseProductOptionId(1l);
        testod.setOrderId(1l);
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(testod);
        orderList.stream().forEach(order -> {
            Long gpId = order.getGroupPurchaseId();
            GroupPurchase gp = groupPurchaseService.findOnebyId(gpId).get();
            Long orderId = order.getOrderId();
            ProductOption po = productOptionService.findOnebyId(
                    groupPurchaseProductOptionService.findOnebyId(order.getGroupPurchaseProductOptionId()).get()
                            .getProductOptionId())
                    .get();

            model.addAttribute("gp_price" + orderId, gp.getPrice());
            model.addAttribute("gp_img" + orderId,
                    productImageService.findListbyProductId(gp.getProductId()).get(0).getImagePath());
            model.addAttribute("gp_name" + orderId, productService.findOnebyId(gp.getProductId()).get().getName());
            model.addAttribute("gp_color" + orderId, po.getColor());
            model.addAttribute("gp_size" + orderId, po.getSize());
        });
        return "orders/orderListForm";
    }

    @PostMapping("/order/myOrder")
    public String orderList(HttpSession session) {

        return null;
    }

}
