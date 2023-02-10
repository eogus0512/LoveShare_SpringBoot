package daehyun.loveShare.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String nickName;
    @NotEmpty
    private String age;
    @NotEmpty
    private String gender;

    private String loverName;




}
