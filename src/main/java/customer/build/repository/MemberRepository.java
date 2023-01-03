package customer.build.repository;

import customer.build.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // 회원 생성
    Member save(Member member);

    // id로 회원 검색
    Optional<Member> findById(Long id);

    // name 으로 회원 검색
    Optional<Member> findByName(String name);

    // 회원 모두 출력
    List<Member> findAll();
}
