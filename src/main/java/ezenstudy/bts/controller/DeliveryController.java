package ezenstudy.bts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ezenstudy.bts.service.AddrService;
import ezenstudy.bts.service.DeliveryService;
import ezenstudy.bts.service.OrderService;

@Controller
public class DeliveryController {
    
    private final  DeliveryService deliveryService;
    private final AddrService addrService;
    private final OrderService orderService;

    public DeliveryController(DeliveryService deliveryService, AddrService addrService, OrderService orderService) {
        this.deliveryService = deliveryService;
        this.addrService = addrService;
        this.orderService = orderService;
    }

    @GetMapping("/delivery/delivery/{id}")
    public String delivery(Model model) {
        model.addAttribute("addrId", addrService.findAllAddr());
        model.addAttribute("orderId", orderService.findAllOrder());
        model.addAttribute("status", deliveryService.findAllDeliveries());
        return "redirect:/";
    }

}
