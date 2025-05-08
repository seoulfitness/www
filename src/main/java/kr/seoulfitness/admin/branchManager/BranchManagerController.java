package kr.seoulfitness.admin.branchManager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
@RequestMapping("/admin/branchManagers")
public class BranchManagerController {

    @Autowired
    private UserService userService;

    // 지점 관리자 존재 여부 확인
    public boolean isUserExists(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        UserDto user = userService.read(params);
        return user != null;
    }

    // 지점 관리자 등록
    @GetMapping("/create")
    public String createGet(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("pageTitle", "지점 관리자");
        model.addAttribute("activePage", "branchManagers");
        return "admin/branchManager/create";
    }

    // 지점 관리자 등록 처리
    @PostMapping("/create")
    public String createPost(UserDto user, BranchManagerDto branchManager, HttpSession session, RedirectAttributes redirectAttributes) {
        // 지점 관리자 등록
        user.setRole("BRANCH_MANAGER");
        user.setCreatedBy((String) session.getAttribute("userId"));
        user.setUpdatedBy((String) session.getAttribute("userId"));
        boolean resultUserCreate = userService.create(user);

        // 지점 관리자 등록 성공
        if (resultUserCreate) {
            redirectAttributes.addFlashAttribute("successMessage", "지점 관리자 등록이 완료되었습니다.");
            return "redirect:/admin/branchManagers";
        }

        // 지점 관리자 등록 실패
        redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 등록에 실패했습니다.");
        return "redirect:/admin/branchManagers/create";
    }

    // 지점 관리자 목록
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
        params.put("role", "BRANCH_MANAGER");

        // 지점 관리자 목록 조회
        Map<String, Object> result = userService.list(params);
        model.addAttribute("users", result.get("users"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));

        // 목록 페이지 설정        
        model.addAttribute("pageTitle", "지점 관리자");
        model.addAttribute("activePage", "branchManagers");

        return "admin/branchManager/list";
    }

    // 지점 관리자 상세
    @GetMapping("/{userId}")
    public String read(@PathVariable String userId, Model model) {
        // 지점 관리자 존재 여부 확인
        if (!isUserExists(userId)) {
            return "redirect:/admin/branchManagers";
        }

        // 지점 관리자 조회
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        UserDto user = userService.read(params);
        model.addAttribute("user", user);

        // 추가 페이지 설정
        model.addAttribute("pageTitle", "지점 관리자");
        model.addAttribute("activePage", "branchManagers");
        return "admin/branchManager/read";
    }

    // 지점 관리자 수정
    @GetMapping("/{userId}/update")
    public String updateGet(@PathVariable String userId, Model model, RedirectAttributes redirectAttributes) { 
        // 지점 관리자 존재 여부 확인
        if (!isUserExists(userId)) {
            return "redirect:/admin/branchManagers";
        }

        // 지점 관리자 조회
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        UserDto user = userService.read(params);
        model.addAttribute("user", user);

        // 추가 페이지 설정
        model.addAttribute("pageTitle", "지점 관리자");
        model.addAttribute("activePage", "branchManagers");
        return "admin/branchManager/update";
    }

    // 지점 관리자 수정 처리
    @PostMapping("/{userId}/update")
    public String updatePost(@PathVariable String userId, UserDto user, HttpSession session, RedirectAttributes redirectAttributes) {
        // 지점 관리자 존재 여부 확인
        if (!isUserExists(userId)) {
            return "redirect:/admin/branchManagers";
        }

        // 지점 관리자 수정
        user.setUserId(userId);
        user.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = userService.update(user);

        // 지점 관리자 수정 성공
        if (result) {
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                redirectAttributes.addFlashAttribute("successMessage", "지점 관리자 비밀번호 수정이 완료되었습니다.");
            } else {
                redirectAttributes.addFlashAttribute("successMessage", "지점 관리자 수정이 완료되었습니다.");
            }
            return "redirect:/admin/branchManagers/" + userId;
        }

        // 지점 관리자 수정 실패
        redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 수정에 실패했습니다.");
        return "redirect:/admin/branchManagers/" + userId + "/update";
    }

    // 지점 관리자 삭제
    @PostMapping("/{userId}/delete")
    public String delete(@PathVariable String userId, RedirectAttributes redirectAttributes) {
        // 지점 관리자 존재 여부 확인
        if (!isUserExists(userId)) {
            return "redirect:/admin/branchManagers";
        }

        // 지점 관리자 삭제
        boolean result = userService.delete(userId);

        // 지점 관리자 삭제 성공
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "지점 관리자 삭제가 완료되었습니다.");
            return "redirect:/admin/branchManagers";
        }

        // 지점 관리자 삭제 실패
        redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 삭제에 실패했습니다.");
        return "redirect:/admin/branchManagers/" + userId;
    }
}


