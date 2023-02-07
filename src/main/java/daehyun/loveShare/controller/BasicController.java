package daehyun.loveShare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {
    @GetMapping("navbar")
    public String navbar() {
        return "layout/layout";
    }
}
