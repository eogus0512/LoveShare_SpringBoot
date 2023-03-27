package daehyun.loveShare.domain.profile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
@Slf4j
public class DBPostRepository implements PostRepository{
    EntityManager em;

    public DBPostRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public Post save(Post post) {
        log.info("save: post={}", post);
        em.persist(post);
        return post;
    }

    @Override
    public Post delete(Long id) {
        return null;
    }

    @Override
    public Post modify(Long id) {
        return null;
    }

    @Override
    public Post findById(Long id) {
        Post post = em.find(Post.class, id);
        return post;
    }

    @Override
    public Optional<Post> findByHashTag(String hashTag) {
        List<Post> result = em.createQuery("select m from Post m where m.hashTag = :hashTag", Post.class)
                .setParameter("hashTag", hashTag)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Post> findByBoyFriend(String boyFriend) {
        return em.createQuery("select m from Post m where m.boyFriend = :boyFriend", Post.class)
                .setParameter("boyFriend", boyFriend)
                .getResultList();
    }
}
