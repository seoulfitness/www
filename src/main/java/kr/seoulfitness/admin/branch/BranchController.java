package kr.seoulfitness.admin.branch;

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

import kr.seoulfitness.admin.branchManager.BranchManagerService;

@Controller
@RequestMapping("/admin/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchManagerService branchManagerService;

    // 지점 존재 여부 확인
    public boolean isBranchExists(int branchId) {
        return branchService.read(branchId) != null;
    }

    // 지점 등록
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("pageTitle", "지점 관리");
        model.addAttribute("activePage", "branches");
        return "admin/branch/create";
    }

    // 지점 등록 처리
    @PostMapping("/create")
    public String create(BranchDto branch, HttpSession session, RedirectAttributes redirectAttributes) {
        // 지점 등록
        branch.setCreatedBy((String) session.getAttribute("userId"));
        branch.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = branchService.create(branch);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "지점 등록이 완료되었습니다.");
            return "redirect:/admin/branches";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "지점 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("branch", branch);
        return "redirect:/admin/branches/create";
    }

    // 지점 목록
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

        // 지점 목록 조회
        Map<String, Object> result = branchService.list(params);

        // 목록 페이지 설정
        model.addAttribute("branches", result.get("branches"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "지점 관리");
        model.addAttribute("activePage", "branches");

        /*
        // 지점 관리자 정보 조회
        Map<String, Object> branchManagers = userService.list(1, 100, 100, "", "branchManager");

        // 지점 목록에 지점 관리자 정보 추가
        List<BranchDto> branches = (List<BranchDto>) result.get("branches");
        List<UserDto> managers = (List<UserDto>) branchManagers.get("users");
        
        for (BranchDto branch : branches) {
            List<UserDto> branchManagersInBranch = new ArrayList<>();
            for (UserDto manager : managers) {
                if (manager.getBranchId() != null && manager.getBranchId().equals(branch.getBranchId())) {
                    branchManagersInBranch.add(manager);
                }
            }
            branch.setBranchManagers(branchManagersInBranch);
        }
        */

        return "admin/branch/list";
    }

    // 지점 상세
    @GetMapping("/{branchId}")
    public String read(@PathVariable int branchId, Model model) {
        // 지점 존재 여부 확인
        if (!isBranchExists(branchId)) {
            return "redirect:/admin/branches";
        }

        // 지점 조회
        BranchDto branch = branchService.read(branchId);

        model.addAttribute("branch", branch);
        model.addAttribute("pageTitle", "지점 관리");
        model.addAttribute("activePage", "branches");
        return "admin/branch/read";
    }

    // 지점 수정
    @GetMapping("/{branchId}/update")
    public String editForm(@PathVariable int branchId, Model model) {   
        // 지점 존재 여부 확인
        if (!isBranchExists(branchId)) {
            return "redirect:/admin/branches";
        }

        // 지점 조회
        BranchDto branch = branchService.read(branchId);

        // 지점 수정
        model.addAttribute("branch", branch);
        model.addAttribute("pageTitle", "지점 관리");
        model.addAttribute("activePage", "branches");
        return "admin/branch/update";
    }

    // 지점 수정 처리
    @PostMapping("/{branchId}/update")
    public String update(BranchDto branch, HttpSession session, RedirectAttributes redirectAttributes) {
        // 지점 존재 여부 확인
        if (!isBranchExists(branch.getBranchId())) {
            return "redirect:/admin/branches";
        }

        // 지점 수정
        branch.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = branchService.update(branch);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "지점 수정이 완료되었습니다.");
            return "redirect:/admin/branches/" + branch.getBranchId();
        }

        redirectAttributes.addFlashAttribute("errorMessage", "지점 수정에 실패했습니다.");
        return "redirect:/admin/branches/" + branch.getBranchId() + "/update";
    }

    // 지점 삭제
    @PostMapping("/{branchId}/delete")
    public String delete(@PathVariable int branchId, RedirectAttributes redirectAttributes) {
        // 지점 존재 여부 확인
        if (!isBranchExists(branchId)) {
            return "redirect:/admin/branches";
        }

        // 지점 관리자에서 사용자 삭제
        Map<String, Object> params = new HashMap<>();
        params.put("branchId", branchId);
        params.put("branchManagerId", 0);
        boolean branchManagerResult = branchManagerService.delete(params);
        if (!branchManagerResult) {
            redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 삭제에 실패했습니다.");
            return "redirect:/admin/branches/" + branchId;
        }

        // 지점 삭제
        boolean branchResult = branchService.delete(branchId);
        if (branchResult) {
            redirectAttributes.addFlashAttribute("successMessage", "지점 삭제가 완료되었습니다.");
            return "redirect:/admin/branches";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "지점 삭제에 실패했습니다.");
        return "redirect:/admin/branches/" + branchId;
    }
}


