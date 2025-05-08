package kr.seoulfitness.admin.normalUser;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.seoulfitness.admin.user.UserDto;
import kr.seoulfitness.admin.user.UserService;

@Controller
@RequestMapping("/admin/normal-users")
public class NormalUserController {

    @Autowired
    private UserService userService;

    // 일반 회원 존재 여부 확인
    public boolean isUserExists(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        UserDto user = userService.read(params);
        return user != null;
    }
    
    // 일반 회원 목록 (화면, GET)
    @GetMapping("")
    public String list(
        @RequestParam(value = "page", defaultValue = "1") int currentPage, 
        @RequestParam(required = false) String keyword,
        Model model
    ) {
        // 파라미터 생성
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("listCountPerPage", 10);
        params.put("pageCountPerPage", 5);
        params.put("keyword", keyword);
        params.put("role", "NORMAL_USER");
        
        // 일반 회원 목록 조회
        Map<String, Object> result = userService.list(params);

        // 목록 페이지 전달
        model.addAttribute("users", result.get("users"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "일반 회원 관리");
        model.addAttribute("activePage", "normalUsers");

        return "normalUser/list";
    }

    // 일반 회원 상세 (화면, GET)
    @GetMapping("/{userId}")
    public String read(@PathVariable("userId") String userId, Model model) {
        // 일반 회원 존재 여부 확인
        if (!isUserExists(userId)) {
            return "redirect:/admin/normal-users";
        }

        // 일반 회원 상세 조회
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        UserDto user = userService.read(params);

        // 상세 페이지 전달
        model.addAttribute("user", user);
        return "normalUser/read";
    }
    
    // 일반 회원 삭제 (처리, POST)
    @PostMapping("/{userId}/delete")
    public String delete(@PathVariable("userId") String userId, RedirectAttributes redirectAttributes) {
        // 일반 회원 존재 여부 확인
        if (!isUserExists(userId)) {
            return "redirect:/admin/normal-users";
        }

        // 일반 회원 삭제
        boolean result = userService.delete(userId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "일반 회원 삭제가 완료되었습니다.");
            return "redirect:/admin/normal-users";
        }

        // 일반 회원 삭제 실패
        redirectAttributes.addFlashAttribute("errorMessage", "일반 회원 삭제에 실패했습니다.");
        return "redirect:/admin/normal-users/" + userId;
    }
}
