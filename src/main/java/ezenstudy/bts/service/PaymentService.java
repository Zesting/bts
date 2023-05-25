package ezenstudy.bts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ezenstudy.bts.domain.Payment;
import ezenstudy.bts.repository.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

 
    public Optional<Payment> findOne(Long memberId) {
        return paymentRepository.findOne(memberId);
    }
    
    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }
    
}
