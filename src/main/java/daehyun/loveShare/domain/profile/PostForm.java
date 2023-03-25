package daehyun.loveShare.domain.profile;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostForm {

    private List<MultipartFile> images;
    private String content;
    private String hashTag;
}
