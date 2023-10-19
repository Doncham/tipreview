package tipview.toyproject.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tipview.toyproject.domain.Member;
import tipview.toyproject.repository.MemberRepositoryI;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepositoryI memberRepository;

    @Transactional
    public Long Join(Member member){
        //근데 예외 던지고 끝나면 안될거 같은디..
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //회원 가입 시 중복 체크 로직
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByNickname(member.getNickname());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다."); }
    }

    //이거 근데 객체 반환해서 어디다 쓰냐??

    public Optional<Member> findOne(Long memberId){
        //return memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("없다야")) ;
        return memberRepository.findById(memberId);
    }

    public String Login(String nickName, String password) {
         List<Member> members = memberRepository.findByNickname(nickName);
         Member member = members.get(0);
         log.info("{} {}",member.getPassword(), password);

         if(member.getPassword().equals(password)) {
             return "true";
         }
         else{
             return "false";
         }
    }

}
