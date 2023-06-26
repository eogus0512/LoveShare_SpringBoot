package daehyun.loveShare.web.profile;

import daehyun.loveShare.domain.member.Member;
import daehyun.loveShare.domain.member.MemberRepository;
import daehyun.loveShare.domain.profile.*;
import daehyun.loveShare.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProfileController {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final FileStore fileStore;

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

    @GetMapping("/profile-add")
    public String addPost(@ModelAttribute PostForm form) {
        return "page/profilePage_add";
    }

    @PostMapping("/profile-add")
    public String savePost(@ModelAttribute PostForm form, RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {
        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImages());

        //데이터베이스에 저장
        Post post = new Post();
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        log.info("gender={}", member.getGender());
        if (member.getGender().equals("MEN")) {
            post.setBoyFriend(member.getLoginId());
            post.setGirlFriend(member.getLoverName());
        } else {
            post.setBoyFriend(member.getLoverName());
            post.setGirlFriend(member.getLoginId());
        }
        post.setContent(form.getContent());
        post.setHashTag(form.getHashTag());
        post.setImages(storeImageFiles);
        log.info("post={}", post);
        postRepository.save(post);

        redirectAttributes.addAttribute("postId", post.getId());
        return "redirect:/profile/post/{postId}";
    }

    @GetMapping("/profile/post/{id}")
    public String postView(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id);
        model.addAttribute("post", post);
        return "page/profilePage_post";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @GetMapping("/profile/posts")
    public String redirect_posts(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (member.getGender().equals("MEN")) {
            redirectAttributes.addAttribute("boyFriend", member.getLoginId());
            redirectAttributes.addAttribute("girlFriend", member.getLoverName());
        } else {
            redirectAttributes.addAttribute("boyFriend", member.getLoverName());
            redirectAttributes.addAttribute("girlFriend", member.getLoginId());
        }

        return "redirect:/profile/couple/{boyFriend}/{girlFriend}";
    }

    @GetMapping("/profile/couple/{boyFriend}/{girlFriend}")
    public String couplePosts(@PathVariable String boyFriend, @PathVariable String girlFriend, Model model) {
        List<Post> post = postRepository.findByBoyFriend(boyFriend);
        log.info("post={}", post);
        model.addAttribute("post", post);
        return "page/profilePage_couple";
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
