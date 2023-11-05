package tipview.toyproject.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tipview.toyproject.domain.Member;
import tipview.toyproject.domain.Review;
import tipview.toyproject.controller.dto.ReviewUpdateDto;
import tipview.toyproject.domain.Store;
import tipview.toyproject.repository.MemberRepositoryI;
import tipview.toyproject.repository.ReviewRepositoryI;
import tipview.toyproject.repository.StoreRepositoryI;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepositoryI reviewRepository;
    private final StoreRepositoryI storeRepositoryI;
    private final MemberRepositoryI memberRepositoryI;

    //리뷰 등록
    @Transactional
    public Long makeReview(Review review,Long member_id, Long store_id) {
        Member member = memberRepositoryI.findById(member_id).get();
        Store store = storeRepositoryI.findById(store_id).get();
        //아 setter쓰면 안되는데....
        review.setMember(member);
        review.setStore(store);

        reviewRepository.save(review); //위치가 이게 맞지 않냐?
        return review.getId();
    }

    //리뷰 삭제
    @Transactional
    public void deleteReview(Long review_id) {

        reviewRepository.deleteById(review_id);
    }

    //리뷰 수정
    //컨트롤러 단에서 Review객체를 JSON으로 바꿔보자
    @Transactional //수정하고 flush날리면 컨트롤러에서 db에 접근할게
    public void updateReview(Long id,ReviewUpdateDto reviewUpdateDto) {
        //updateReview로 store 빼고 다 교체
        //store_id로 store찾아서 교체
        Optional<Review> findReviews = reviewRepository.findById(id);
        Review reviewToChange = findReviews.get();
        reviewToChange.updateReview(reviewUpdateDto);
        Long store_id = reviewUpdateDto.getStore_id();
        log.info("store_id123 {}",store_id);

        Optional<Store> new_store = storeRepositoryI.findById(store_id);

        reviewToChange.setStore(new_store.get()); // 식당 바뀌는지 확인해라

        Review new_review = reviewRepository.findById(id).get();

    }



    //리뷰 조회
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    public Review findReview(Long id) {
        return reviewRepository.findById(id).get();
    }


}
