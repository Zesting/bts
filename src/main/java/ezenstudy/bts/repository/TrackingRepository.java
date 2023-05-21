package ezenstudy.bts.repository;

import java.util.List;

import ezenstudy.bts.domain.Tracking;

public interface TrackingRepository {

    Tracking save(Tracking tracking);

    List<Tracking> findAll();
	
    void setTracking(String key, Tracking tracking);
    
    Tracking getTracking(String key);

}
