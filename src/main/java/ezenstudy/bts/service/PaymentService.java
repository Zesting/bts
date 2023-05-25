package ezenstudy.bts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ezenstudy.bts.domain.Payment;
import ezenstudy.bts.repository.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

 
    public List<Payment> findOne(Long memberId) {
        return paymentRepository.findAll().stream().
        filter(mp->mp.getMemberId().equals(memberId)).
        collect(Collectors.toList());
    }
    
    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }
    
}
