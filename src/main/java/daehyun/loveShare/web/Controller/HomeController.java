package daehyun.loveShare.web.Controller;

import daehyun.loveShare.domain.login.LoginForm;
import daehyun.loveShare.domain.member.Member;
import daehyun.loveShare.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {
    @GetMapping("/")
    public String main(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model, HttpServletResponse response) throws IOException {

        if (loginMember == null) {
            LoginForm loginForm = new LoginForm();
            model.addAttribute("loginForm", loginForm);
            response.sendRedirect("/login");
            return "page/loginPage";
        }

        model.addAttribute("member", loginMember);
        return "page/main";
    }
}
