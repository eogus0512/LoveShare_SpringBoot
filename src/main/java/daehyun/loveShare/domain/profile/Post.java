package daehyun.loveShare.domain.profile;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String boyFriend;

    @NotNull
    private String girlFriend;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<UploadFile> images = new ArrayList<>();

    private String content;

    private String hashTag;

}
