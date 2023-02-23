package daehyun.loveShare.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
@Slf4j
public class DBMemberRepository implements MemberRepository{

    private final EntityManager em;

    public DBMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        log.info("save: member={}", member);
        em.persist(member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        Member member = em.find(Member.class, id);
        return member;
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        List<Member> result = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public Optional<Member> findByUserName(String userName) {
        List<Member> result = em.createQuery("select m from Member m where m.userName = :userName", Member.class)
                .setParameter("userName", userName)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByNickName(String nickName) {
        List<Member> result = em.createQuery("select m from Member m where m.nickName = :nickName", Member.class)
                .setParameter("nickName", nickName)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByLoverName(String loverName) {
        List<Member> result = em.createQuery("select m from Member m where m.loverName = :loverName", Member.class)
                .setParameter("loverName", loverName)
                .getResultList();
        return result.stream().findAny();
    }
}
