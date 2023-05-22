package ezenstudy.bts.repository;

import java.util.Optional;

import ezenstudy.bts.domain.Payment;

public interface PaymentRepository {
    public Payment save(Payment payment);
    public Optional<Payment> findByOrderId(Long orderId);
}
