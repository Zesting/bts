package ezenstudy.bts.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ezenstudy.bts.domain.GroupPurchase;
import ezenstudy.bts.domain.Member;
import ezenstudy.bts.domain.Order;
import ezenstudy.bts.domain.Payment;
import ezenstudy.bts.domain.ProductOption;
import ezenstudy.bts.service.GroupPurchaseProductOptionService;
import ezenstudy.bts.service.GroupPurchaseService;
import ezenstudy.bts.service.OrderService;
import ezenstudy.bts.service.PaymentService;
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
    private final PaymentService paymentService;

    // 모든 주문 보기//
    @GetMapping("/order/myOrder")
    public String oneOrderForm(HttpSession session, Model model) {

        /** 로그인된 회원 객체 */
        Member sessionMember = (Member) session.getAttribute("logInMember");
        /** 상기 회원의 모든 주문 리스트 */
        List<Order> orders = orderService.findAllByMember(sessionMember.getId());
        /** 주문 리스트에 포함한 주문 객체에 존재하는 데이터를 저장한 리스트 */
        List<Map<String, Object>> member_orderList = new ArrayList<>();

        orders.stream().forEach(order -> {
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
            Payment payment = paymentService.findById(order.getPaymentId()).get();
            Date approvalDate = payment.getApprovalDate();
            Byte paymentApproval = payment.getPaymentApproval();
            Date orderCompleteDate = orderService.findOneByOrder(orderId).get().getOrder_completeDate();

            /** 결제 여부 */

            Map<String, Object> orderMap = new LinkedHashMap<>();

            orderMap.put("orderId", orderId);
            orderMap.put("gp_name", pn);
            orderMap.put("gp_color", po.getColor());
            orderMap.put("gp_size", po.getSize());
            orderMap.put("gp_price", gp.getPrice());
            orderMap.put("gp_imagePath", pi);
            orderMap.put("approvalDate", approvalDate);
            orderMap.put("paymentApproval", paymentApproval);
            orderMap.put("orderCompleteDate", orderCompleteDate); 
            orderMap.put("gpid", gpId);

            member_orderList.add(orderMap);

            /** 오더 리스트 view로 전달(Model) */
        });

        model.addAttribute("member_orderList", member_orderList);
        System.out.println("member_orderList.size() :" + member_orderList.size());

        return "orders/orderListForm";
    }

}
