package ezenstudy.bts.service;

import ezenstudy.bts.repository.DeliveryRepository;

public class DeliveryService {
    
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

}
