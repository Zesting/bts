package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ezenstudy.bts.domain.Delivery;

public class MemoryDeliveryRepository implements DeliveryRepository{
    private final Map<Long, Delivery> deliveryMap = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Delivery findById(Long id) {
        return deliveryMap.get(id);
    }

    @Override
    public Delivery save(Delivery delivery) {
        delivery.setId(++sequence);
        deliveryMap.put(delivery.getId(),delivery);
        return delivery;
    }

    @Override
    public List<Delivery> findAll() {
        return new ArrayList<>(deliveryMap.values());
    }

    @Override
    public Delivery update(Long id, Delivery delivery) {
        return deliveryMap.put(delivery.getId(), delivery);
    }
    
}
