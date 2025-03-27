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

import kr.seoulfitness.admin.branch.BranchService;
import kr.seoulfitness.admin.user.UserDto;
import kr.seoulfitness.admin.user.UserService;

@Controller
@RequestMapping("/admin/branchManagers")
public class BranchManagerController {

    @Autowired
    private BranchManagerService branchManagerService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private UserService userService;

    // 지점 관리자 존재 여부 확인
    public boolean isBranchManagerExists(int branchManagerId) {
        return branchManagerService.read(branchManagerId) != null;
    }

    // 지점 관리자 등록
    @GetMapping("/create")
    public String createForm(Model model, RedirectAttributes redirectAttributes) {
        // 지점 목록 조회
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", 1);
        params.put("listCountPerPage", 100);
        params.put("pageCountPerPage", 100);
        params.put("keyword", "");
        
        Map<String, Object> result = branchService.list(params);
        if (result.get("branches") == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "등록된 지점이 없습니다.");
            return "redirect:/admin/branchManagers";
        }

        model.addAttribute("branches", result.get("branches")); // 지점 목록
        model.addAttribute("pageTitle", "지점 관리자");
        model.addAttribute("activePage", "branchManagers");
        return "admin/branchManager/create";
    }

    // 지점 관리자 등록 처리
    @PostMapping("/create")
    public String create(UserDto user, BranchManagerDto branchManager, HttpSession session, RedirectAttributes redirectAttributes) {
        // 사용자 등록
        user.setRole("지점 관리자");
        user.setCreatedBy((String) session.getAttribute("userId"));
        user.setUpdatedBy((String) session.getAttribute("userId"));
        boolean resultUserCreate = userService.create(user);
        if (!resultUserCreate) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("branchManager", branchManager);
            redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 등록에 실패했습니다.");
            return "redirect:/admin/branchManagers/create";
        }

        // 사용자를 지점 관리자로 등록
        branchManager.setCreatedBy((String) session.getAttribute("userId"));
        branchManager.setUpdatedBy((String) session.getAttribute("userId"));
        boolean resultBranchManagerCreate = branchManagerService.create(branchManager);
        if (!resultBranchManagerCreate) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("branchManager", branchManager);
            redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 등록에 실패했습니다.");
            return "redirect:/admin/branchManagers/create";
        }

        redirectAttributes.addFlashAttribute("successMessage", "지점 관리자 등록이 완료되었습니다.");
        return "redirect:/admin/branchManagers";
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

        // 지점 관리자 목록 조회
        Map<String, Object> result = branchManagerService.list(params);

        // 목록 페이지 설정
        model.addAttribute("branchManagers", result.get("branchManagers"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "지점 관리자");
        model.addAttribute("activePage", "branchManagers");

        return "admin/branchManager/list";
    }

    // 지점 관리자 상세
    @GetMapping("/{branchManagerId}")
    public String read(@PathVariable int branchManagerId, Model model) {
        // 지점 관리자 존재 여부 확인
        if (!isBranchManagerExists(branchManagerId)) {
            return "redirect:/admin/branchManagers";
        }

        // 지점 관리자 조회
        BranchManagerDto branchManager = branchManagerService.read(branchManagerId);
        model.addAttribute("branchManager", branchManager);
        model.addAttribute("pageTitle", "지점 관리자");
        model.addAttribute("activePage", "branchManagers");
        return "admin/branchManager/read";
    }

    // 지점 관리자 수정
    @GetMapping("/{branchManagerId}/update")
    public String editForm(@PathVariable int branchManagerId, Model model, RedirectAttributes redirectAttributes) { 
        // 지점 관리자 존재 여부 확인
        BranchManagerDto existsBranchManager = branchManagerService.read(branchManagerId);
        if (existsBranchManager == null) {
            return "redirect:/admin/branchManagers";
        }
        
        // 지점 목록 조회
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", 1);
        params.put("listCountPerPage", 100);
        params.put("pageCountPerPage", 100);
        params.put("keyword", "");
        Map<String, Object> result = branchService.list(params);
        if (result.get("branches") == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "등록된 지점이 없습니다.");
            return "redirect:/admin/branchManagers";
        }
        model.addAttribute("branches", result.get("branches")); // 지점 목록

        // 지점 관리자 존재 여부 확인
        BranchManagerDto branchManager = branchManagerService.read(branchManagerId);
        if (branchManager == null) {
            return "redirect:/admin/branchManagers";
        }

        // 지점 관리자 수정
        model.addAttribute("branchManager", branchManager);
        model.addAttribute("pageTitle", "지점 관리자");
        model.addAttribute("activePage", "branchManagers");
        return "admin/branchManager/update";
    }

    // 지점 관리자 수정 처리
    @PostMapping("/{branchManagerId}/update")
    public String update(@PathVariable int branchManagerId, BranchManagerDto branchManager, HttpSession session, RedirectAttributes redirectAttributes) {
        // 지점 관리자 존재 여부 확인
        BranchManagerDto existsBranchManager = branchManagerService.read(branchManagerId);
        if (existsBranchManager == null) {
            return "redirect:/admin/branchManagers";
        }

        // 지점 관리자 수정
        branchManager.setBranchManagerId(branchManagerId);
        branchManager.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = branchManagerService.update(branchManager);
        if (!result) {
            redirectAttributes.addFlashAttribute("branchManager", branchManager);
            redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 수정에 실패했습니다.");
            return "redirect:/admin/branchManagers/" + branchManagerId + "/update";
        }

        redirectAttributes.addFlashAttribute("successMessage", "지점 관리자 수정이 완료되었습니다.");
        return "redirect:/admin/branchManagers/" + branchManagerId;        
    }

    // 지점 관리자 삭제
    @PostMapping("/{branchManagerId}/delete")
    public String delete(@PathVariable int branchManagerId, RedirectAttributes redirectAttributes) {
        // 지점 관리자 존재 여부 확인
        BranchManagerDto branchManager = branchManagerService.read(branchManagerId);
        if (branchManager == null) {
            return "redirect:/admin/branchManagers";
        }

        // 지점 관리자 삭제
        Map<String, Object> params = new HashMap<>();
        params.put("branchManagerId", branchManagerId);
        params.put("branchId", 0);
        boolean result = branchManagerService.delete(params);
        if (!result) {
            redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 관리자 삭제에 실패했습니다.");
            return "redirect:/admin/branchManagers/" + branchManagerId;
        }

        redirectAttributes.addFlashAttribute("successMessage", "지점 관리자 삭제가 완료되었습니다.");
        return "redirect:/admin/branchManagers";
    }
}


