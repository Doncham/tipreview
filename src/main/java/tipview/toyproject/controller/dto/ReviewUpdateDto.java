package tipview.toyproject.controller.dto;

import lombok.Getter;
import tipview.toyproject.domain.Store;
@Getter
public class ReviewUpdateDto {

    private Long store_id;
    private String imagePath;
    private String content;
    private String foodName;
    private int stars;

    public ReviewUpdateDto() {
    }

    public ReviewUpdateDto(Long review_id,Long store, String imagePath, String content, String foodName, int stars) {
        this.store_id = store;
        this.imagePath = imagePath;
        this.content = content;
        this.foodName = foodName;
        this.stars = stars;
    }
// 수정일도 만들어줘야 함

}
