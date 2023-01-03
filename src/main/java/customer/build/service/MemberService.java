package customer.build.service;

import customer.build.domain.Member;
import customer.build.repository.MemberRepository;
import customer.build.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { // new 에서 생성하는게 아니라 외부에서 넣어 주도록 함 > DI
        this.memberRepository = memberRepository;
    }


    // 회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증하는 코드
        memberRepository.save(member);
        return member.getId();
    }

    // 회원 검증 코드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 입니다.");
                });
    }

    // 회원 검색
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
