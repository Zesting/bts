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

    // @GetMapping("/tracking/tracking")
    // @ResponseBody
    // public String getTracking() {
    //     return "<body>\n" +
    //             "    <form action=\"http://info.sweettracker.co.kr/tracking/5\" method=\"post\">\n" +
    //             "        <div class=\"form-group\">\n" +
    //             "          <label for=\"t_key\">API key</label>\n" +
    //             "          <input type=\"text\" class=\"form-control\" id=\"t_key\" name=\"t_key\" placeholder=\"제공받은 APIKEY\">\n" +
    //             "        </div>\n" +
    //             "        <div class=\"form-group\">\n" +
    //             "          <label for=\"t_code\">택배사 코드</label>\n" +
    //             "          <input type=\"text\" class=\"form-control\" name=\"t_code\" id=\"t_code\" placeholder=\"택배사 코드\">\n" +
    //             "        </div>\n" +
    //             "        <div class=\"form-group\">\n" +
    //             "          <label for=\"t_invoice\">운송장 번호</label>\n" +
    //             "          <input type=\"text\" class=\"form-control\" name=\"t_invoice\" id=\"t_invoice\" placeholder=\"운송장 번호\">\n" +
    //             "        </div>\n" +
    //             "        <button type=\"submit\" class=\"btn btn-default\">조회하기</button>\n" +
    //             "      </form>\n" +
    //             "</body>\n" +
    //             "</html>";
    // }

    @GetMapping("/tracking/tracking")
    public String showTrackingPage() {
        return "tracking/tracking"; // tracking.html 파일의 이름을 반환합니다.
    }
    

}
