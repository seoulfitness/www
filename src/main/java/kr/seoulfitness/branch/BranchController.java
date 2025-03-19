package kr.seoulfitness.branch;

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

import kr.seoulfitness.branchManager.BranchManagerService;

@Controller
@RequestMapping("/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchManagerService branchManagerService;

    // 지점 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "지점 관리");
        return "branch/create";
    }

    // 지점 등록 처리
    @PostMapping("/create")
    public String createPost(BranchDto branch, HttpSession session, RedirectAttributes redirectAttributes) {
        // 지점 등록
        branch.setCreatedBy((String) session.getAttribute("userId"));
        branch.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = branchService.create(branch);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "지점 등록이 완료되었습니다.");
            return "redirect:/branches";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "지점 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("branch", branch);
        return "redirect:/branches/create";
    }

    // 지점 목록
    @GetMapping("")
    public String listGet(
        @RequestParam(value = "page", defaultValue = "1") int currentPage, 
        @RequestParam(required = false) String keyword,
        Model model
    ) {
        int listCountPerPage = 10;  // 한 페이지에서 불러올 게시글 수
        int pageCountPerPage = 5;   // 한 페이지에서 보여질 페이지 수
        Map<String, Object> result = branchService.list(currentPage, listCountPerPage, pageCountPerPage, keyword);
        model.addAttribute("branches", result.get("branches"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "지점 관리");

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

        return "branch/list";
    }

    // 지점 상세
    @GetMapping("/{branchId}")
    public String readGet(@PathVariable int branchId, Model model) {
        BranchDto branch = branchService.read(branchId);
        model.addAttribute("branch", branch);
        model.addAttribute("pageTitle", "지점 관리");
        return "branch/read";
    }

    // 지점 수정
    @GetMapping("/{branchId}/update")
    public String updateGet(@PathVariable int branchId, Model model) {   
        // 지점 존재 여부 확인
        BranchDto branch = branchService.read(branchId);
        if (branch == null) {
            return "redirect:/branches";
        }

        // 지점 수정
        model.addAttribute("branch", branch);
        model.addAttribute("pageTitle", "지점 관리");
        return "branch/update";
    }

    // 지점 수정 처리
    @PostMapping("/{branchId}/update")
    public String updatePost(@PathVariable int branchId, BranchDto branch, HttpSession session, RedirectAttributes redirectAttributes) {
        // 지점 존재 여부 확인
        branch.setBranchId(branchId);
        branch = branchService.read(branchId);
        if (branch == null) {
            return "redirect:/branches";
        }

        // 지점 수정
        branch.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = branchService.update(branch);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "지점 수정이 완료되었습니다.");
            return "redirect:/branches/" + branchId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "지점 수정에 실패했습니다.");
        return "redirect:/branches/" + branchId + "/update";
    }

    // 지점 삭제
    @PostMapping("/{branchId}/delete")
    public String deletePost(@PathVariable int branchId, RedirectAttributes redirectAttributes) {
        // 지점 존재 여부 확인
        BranchDto branch = branchService.read(branchId);
        if (branch == null) {
            return "redirect:/branches";
        }

        // 지점 관리자 삭제
        boolean branchManagerResult = branchManagerService.delete(0, branchId);
        if (!branchManagerResult) {
            redirectAttributes.addFlashAttribute("errorMessage", "지점 관리자 삭제에 실패했습니다.");
            return "redirect:/branches/" + branchId;
        }

        // 지점 삭제
        boolean branchResult = branchService.delete(branchId);
        if (branchResult) {
            redirectAttributes.addFlashAttribute("successMessage", "지점 삭제가 완료되었습니다.");
            return "redirect:/branches";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "지점 삭제에 실패했습니다.");
        return "redirect:/branches/" + branchId;
    }
}


