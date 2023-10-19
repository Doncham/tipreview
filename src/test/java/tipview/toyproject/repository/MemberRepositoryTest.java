package tipview.toyproject.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tipview.toyproject.domain.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepositoryI memberRepository;
    @Test
    void 이름_회원_찾기() {
        Member memberA = new Member("김상원", "123");
        memberRepository.save(memberA);

        List<Member> findMembers = memberRepository.findByNickname("김상원");

        Assertions.assertThat(findMembers.get(0).getNickname()).isEqualTo("김상원");
    }
}