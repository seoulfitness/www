package kr.seoulfitness;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController: 클라이언트 요청을 처리하는 스프링 MVC 컨트롤러
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {
    @GetMapping("")
    public String home() {
        return "dashboard/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard/dashboard";
    }
}
