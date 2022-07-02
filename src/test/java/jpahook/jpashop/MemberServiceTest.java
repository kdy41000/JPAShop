package jpahook.jpashop;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpahook.jpashop.domain.member.Member;
import jpahook.jpashop.repository.MemberRepository;
import jpahook.jpashop.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional  // 기본적으로 rollback
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	EntityManager em;
	
	@Test
	//@Rollback(false)  // rollback하지 않도록 설정
	public void 회원가입() throws Exception {
		// given
		Member member = new Member();
		member.setName("kim");
		
		// when
		Long saveId = memberService.join(member);
		
		em.flush();  // 영속성 컨텍스트에 저장 후 rollback된다.
		
		// then
		assertEquals(member, memberRepository.findOne(saveId));
	}
	
	@Test
	public void 중복_회원_예외() throws Exception {
		// given
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");
		
		// when
		memberService.join(member1);
		try {
			memberService.join(member2);
		} catch (IllegalStateException e) {
			return;
		}
		
		// then
		fail("예외가 발생해야 한다.");
	}

}
