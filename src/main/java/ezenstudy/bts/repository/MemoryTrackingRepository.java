package ezenstudy.bts.repository;


import ezenstudy.bts.domain.Tracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryTrackingRepository implements TrackingRepository {
    private Map<String, Tracking> trackingMap;

    public MemoryTrackingRepository() {
        trackingMap = new HashMap<>();
    }

    @Override
    public Tracking save(Tracking tracking) {
        trackingMap.put(tracking.getT_key(), tracking);
        return tracking;
    }

    @Override
    public List<Tracking> findAll() {
        return new ArrayList<>(trackingMap.values());
    }

    @Override
    public void setTracking(String key, Tracking tracking) {
        trackingMap.put(key, tracking);
    }

    @Override
    public Tracking getTracking(String key) {
        return trackingMap.get(key);
    }
}

