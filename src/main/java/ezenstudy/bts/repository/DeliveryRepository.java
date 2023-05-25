package ezenstudy.bts.repository;

import java.util.List;

import ezenstudy.bts.domain.Delivery;

public interface DeliveryRepository {
    Delivery findById(Long id);
    Delivery save(Delivery delivery);
    List<Delivery> findAll();
    Delivery update(Long id, Delivery delivery);
}
