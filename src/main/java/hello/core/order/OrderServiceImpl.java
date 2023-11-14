package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    //LINE:: DIP 위배 추상이 아니라 구체화에 의존하기때문.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


//    @Autowired // LINE :: 필드 주입은 추천하지 않음.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired //LINE:: 수정자 주입
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }



    //@Autowired  // LINE :: 생성자가 단 하나 있을 때는 생략가능 , Lombok @RequiredArgsConstructor
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        Order order = new Order(memberId,itemName,itemPrice,discountPrice);
        return order;
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
