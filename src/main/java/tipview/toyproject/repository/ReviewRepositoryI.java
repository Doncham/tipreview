package tipview.toyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tipview.toyproject.controller.dto.ReviewUpdateDto;
import tipview.toyproject.domain.Review;

public interface ReviewRepositoryI extends JpaRepository<Review,Long> {


}
