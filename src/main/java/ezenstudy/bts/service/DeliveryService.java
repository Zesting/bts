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
    public Delivery findDeliveryById(Long id) {
        // Implement the logic to find a delivery by its ID
        // This could involve querying a database or accessing a repository
        
        // For example, assuming you have a delivery repository
        return deliveryRepository.findById(id);
    }

}
