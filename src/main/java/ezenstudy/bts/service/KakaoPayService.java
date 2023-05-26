package ezenstudy.bts.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import ezenstudy.bts.DTO.KakaoCancelResponse;
import ezenstudy.bts.DTO.KakaoPayApprovalVO;
import ezenstudy.bts.DTO.PaymentDTO;
import ezenstudy.bts.domain.GroupPurchase;
import ezenstudy.bts.repository.PaymentRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;

@Log
@Service
public class KakaoPayService {
    private static final String HOST = "https://kapi.kakao.com";

    private PaymentDTO paymentDTO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;
    private final PaymentRepository paymentRepository;


    public KakaoPayService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // ---------------------------------결제준비------------------------------------------
    public String kakaoPayReady(GroupPurchase gp, String productName, String memberName) {

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "caab353b4b3e2459f386010dd152803d");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        //밑에 3개는 결제승인이랑 일치해야함
        params.add("cid", "TC0ONETIME"); 
        params.add("partner_order_id", "306"); 
        params.add("partner_user_id", "gorany"); 
        
        params.add("item_name", productName); // 상품명 받아옴
        params.add("quantity", "1"); 
        params.add("total_amount", String.valueOf(gp.getPrice())); // 상품가격 받아옴
        params.add("tax_free_amount", "100"); // 비과세금액 더미값

        params.add("approval_url", "http://localhost:8080/payment/paymentSuccess");
        params.add("cancel_url", "http://localhost:8080/paymentCancel");
        params.add("fail_url", "http://localhost:8080/paymentSuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            paymentDTO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, PaymentDTO.class);
            System.out.println(paymentDTO);
            log.info("" + paymentDTO);

            return paymentDTO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            System.out.println("이동에 실패함");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return "/pay";

    }

    // ---------------------------------결제승인요청------------------------------------------
    public KakaoPayApprovalVO kakaoPayInfo(String pg_token,HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        Long gpId = (Long)session.getAttribute("groupPurchaseId");

        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "caab353b4b3e2459f386010dd152803d");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("tid", paymentDTO.getTid()); //결제준비api응답에 포함
        params.add("pg_token", pg_token); //결제승인 요청을인증하는 토큰
        //결제준비랑 똑같아야함
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "306");
        params.add("partner_user_id", "gorany");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        // 카카오페이한테 kakaoPayApprovalVO 로 값받아오기
        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body,
                    KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);
            
            //여기서 카카오페이에서 kakaoPayApprovalVO 로 받은 값들 MemoryRepository에 저장
            paymentRepository.save(kakaoPayApprovalVO.paymentVoSave(memberId,gpId));
            

            return kakaoPayApprovalVO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    //결제취소
    public KakaoCancelResponse kakaoCancel() {

         // 서버로 요청할 Header
         HttpHeaders headers = new HttpHeaders();
         headers.add("Authorization", "KakaoAK " + "caab353b4b3e2459f386010dd152803d");
         headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
         headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 카카오페이 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", "환불할 결제 고유 번호");
        parameters.add("cancel_amount", "환불 금액");
        parameters.add("cancel_tax_free_amount", "환불 비과세 금액");

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);
    
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
    
        KakaoCancelResponse cancelResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/cancel",
                requestEntity,
                KakaoCancelResponse.class);
                
        return cancelResponse;
    }
    

}
