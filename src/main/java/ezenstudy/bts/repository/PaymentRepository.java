package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.Payment;

public interface PaymentRepository {
    public Payment save(Payment payment);

    public Optional<Payment> findOneByMemberId(Long memberId);

    public Optional<Payment> findOneById(Long paymentId);

    public List<Payment> findAll();
}
