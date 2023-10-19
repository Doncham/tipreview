package tipview.toyproject.controller;


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

    @PostMapping("/review/create")
    public String create(@RequestBody Review review) {
        reviewService.makeReview(review);
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
