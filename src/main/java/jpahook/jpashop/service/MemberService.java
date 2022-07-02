package jpahook.jpashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpahook.jpashop.domain.member.Member;
import jpahook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)  // readOnly = true���� �� �ش� Ŭ���� ��ü ��ȸ������ ����ȭ�Ѵ�.
@RequiredArgsConstructor  // ������ ������ final�� ���� ��ü�� �����ڸ� �������ش�.(�ֽ� Ʈ����)
//@AllArgsConstructor  // ������ ������ ��� ��ü�� �����ڸ� �������ش�.
public class MemberService {

	private final MemberRepository memberRepository;
	
	//private MemberRepository memberRepository;
	
	// ���� ���
	/*
	@Autowired
	private MemberRepository memberRepository;
	*/
	
	// ȸ�� ����
	@Transactional  // ��ȸ ����� �ƴϹǷ� ������ @Transactional�� �����Ѵ�.
	public Long join(Member member) {
		validateDuplicateMember(member);  // �ߺ� ȸ�� ����
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("�̹� �����ϴ� ȸ���Դϴ�.");
		}
	}
	
	// ȸ�� ��ü ��ȸ
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	// ���� ȸ�� ��ȸ
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
	
	
	
}
