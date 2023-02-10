package daehyun.loveShare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {
    @GetMapping("/main")
    public String navbar() {
        return "page/main";
    }
}
