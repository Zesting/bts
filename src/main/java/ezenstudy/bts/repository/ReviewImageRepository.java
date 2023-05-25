package ezenstudy.bts.repository;

import java.util.List;
import java.util.Optional;

import ezenstudy.bts.domain.ReviewImage;

public interface ReviewImageRepository {
    public ReviewImage save(ReviewImage reviewImage);
    public Optional<ReviewImage> findById(Long id);
    public List<ReviewImage> findAll();
    public List<ReviewImage> findByReviewBoardId(Long reviewBoardId);
    public ReviewImage findByOneReviewBoardId(Long boardId);
}
