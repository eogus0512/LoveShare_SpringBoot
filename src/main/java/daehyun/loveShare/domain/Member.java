package daehyun.loveShare.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String Password;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String nickName;
    @NotEmpty
    private Long age;
    @NotEmpty
    private String gender;

    private String loverName;




}
