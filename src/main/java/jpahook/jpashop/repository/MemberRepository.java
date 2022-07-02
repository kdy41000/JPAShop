package jpahook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpahook.jpashop.domain.member.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	//@PersistenceContext  // proxy를 통해서 EntityManager객체를 생성하도록 설정
	private final EntityManager em;
	
	public void save(Member member) {
		em.persist(member);  // 엔티티를 영속성 컨텍스트에 저장 (영속성 컨텍스트란? Entity를 영구 저장하는 환경을 뜻한다.)
	}
	
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)  // JPQL(테이블을 대상으로 하는 것이 아니라 Member객체를 대상으로 조회)
				.getResultList();
	}
	
	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)  // JPQL(테이블을 대상으로 하는 것이 아니라 Member객체를 대상으로 조회)
				.setParameter("name", name)
				.getResultList();
	}
	
	
}
