package ezenstudy.bts.service;

import java.util.List;

import ezenstudy.bts.domain.Delivery;
import ezenstudy.bts.repository.DeliveryRepository;

public class DeliveryService {
    
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }


    public List<Delivery> getAllDelivery(){
        return deliveryRepository.findAll();
    }

    public Delivery save(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> findAllDeliveries() {
        return deliveryRepository.findAll();
    }

}
