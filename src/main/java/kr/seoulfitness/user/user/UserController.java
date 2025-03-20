package kr.seoulfitness.user.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    
    // 사용자 등록 (화면, GET)
    @GetMapping("/create")
    public String createGet() {
        return "user/create";
    }

    // 사용자 처리 (화면, POST)
    @PostMapping("/create")
    public String createPost() {
        return "redirect:/users";
    }

    // 사용자 목록 (화면, GET)
    @GetMapping("/list")
    public String listGet() {
        return "user/list";
    }

    // 사용자 상세 (화면, GET)
    @GetMapping("/{id}")
    public String detailGet(@PathVariable("id") int id) {
        return "user/read";
    }

    // 사용자 수정 (화면, GET)
    @GetMapping("/{id}/update")
    public String editGet(@PathVariable("id") int id) {
        return "user/update";
    }

    // 사용자 수정 (화면, POST)
    @PostMapping("/{id}/update")
    public String editPost(@PathVariable("id") int id) {
        return "redirect:/users/{id}";
    }
    
    // 사용자 삭제 (처리, POST)
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable("id") int id) {
        return "redirect:/users";
    }
}
