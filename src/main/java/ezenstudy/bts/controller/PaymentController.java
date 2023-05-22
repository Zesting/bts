package ezenstudy.bts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ezenstudy.bts.service.KakaoPayService;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log

public class PaymentController {
    @Setter(onMethod_ = @Autowired)
    private KakaoPayService kakaopay;
    
    
    @GetMapping("/payment")
    public void kakaoPayGet() {
        
    }
    
    @PostMapping("/payment")
    public String kakaoPay() {
        log.info("kakaoPay post............................................");
        
        return "redirect:" + kakaopay.kakaoPayReady();
 
    }
    
    @GetMapping("/paymentSuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("paymentSuccess get............................................");
        log.info("paymentSuccess pg_token : " + pg_token);
        
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
        
    }
}
