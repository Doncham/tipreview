package tipview.toyproject.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tipview.toyproject.Service.ReviewService;
import tipview.toyproject.Service.S3UploadService;
import tipview.toyproject.controller.dto.ReviewUpdateDto;
import tipview.toyproject.controller.dto.S3ImageDto;
import tipview.toyproject.domain.Review;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final S3UploadService s3UploadService;
    @Operation(summary = "리뷰 등록", description ="멤버랑 가게 정보는 입력해줘요 각각 1번과2번 사용 가능(더미 데이터)")
    @Parameter(name = "member_id", description = "멤버의 주키")
    @Parameter(name ="store_id", description = "가게의 주키")
    @PostMapping("/review/create/{member_id}/{store_id}")
    public String create(@RequestBody Review review, @PathVariable("member_id") Long member_id,
                         @PathVariable("store_id") Long store_id) {
        reviewService.makeReview(review,member_id,store_id);
        //리뷰를 만들다가 예외가 생기는 상황은 따로 생각을 해봐야할거같아
        return "success";
    }

    @PostMapping("/review/delete/{review_id}")
    public String delete(@PathVariable("review_id") long review_id ){
        reviewService.deleteReview(review_id);
        return "success";
    }

    @GetMapping(value = "/review/findAll")
    public List<Review> getAll() {
        return reviewService.findAllReviews();
    }

    @PostMapping(value = "/review/update/{review_id}")
    public Review updateReview(@PathVariable("review_id") Long review_id, @RequestBody ReviewUpdateDto reviewUpdateDto){
        reviewService.updateReview(review_id, reviewUpdateDto);
        return reviewService.findReview(review_id);
    }

    @PostMapping(value = "/review/imageUpload")
    public S3ImageDto uploadReview(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        S3ImageDto s3ImageDto = new S3ImageDto(s3UploadService.saveFile(multipartFile));
        return s3ImageDto;

    }







}
