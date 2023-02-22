package daehyun.loveShare.web.Controller;

import daehyun.loveShare.domain.member.Member;
import daehyun.loveShare.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final MemberRepository memberRepository;

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        if (session.getAttribute("loverName") == null) {
            Optional<Member> subscriber = memberRepository.findByLoverName((String) session.getAttribute("loginId"));
            if (subscriber != null) {
                model.addAttribute("subscriber", subscriber);
            }
        }
        return "page/profilePage";
    }

    @PostMapping("/profile-request")
    public String profile_request(@Param("loverName") String loverName, HttpSession session) {
        session.setAttribute("loverName", loverName);

    }
}
