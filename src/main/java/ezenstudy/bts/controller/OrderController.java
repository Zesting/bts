package ezenstudy.bts.controller;

/* import java.util.ArrayList; */
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ezenstudy.bts.domain.GroupPurchase;
import ezenstudy.bts.domain.Member;
import ezenstudy.bts.domain.Order;
import ezenstudy.bts.domain.ProductOption;
import ezenstudy.bts.service.GroupPurchaseProductOptionService;
import ezenstudy.bts.service.GroupPurchaseService;
import ezenstudy.bts.service.OrderService;
import ezenstudy.bts.service.ProductImageService;
import ezenstudy.bts.service.ProductOptionService;
import ezenstudy.bts.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final GroupPurchaseService groupPurchaseService;
    private final GroupPurchaseProductOptionService groupPurchaseProductOptionService;
    private final ProductImageService productImageService;
    private final ProductOptionService productOptionService;
    private final ProductService productService;

    @GetMapping("/order/myOrder")
    public String listForm(Model model, HttpSession session) {

        Member member = (Member) session.getAttribute("logInMember");
        List<Order> member_orderList = orderService.findAllByMember(member.getId());

        member_orderList.stream().forEach(order -> {
            /* 리스트에 존재하는 주문 객체의 주문 고유 번호 */
            Long orderId = order.getOrderId();
            /** 주문에 저장된 공동구매 고유 번호 */
            Long gpId = order.getGroupPurchaseId();
            /** 상기 공동구매 고유 번호에 해당하는 공동구매 객체 */
            GroupPurchase gp = groupPurchaseService.findOnebyId(gpId).get();
            /** 회원이 선택한 상품 옵션 객체 */
            ProductOption po = productOptionService.findOnebyId(groupPurchaseProductOptionService
                    .findOnebyId(order.getGroupPurchaseProductOptionId()).get().getProductOptionId()).get();
            /** 공동 구매 대상 상품 이름 */
            String pn = productService.findOnebyId(gp.getProductId()).get().getName();
            /** 공동 구매 대상 상품 이미지 경로 */
            String pi = productImageService.findListbyProductId(gp.getProductId()).get(0).getImagePath();
            /** 결제 여부 변수 */
            String pay = orderService.findOneByOrder(orderId).get().getPayment();
            /** 배송 상태 변수 */
            String del = orderService.findOneByOrder(orderId).get().getDelivery();

            model.addAttribute("orderList", member_orderList);
            model.addAttribute("gp_name" + orderId, pn);
            model.addAttribute("gp_color" + orderId, po.getColor());
            model.addAttribute("gp_size" + orderId, po.getSize());
            model.addAttribute("gp_price" + orderId, gp.getPrice());
            model.addAttribute("gp_image" + orderId, pi);
            model.addAttribute("gp_pay" + orderId, pay);
            model.addAttribute("gp_del" + orderId, del);
        });

        // List<Order> orderList = orderService.findAllByMember(member.getId());

        // <img th:src="@{${['gp_image' + order.getOrderId()]}}}" alt="productImage">

        /*
         * Order testod = new Order();
         * testod.setGroupPurchaseId(1l);
         * testod.setGroupPurchaseProductOptionId(1l);
         * testod.setOrderId(1l);
         * 
         * List<Order> orderList = new ArrayList<Order>();
         * orderList.add(testod);
         * orderList.stream().forEach(order -> {
         * Long gpId = order.getGroupPurchaseId();
         * GroupPurchase gp = groupPurchaseService.findOnebyId(gpId).get();
         * Long orderId = order.getOrderId();
         * ProductOption po = productOptionService.findOnebyId(
         * groupPurchaseProductOptionService.findOnebyId(order.
         * getGroupPurchaseProductOptionId()).get()
         * .getProductOptionId())
         * .get();
         * 
         * model.addAttribute("gp_price" + orderId, gp.getPrice());
         * model.addAttribute("gp_img" + orderId,
         * productImageService.findListbyProductId(gp.getProductId()).get(0).
         * getImagePath());
         * model.addAttribute("gp_name" + orderId,
         * productService.findOnebyId(gp.getProductId()).get().getName());
         * model.addAttribute("gp_color" + orderId, po.getColor());
         * model.addAttribute("gp_size" + orderId, po.getSize());
         * });
         */
        return "orders/orderListForm";
    }

}
