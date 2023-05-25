package ezenstudy.bts.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import ezenstudy.bts.domain.ReviewImage;
@Repository
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
    public ReviewImage save(ReviewImage reviewImage) {
        reviewImage.setId(++sequence);
        store.put(reviewImage.getId(), reviewImage);
        System.out.println(reviewImage);
        return reviewImage;
    }
    @Override
    public List<ReviewImage> findByReviewBoardId(Long reviewBoardId) {
        return store.values().stream().
        filter(rb -> rb.getReviewBoardId() == reviewBoardId).
        collect(Collectors.toList());
    }
    @Override
    public ReviewImage findByOneReviewBoardId(Long reviewBoardId) {
        return store.values().stream()
            .filter(rb -> rb.getReviewBoardId() == reviewBoardId)
            .findFirst()
            .orElse(null);
    }

}
