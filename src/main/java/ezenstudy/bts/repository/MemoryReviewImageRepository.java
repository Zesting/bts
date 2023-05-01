package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ezenstudy.bts.domain.ReviewImage;

public class MemoryReviewImageRepository implements ReviewImageRepository {
    private static final Map<Long,ReviewImage> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public List<ReviewImage> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<ReviewImage> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Long findByProductId(Long productId) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public ReviewImage save(ReviewImage reviewImage) {
        reviewImage.setId(++sequence);
        store.put(reviewImage.getId(), reviewImage);
        return reviewImage;
    }

}
