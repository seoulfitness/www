package kr.seoulfitness.branchManager;

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

@Controller
@RequestMapping("/branchManagers")
public class BranchManagerManagerController {

    @Autowired
    private BranchManagerService branchManagerService;

    // 지점 관리자 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "지점 관리자");
        return "branchManager/create";
    }

    // 지점 관리자 등록 처리
    @PostMapping("/create")
    public String createPost(BranchManagerDto branchManager, HttpSession session, RedirectAttributes redirectAttributes) {
        // 지점 관리자 등록
        branchManager.setCreatedBy((String) session.getAttribute("userId"));
        branchManager.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = branchManagerService.create(branchManager);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "지점 관리자 등록이 완료되었습니다.");
            return "redirect:/branchManagers";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("branchManager", branchManager);
        return "redirect:/branchManagers/create";
    }

    // 지점 관리자 목록
    @GetMapping("")
    public String listGet(
        @RequestParam(value = "page", defaultValue = "1") int currentPage, 
        @RequestParam(required = false) String keyword,
        Model model
    ) {
        int listCountPerPage = 10;  // 한 페이지에서 불러올 게시글 수
        int pageCountPerPage = 5;   // 한 페이지에서 보여질 페이지 수
        int branchId = 0;
        Map<String, Object> result = branchManagerService.list(currentPage, listCountPerPage, pageCountPerPage, keyword, branchId);
        model.addAttribute("branchManagers", result.get("branchManagers"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "지점 관리자");
        return "branchManager/list";
    }

    // 지점 관리자 상세
    @GetMapping("/{branchManagerId}")
    public String readGet(@PathVariable int branchManagerId, Model model) {
        BranchManagerDto branchManager = branchManagerService.read(branchManagerId);
        model.addAttribute("branchManager", branchManager);
        model.addAttribute("pageTitle", "지점 관리자");
        return "branchManager/read";
    }

    // 지점 관리자 수정
    @GetMapping("/{branchManagerId}/update")
    public String updateGet(@PathVariable int branchManagerId, Model model) {   
        // 지점 관리자 존재 여부 확인
        BranchManagerDto branchManager = branchManagerService.read(branchManagerId);
        if (branchManager == null) {
            return "redirect:/branchManagers";
        }

        // 지점 관리자 수정
        model.addAttribute("branchManager", branchManager);
        model.addAttribute("pageTitle", "지점 관리자");
        return "branchManager/update";
    }

    // 지점 관리자 수정 처리
    @PostMapping("/{branchManagerId}/update")
    public String updatePost(@PathVariable int branchManagerId, BranchManagerDto branchManager, HttpSession session, RedirectAttributes redirectAttributes) {
        // 지점 관리자 존재 여부 확인
        branchManager.setBranchManagerId(branchManagerId);
        branchManager = branchManagerService.read(branchManagerId);
        if (branchManager == null) {
            return "redirect:/branchManagers";
        }

        // 지점 관리자 수정
        branchManager.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = branchManagerService.update(branchManager);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "지점 관리자 수정이 완료되었습니다.");
            return "redirect:/branchManagers/" + branchManagerId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 수정에 실패했습니다.");
        return "redirect:/branchManagers/" + branchManagerId + "/update";
    }

    // 지점 관리자 삭제
    @PostMapping("/{branchManagerId}/delete")
    public String deletePost(@PathVariable int branchManagerId, RedirectAttributes redirectAttributes) {
        // 지점 관리자 존재 여부 확인
        BranchManagerDto branchManager = branchManagerService.read(branchManagerId);
        if (branchManager == null) {
            return "redirect:/branchManagers";
        }

        // 지점 관리자 삭제
        int branchId = 0;
        boolean result = branchManagerService.delete(branchManagerId, branchId);
        if (!result) {
            redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 관리자 삭제에 실패했습니다.");
            return "redirect:/branchManagers/" + branchManagerId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 삭제에 실패했습니다.");
        return "redirect:/branchManagers/" + branchManagerId;
    }
}


