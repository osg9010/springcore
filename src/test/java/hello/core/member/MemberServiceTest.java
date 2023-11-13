package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    AppConfig appConfig = new AppConfig();
//    MemberService memberService = new MemberServiceImpl();
    MemberService memberService = appConfig.memberService();
    @Test
    void join(){
        //given
        Member osk = new Member(1L, "osk", Grade.VIP);

        //when
        memberService.join(osk);
        Member findMember = memberService.findMember(1L);
        
        //then
        Assertions.assertThat(osk).isEqualTo(findMember);
    }
}
