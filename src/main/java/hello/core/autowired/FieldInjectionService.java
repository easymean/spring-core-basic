package hello.core.autowired;

import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FieldInjectionService {

    @Autowired
    private MemberRepository memberRepository; // 필드 주입만 하면 test시 NullPointerException

    public void setMemberRepository(MemberRepository memberRepository) { // setter를 통해 NullPointerException 방지
        this.memberRepository = memberRepository;
    }

    public Member printFieldInjection(Long id) {
        return memberRepository.findById(id);
    }
}
