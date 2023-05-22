package ezenstudy.bts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.ResponseBody;

import ezenstudy.bts.domain.Tracking;
import ezenstudy.bts.service.TrackingService;

@Controller
public class TrackingController {
    private TrackingService trackingService;
    
    @Autowired
    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PostMapping("/tracking/tracking")
    public String saveTracking(@ModelAttribute("tracking") Tracking tracking) {
        trackingService.saveTracking(tracking);
        return "redirect:/tracking/" + tracking.getT_key();
    }
    
    @GetMapping("/tracking/tracking")
    public String showTrackingPage() {
        return "tracking/tracking"; // tracking.html 파일의 이름을 반환합니다.
    }
    

}
