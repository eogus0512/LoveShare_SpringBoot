package daehyun.loveShare.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Member findById(Long id);
    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByUserName(String userName);

    Optional<Member> findByNickName(String name);

    Optional<Member> findByLoverName(String name);
    List<Member> findAll();
}
