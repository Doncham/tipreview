package tipview.toyproject;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tipview.toyproject.domain.Member;
import tipview.toyproject.domain.Review;
import tipview.toyproject.domain.Store;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {

        initService.dbInit1();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor // 이거 도대체 뭐더라? 롬복이요
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            System.out.println("Init1" + this.getClass());
            Member memberA = createMember("userA", "111");
            Member memberB = createMember("Sangwon", "111");


            Store store1 = createStore("한스델리", "010-8873-2065");
            Store store2 = createStore("우쿠야", "010-8873-1111");

            Review reviewA = createReview(memberA,store1,"123","맛집이네","돈돈참",10);
            Review reviewB = createReview(memberB,store2,"12345","맛집이네","육회비빔밥",2500);

            em.persist(store1);
            em.persist(store2);

            em.persist(memberA);
            em.persist(memberB);

            em.persist(reviewA);
            em.persist(reviewB);



            em.flush();

            System.out.println("size" + memberA.getReviews().size());
        }

        private Member createMember(String nickname, String password) {
            Member member = new Member(nickname, password);
            return member;
        }

        private Review createReview(Member member, Store store, String imagePath, String content, String foodName, int stars){
            Review review = new Review(member, store, imagePath, content, foodName, stars);
            return review;
        }

        private Store createStore(String name, String phoneNumber) {
            return new Store(name, phoneNumber);
        }

    }
}
