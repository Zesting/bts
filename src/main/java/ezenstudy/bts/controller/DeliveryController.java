package ezenstudy.bts.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ezenstudy.bts.DTO.UpdateMemberDTO;
import ezenstudy.bts.domain.Addr;
import ezenstudy.bts.domain.Delivery;
import ezenstudy.bts.domain.Order;
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
    public String delivery(@PathVariable Long id, Model model) {
        Delivery delivery = deliveryService.findDeliveryById(id);
        List<Addr> addrList = addrService.findAllAddr();
        List<Order> orderList = orderService.findAllOrder();
        // Order order = orderService.findOneByOrder(id).orElse(null);
        // List<Addr> zipCodeList = addrService.findAllAddr();
        // List<Addr> streetAddrList = addrService.findAllAddr();
        // List<Addr> detailAddrList = addrService.findAllAddr();

        model.addAttribute("delivery", delivery);
        model.addAttribute("order", orderList);
        model.addAttribute("zipCode", addrList);
        model.addAttribute("streetAddr", addrList);
        // model.addAttribute("detailAddr", addrList);

        return "delivery/delivery";
    }

    @PostMapping("/delivery/delivery/{id}")
    public String handleDeliveryShow(Model model,@ModelAttribute("updateMemberDTO") UpdateMemberDTO updateMemberDTO) {
        updateMemberDTO = new UpdateMemberDTO();
        Addr addr = addrService.addrConverter(updateMemberDTO);
        // List<Addr> zipCodeList = addrService.findAllAddr();
        // List<Addr> streetAddrList = addrService.findAllAddr();
        // List<Addr> detailAddrList = addrService.findAllAddr();

        model.addAttribute("zipCode", addr);
        model.addAttribute("streetAddr", addr);
        model.addAttribute("detailAddr", addr);
        model.addAttribute("message", "POST 요청이 정상적으로 처리되었습니다.");

        return "delivery/delivery";
    }

}
