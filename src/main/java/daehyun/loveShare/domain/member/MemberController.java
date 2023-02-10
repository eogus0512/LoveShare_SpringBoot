package daehyun.loveShare.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/join")
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping
    public String joinForm(@ModelAttribute("member") Member member) {
        return "page/joinPage";
    }

    @PostMapping
    public String saveMember(@Valid @ModelAttribute Member member, BindingResult result) {
        if (result.hasErrors()) {
            return "page/joinPage";
        }

        memberRepository.save(member);
        return "redirect:/main";
    }

}
