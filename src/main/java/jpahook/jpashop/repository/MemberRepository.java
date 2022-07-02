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

	//@PersistenceContext  // proxy�� ���ؼ� EntityManager��ü�� �����ϵ��� ����
	private final EntityManager em;
	
	public void save(Member member) {
		em.persist(member);  // ��ƼƼ�� ���Ӽ� ���ؽ�Ʈ�� ���� (���Ӽ� ���ؽ�Ʈ��? Entity�� ���� �����ϴ� ȯ���� ���Ѵ�.)
	}
	
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)  // JPQL(���̺��� ������� �ϴ� ���� �ƴ϶� Member��ü�� ������� ��ȸ)
				.getResultList();
	}
	
	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)  // JPQL(���̺��� ������� �ϴ� ���� �ƴ϶� Member��ü�� ������� ��ȸ)
				.setParameter("name", name)
				.getResultList();
	}
	
	
}
