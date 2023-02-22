package daehyun.loveShare.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public Optional<Member> findByUserName(String userName) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(userName))
                .findFirst();
    }

    @Override
    public Optional<Member> findByNickName(String nickName) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(nickName))
                .findFirst();
    }

    @Override
    public Optional<Member> findByLoverName(String loverName) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loverName))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }
}
