package tipview.toyproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tipview.toyproject.controller.dto.ReviewUpdateDto;

import java.time.LocalDateTime;

@Entity
@Getter
public class Review {
    @Id
    @GeneratedValue
    @Column(name="review_id")
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonIgnore
    @Setter
    private Store store;

    @Column(name="image_path")
    private String imagePath;
    @Column(name="content")
    private String content;
    @Column(name = "foodName")
    private String foodName;

    @Column(name = "stars")
    private int stars;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Review() {
    }

    public Review(Member member, Store store, String imagePath, String content, String foodName, int stars) {
        this.member = member;
        this.store = store;
        this.imagePath = imagePath;
        this.content = content;
        this.foodName = foodName;
        this.stars = stars;
        member.getReviews().add(this);
    }

    public void updateReview(ReviewUpdateDto dto){
        this.content = dto.getContent();
        this.foodName = dto.getFoodName();
        this.stars = dto.getStars();
        this.imagePath = dto.getImagePath();

    }


}