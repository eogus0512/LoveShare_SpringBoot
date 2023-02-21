package daehyun.loveShare;

import daehyun.loveShare.domain.member.DBMemberRepository;
import daehyun.loveShare.domain.member.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new DBMemberRepository(em);
    }
}
