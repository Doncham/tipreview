package tipview.toyproject.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class S3ImageDto {
    private String image_url;

    public S3ImageDto(String image_url) {
        this.image_url = image_url;
    }
}
