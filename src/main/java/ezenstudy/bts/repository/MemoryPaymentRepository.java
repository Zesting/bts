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
    public Optional<Payment> findOneByMemberId(Long memberId) {
        return Optional.ofNullable(store.get(memberId));
    }

    @Override
    public Optional<Payment> findOneById(Long paymentId) {
        return Optional.ofNullable(store.get(paymentId));
    }

    @Override
    public List<Payment> findAll() {
        return new ArrayList<>(store.values());
    }

    public MemoryPaymentRepository() {
        Payment dm1 = new Payment();
        dm1.setAmount(56000);
        dm1.setApprovalDate(
                Timestamp.valueOf(
                        LocalDateTime.of(2023, 5, 1, 17, 30, 0)));
        dm1.setCardName("현대카드");
        dm1.setGroupPurchaseId(1l);
        dm1.setMemberId(1l);
        dm1.setPaymentType("CARD");
        dm1.setProductName("나이키 덩크 로우 레트로 SE");
        dm1.setTid("a46c37b172620615d8e3");
        dm1.setPaymentApproval((byte) 1);
        save(dm1);

        Payment dm2 = new Payment();
        dm2.setAmount(59800);
        dm2.setApprovalDate(
                Timestamp.valueOf(
                        LocalDateTime.of(2023, 4, 10, 15, 20, 0)));
        dm2.setCardName("삼성카드");
        dm2.setGroupPurchaseId(1l);
        dm2.setMemberId(1l);
        dm2.setPaymentType("CASH");
        dm2.setProductName("에어 베이퍼맥스 2023 플라이니트");
        dm2.setTid("r42g34332432rew1424");
        dm2.setPaymentApproval((byte) 1);

        save(dm2);

        Payment dm3 = new Payment();
        dm3.setAmount(59800);
        dm3.setApprovalDate(
                Timestamp.valueOf(
                        LocalDateTime.of(2023, 3, 29, 15, 20, 0)));
        dm3.setCardName("삼성카드");
        dm3.setGroupPurchaseId(3l);
        dm3.setMemberId(1l);
        dm3.setPaymentType("CASH");
        dm3.setProductName("에어 베이퍼맥스 2023 플라이니트");
        dm3.setTid("r42g34332432rew1424");
        save(dm3);

        Payment dm4 = new Payment();
        dm4.setAmount(73000);
        dm4.setApprovalDate(
                Timestamp.valueOf(
                        LocalDateTime.of(2023, 2, 9, 15, 20, 0)));
        dm4.setGroupPurchaseId(2l);
        dm4.setMemberId(1L);
        dm4.setPaymentType("CASH");
        dm4.setTid("r20g12193939rwe1203");
        save(dm4);

        Payment dm5 = new Payment();
        dm5.setAmount(59800);
        dm5.setApprovalDate(
                Timestamp.valueOf(
                        LocalDateTime.of(2023, 1, 11, 15, 20, 0)));
        dm5.setGroupPurchaseId(3l);
        dm5.setMemberId(2L);
        dm5.setPaymentType("CASH");
        dm5.setTid("r20g12193939rwe1203");
        save(dm5);

        Payment dm6 = new Payment();
        dm6.setAmount(73000);
        dm6.setApprovalDate(
                Timestamp.valueOf(LocalDateTime.of(2023, 4, 13, 11, 17, 33, 0)));
        dm6.setGroupPurchaseId(2L);
        dm6.setMemberId(2L);
        dm6.setPaymentType("CASH");
        dm6.setTid("r20g12193939rwe1203");
        save(dm6);

    }

}
