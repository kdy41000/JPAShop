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
@Transactional(readOnly = true)  // readOnly = true설정 시 해당 클래스 전체 조회성능을 최적화한다.
@RequiredArgsConstructor  // 컴파일 시점에 final이 붙은 객체의 생성자를 생성해준다.(최신 트렌드)
//@AllArgsConstructor  // 컴파일 시점에 모든 객체의 생성자를 생성해준다.
public class MemberService {

	private final MemberRepository memberRepository;
	
	//private MemberRepository memberRepository;
	
	// 기존 방식
	/*
	@Autowired
	private MemberRepository memberRepository;
	*/
	
	// 회원 가입
	@Transactional  // 조회 기능이 아니므로 별도로 @Transactional을 설정한다.
	public Long join(Member member) {
		validateDuplicateMember(member);  // 중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	// 회원 전체 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	// 단일 회원 조회
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
	
	
	
}
