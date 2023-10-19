package tipview.toyproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tipview.toyproject.domain.Member;

import java.util.List;

public interface MemberRepositoryI extends JpaRepository<Member,Long> {
    List<Member> findByNickname(String nickname);
}
