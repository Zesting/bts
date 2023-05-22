package ezenstudy.bts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ezenstudy.bts.domain.Tracking;
import ezenstudy.bts.repository.TrackingRepository;

public class TrackingService {
    private TrackingRepository trackingRepository;

    @Autowired
    public TrackingService(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    public Tracking saveTracking(Tracking tracking) {
        return trackingRepository.save(tracking);
    }

    public List<Tracking> getAllTrackings() {
        return trackingRepository.findAll();
    }

    public void updateTracking(String key, Tracking tracking) {
        trackingRepository.setTracking(key, tracking);
    }

    public Tracking getTracking(String key) {
        return trackingRepository.getTracking(key);
    }
}
