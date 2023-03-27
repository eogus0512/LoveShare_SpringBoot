package daehyun.loveShare.domain.profile;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Post delete(Long id);

    Post modify(Long id);

    Post findById(Long id);
    Optional<Post> findByHashTag(String hashTag);

    List<Post> findByBoyFriend(String boyFriend);

}
