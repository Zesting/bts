package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.ReviewImage;

public interface ReviewImageRepository {
    public ReviewImage save(ReviewImage reviewImage);
    public Optional<ReviewImage> findById(Long boardId);
    public List<ReviewImage> findAll();
    public Long findByProductId(Long productId);
}
