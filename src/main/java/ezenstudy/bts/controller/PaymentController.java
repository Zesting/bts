package ezenstudy.bts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ezenstudy.bts.domain.GroupPurchase;
import ezenstudy.bts.domain.Order;
import ezenstudy.bts.service.GroupPurchaseProductOptionService;
import ezenstudy.bts.service.GroupPurchaseService;
import ezenstudy.bts.service.KakaoPayService;
import ezenstudy.bts.service.MemberService;
import ezenstudy.bts.service.OrderService;
import ezenstudy.bts.service.PaymentService;
import ezenstudy.bts.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;

@Controller
@Log
public class PaymentController {
    private final GroupPurchaseService groupPurchaseService;
    private final GroupPurchaseProductOptionService groupPurchaseProductOptionService;
    private final MemberService memberService;
    private final KakaoPayService kakaopay;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final OrderService orderService;

    public PaymentController(GroupPurchaseService groupPurchaseService, MemberService memberService,
            KakaoPayService kakaopay, ProductService productService, PaymentService paymentService,
            OrderService orderService, GroupPurchaseProductOptionService groupPurchaseProductOptionService) {
        this.groupPurchaseService = groupPurchaseService;
        this.memberService = memberService;
        this.kakaopay = kakaopay;
        this.productService = productService;
        this.paymentService = paymentService;
        this.orderService = orderService;
        this.groupPurchaseProductOptionService = groupPurchaseProductOptionService;
    }

    @GetMapping("/payment")
    public void kakaoPayGet() {

    }

    @PostMapping("/payment")
    public String kakaoPay(
            @RequestParam("memberId") Long memberId,
            @RequestParam("groupPurchaseId") Long groupPurchaseId,
            @RequestParam("groupPurchaseProductOptionId") Long groupPurchaseProductOptionId,
            HttpSession session) {

        session.setAttribute("memberId", memberId);
        session.setAttribute("groupPurchaseId", groupPurchaseId);
        session.setAttribute("groupPurchaseProductOptionId", groupPurchaseProductOptionId);

        // 가격꺼내오기
        GroupPurchase gp = new GroupPurchase();
        gp = groupPurchaseService.findOnebyId(groupPurchaseId).get();
        // 상품이름 꺼내오기
        String productName = productService
                .findOnebyId(groupPurchaseService.findOnebyId(groupPurchaseId).get().getProductId()).get().getName();
        // 멤버 이름꺼내오기
        String memberName = memberService.findById(memberId).get().getName();

        return "redirect:" + kakaopay.kakaoPayReady(gp, productName, memberName);

    }

    @GetMapping("/payment/paymentSuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token,
            Model model,
            HttpSession session) {
        log.info("paymentSuccess get............................................");
        log.info("paymentSuccess pg_token : " + pg_token);

        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, session));
        System.out.println("여기까지 카카오페이 결제데이터 가져오기 정상 동작");

        if (session.getAttribute("groupPurchaseProductOptionId") instanceof Long groupPurchaseProductOptionId &&
                session.getAttribute("memberId") instanceof Long memberId &&
                session.getAttribute("groupPurchaseId") instanceof Long groupPurchaseId &&
                session.getAttribute("paymentId") instanceof Long paymentId) {
            System.out.println("if문 정상 동작");

            Order order = new Order();
            order.setMemberId(memberId);
            order.setGroupPurchaseId(groupPurchaseId);
            order.setGroupPurchaseProductOptionId(groupPurchaseProductOptionId);
            order.setPaymentId(paymentId);

            orderService.orderJoin(order);
            session.removeAttribute("groupPurchaseProductOptionId");
            session.removeAttribute("memberId");
            session.removeAttribute("groupPurchaseId");
            model.addAttribute("orderId", order.getOrderId());
            model.addAttribute("order_gpId", groupPurchaseId);
            model.addAttribute("order_mbId", memberId);
            model.addAttribute("order_gppoId", groupPurchaseProductOptionId);
            log.info("오더에 저장된 것." + order.getOrderId() + "|" + groupPurchaseId + "|"
                    + memberId + "|"
                    + groupPurchaseProductOptionId);
            groupPurchaseProductOptionService.soldCount(groupPurchaseId);
        }
        model.addAttribute("payment", paymentService.findAll());
    }
}
