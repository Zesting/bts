package ezenstudy.bts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ezenstudy.bts.domain.GroupPurchase;
import ezenstudy.bts.service.GroupPurchaseService;
import ezenstudy.bts.service.KakaoPayService;
import ezenstudy.bts.service.MemberService;
import ezenstudy.bts.service.PaymentService;
import ezenstudy.bts.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;

@Controller
@Log
public class PaymentController {
    private final GroupPurchaseService groupPurchaseService;
    private final MemberService memberService;
    private final KakaoPayService kakaopay;
    private final ProductService productService;
    private final PaymentService paymentService;

    public PaymentController(GroupPurchaseService groupPurchaseService, MemberService memberService,
            KakaoPayService kakaopay, ProductService productService, PaymentService paymentService) {
        this.groupPurchaseService = groupPurchaseService;
        this.memberService = memberService;
        this.kakaopay = kakaopay;
        this.productService = productService;
        this.paymentService = paymentService;
    }

    @GetMapping("/payment")
    public void kakaoPayGet() {

    }

    @PostMapping("/payment")
    public String kakaoPay(
            @RequestParam("memberId") Long memberId,
            @RequestParam("groupPurchaseId") Long groupPurchaseId,
            HttpSession session) {

        session.setAttribute("memberId", memberId);
        session.setAttribute("groupPurchaseId", groupPurchaseId);

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

        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token,session));

        
        model.addAttribute("payment", paymentService.findAll());
    }
}
