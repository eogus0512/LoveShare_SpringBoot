package daehyun.loveShare.web.login;

import daehyun.loveShare.domain.login.LoginForm;
import daehyun.loveShare.domain.login.LoginService;
import daehyun.loveShare.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @GetMapping({"login", "/"})
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "page/loginPage";
    }

    @PostMapping("login")
    public String login(@ModelAttribute LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "page/loginPage";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "page/loginPage";
        }



        return "redirect:/main";

    }
}
