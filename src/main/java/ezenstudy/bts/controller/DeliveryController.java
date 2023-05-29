package ezenstudy.bts.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ezenstudy.bts.domain.Addr;
import ezenstudy.bts.domain.Delivery;
import ezenstudy.bts.domain.Member;
import ezenstudy.bts.domain.Order;
import ezenstudy.bts.service.AddrService;
import ezenstudy.bts.service.DeliveryService;
import ezenstudy.bts.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class DeliveryController {
    
            private final DeliveryService deliveryService;
            private final AddrService addrService;
            private final OrderService orderService;

            

            
            @GetMapping("/delivery/delivery/{id}")
            public String delivery(HttpSession session, Model model) {
                
                Member member = (Member) session.getAttribute("logInMember");
                List<Delivery> deliveryList = deliveryService.findAllByMember(member.getId());
                
                deliveryList.stream().forEach(delivery -> {

                    Long deliveryId = delivery.getId();
                    
                    String addrZipCode = addrService.findAddr(member.getId()).get().getZipCode();
                    String addrStreetAddr = addrService.findAddr(member.getId()).get().getStreetAddr();
                    String addrDetailAddr = addrService.findAddr(member.getId()).get().getDetailAddr();

                    model.addAttribute("delList", deliveryList);
                    model.addAttribute("zip" + deliveryId, addrZipCode);
                    model.addAttribute("str" + deliveryId, addrStreetAddr);
                    model.addAttribute("dlt" + deliveryId, addrDetailAddr);
                });
                

                 return "orders/orderListForm";
            }
            @PostMapping("/delivery/delivery/{id}")
             public String handleDeliveryShow(Model model, @ModelAttribute("delivery") Delivery delivery) {
                 model.addAttribute("message", "POST 요청이 정상적으로 처리되었습니다.");
                return "delivery/delivery";
             }
    }

    // @GetMapping("/delivery/delivery/{id}")
    // public String delivery(@PathVariable Long id, Model model) {
    //     Delivery delivery = deliveryService.findDeliveryById(id);
    //     List<Addr> addrList = addrService.findAllAddr();
    //     List<Order> oederList = orderService.findAllOrder();

    //     model.addAttribute("delivery", delivery);
    //     model.addAttribute("zipCodeList", addrList);
    //     model.addAttribute("streetAddrList", addrList);
    //     model.addAttribute("detailAddrList", addrList);
    //     model.addAttribute("orderId", oederList);

    //     return "delivery/delivery";
    // }

    
