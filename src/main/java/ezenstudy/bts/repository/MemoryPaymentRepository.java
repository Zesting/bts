package ezenstudy.bts.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.Payment;

public class MemoryPaymentRepository implements PaymentRepository {
    private static final Map<Long, Payment> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Optional<Payment> findByOrderId(Long orderId) {
        return Optional.ofNullable(store.get(orderId));
    }

    @Override
    public Payment save(Payment payment) {
        payment.setId(++sequence);
        store.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Payment findOne(Long id) {
        return store.get(id);
    }
    
}
