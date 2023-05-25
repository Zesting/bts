package ezenstudy.bts.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ezenstudy.bts.domain.Payment;

@Repository
public class MemoryPaymentRepository implements PaymentRepository {
    private Map<Long, Payment> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Payment save(Payment payment) {
        payment.setId(++sequence);
        store.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Optional<Payment> findOne(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Payment> findAll() {
        return new ArrayList<>(store.values());
    }

    public MemoryPaymentRepository(){
        Payment dm1 = new Payment();
        dm1.setAmount(56000);
        dm1.setApprovalDate(
            Timestamp.valueOf(
                LocalDateTime.of(2023, 5, 1, 17, 30, 0)
                ));
        dm1.setCardName("현대카드");
        dm1.setGroupPurchaseId(1l);
        dm1.setMemberId(1l);
        dm1.setPaymentType("CARD");
        dm1.setProductName("나이키 덩크 로우 레트로 SE");
        dm1.setTid("a46c37b172620615d8e3");
        save(dm1);

        Payment dm2 = new Payment();
        dm2.setAmount(59800);
        dm2.setApprovalDate(
            Timestamp.valueOf(
                LocalDateTime.of(2023, 4, 10, 15, 20, 0)
                ));
        dm2.setCardName("삼성카드");
        dm2.setGroupPurchaseId(3l);
        dm2.setMemberId(1l);
        dm2.setPaymentType("CASH");
        dm2.setProductName("에어 베이퍼맥스 2023 플라이니트");
        dm2.setTid("r42g34332432rew1424");
        save(dm2);
    }
    
}
