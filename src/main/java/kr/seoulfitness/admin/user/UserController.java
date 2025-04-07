package kr.seoulfitness.admin.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 사용자 존재 여부 확인
    public boolean isUserExists(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        UserDto user = userService.read(params);
        return user != null;
    }

    // 사용자 등록 (화면, GET)
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "회원 등록");
        model.addAttribute("activePage", "users");  
        return "user/create";
    }

    // 사용자 처리 (화면, POST)
    @PostMapping("/create")
    public String createPost() {
        return "redirect:/users";
    }

    // 사용자 목록 (화면, GET)
    @GetMapping("/list")
    public String list() {
        return "user/list";
    }

    // 사용자 상세 (화면, GET)
    @GetMapping("/{id}")
    public String read(@PathVariable("id") int id) {
        return "user/read";
    }

    // 사용자 수정 (화면, GET)
    @GetMapping("/{id}/update")
    public String updateGet(@PathVariable("id") int id) {
        return "user/update";
    }

    // 사용자 수정 (화면, POST)
    @PostMapping("/{id}/update")
    public String updateP(@PathVariable("id") int id) {
        return "redirect:/users/{id}";
    }
    
    // 사용자 삭제 (처리, POST)
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        return "redirect:/users";
    }
}
