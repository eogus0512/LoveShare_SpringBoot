package daehyun.loveShare.domain.member;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "아이디는 필수 입력사항입니다.")
    @Pattern(regexp="[a-zA-Z0-9]{8,14}", message = "아이디는 영어와 숫자를 조합하여 8~14자리 이내로 입력해주세요.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수 입력사항입니다.")
    @Pattern(regexp="[a-zA-Z0-9]{8,16}", message = "비밀번호는 영어와 숫자를 조합하여 8~16자리 이내로 입력해주세요.")
    private String password;

    @NotEmpty(message = "이름은 필수 입력사항입니다.")
    private String userName;

    @NotEmpty(message = "닉네임은 필수 입력사항입니다.")
    private String nickName;

    @NotEmpty(message = "나이는 필수 입력사항입니다.")
    private String age;

    @NotEmpty(message = "성별은 필수 입력사항입니다.")
    private String gender;

    private String loverName;
}
