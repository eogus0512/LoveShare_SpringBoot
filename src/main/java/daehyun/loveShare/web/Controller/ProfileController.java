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
        Optional<Member> subscriber = memberRepository.findByLoverName(member.getLoginId());
        log.info("subscriber? {}", subscriber);
        if (subscriber.isPresent()) {
            model.addAttribute("subscriberName", subscriber.get().getLoginId());
        } else {
            model.addAttribute("subscriberName", null);
        }

        return "page/profilePage";
    }

    @PostMapping("/profile-request")
    public String profile_request(@RequestParam String loverName, HttpServletRequest request) {
        update(loverName, request);
        return "redirect:/profile";
    }

    @PostMapping("/profile-request-cancel")
    public String profile_request_cancel(HttpServletRequest request) {
        update(null, request);
        return "redirect:/profile";
    }

    @PostMapping("/profile-accept")
    public String profile_accept(@RequestParam("subscriberName1") String subscriber, HttpServletRequest request, Model model) {
        update(subscriber, request);
        model.addAttribute("subscriberName", subscriber);
        return "redirect:/profile";
    }

    @PostMapping("/profile-reject")
    public String profile_reject(@RequestParam("subscriberName2") String subscriber) {
        Optional<Member> member = memberRepository.findByLoginId(subscriber);
        Member update = memberRepository.findById(member.get().getId());
        update.setLoverName(null);
        return "redirect:/profile";
    }

    public void update(String data, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Member update = memberRepository.findById(member.getId());
        update.setLoverName(data);
        session.setAttribute(SessionConst.LOGIN_MEMBER, update);
        log.info("session? {}", update);
    }
}
