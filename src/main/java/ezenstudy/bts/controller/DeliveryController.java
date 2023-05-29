package ezenstudy.bts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ezenstudy.bts.domain.Addr;
import ezenstudy.bts.domain.Member;
import ezenstudy.bts.service.AddrService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class DeliveryController {
    
            private final AddrService addrService;

            

            
            @GetMapping("/delivery/delivery/{id}")
            public String delivery(HttpSession session, Model model) {
                
                Member member = (Member) session.getAttribute("logInMember");
                Addr addr = addrService.findAddr(member.getId()).get();
                model.addAttribute("zip", addr.getZipCode());
                model.addAttribute("str", addr.getStreetAddr());
                model.addAttribute("dlt", addr.getDetailAddr());
                System.out.println(addr);
            
            
            
                return "delivery/delivery";
            }
        }
        

    
