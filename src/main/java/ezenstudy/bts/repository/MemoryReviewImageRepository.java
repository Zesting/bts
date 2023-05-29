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

    public MemoryReviewImageRepository(){
        ReviewImage ri1=new ReviewImage();
        ri1.setFilePath("/dummyimage/나이키덩크로우레트로블랙1리뷰.jpg");
        ri1.setReviewBoardId(1l);
        save(ri1);

        ReviewImage ri2=new ReviewImage();
        ri2.setFilePath("/dummyimage/나이키리뷰1.jpg");
        ri2.setReviewBoardId(1l);
        save(ri2);

        ReviewImage ri3=new ReviewImage();
        ri1.setFilePath("/dummyimage/나이키에어페이퍼맥스.jpg");
        ri1.setId(1l);
        ri1.setReviewBoardId(2l);
        save(ri3);

        ReviewImage ri4=new ReviewImage();
        ri1.setFilePath("/dummyimage/닥터마틴.jpg");
        ri1.setReviewBoardId(3l);
        save(ri4);

        ReviewImage ri5=new ReviewImage();
        ri1.setFilePath("/dummyimage/닥터마틴마일즈1리뷰.jpg");
        ri1.setReviewBoardId(4l);
        save(ri5);
    }

}
