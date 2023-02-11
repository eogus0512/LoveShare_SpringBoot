package daehyun.loveShare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class BasicController {
    @GetMapping("/main")
    public String main() {
        return "page/main";
    }
}
