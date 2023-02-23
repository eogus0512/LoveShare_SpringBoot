package daehyun.loveShare.web.Controller;

import daehyun.loveShare.domain.member.Member;
import daehyun.loveShare.domain.member.MemberRepository;
import daehyun.loveShare.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProfileController {
    private final MemberRepository memberRepository;

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (member.getLoverName() == null) {
            Optional<Member> subscriber = memberRepository.findByLoverName((String) session.getAttribute("loginId"));
            if (subscriber != null) {
                model.addAttribute("subscriber", subscriber);
            }
        }
        return "page/profilePage";
    }

    @PostMapping("/profile-request")
    public String profile_request(@RequestParam String loverName, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Member update = memberRepository.findById(member.getId());
        update.setLoverName(loverName);

        session.setAttribute(SessionConst.LOGIN_MEMBER, update);
        log.info("session? {}", update);
        return "redirect:/profile";
    }
}
