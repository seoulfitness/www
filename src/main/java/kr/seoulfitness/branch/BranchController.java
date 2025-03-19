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

import kr.seoulfitness.user.UserDto;

@Controller
@RequestMapping("/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    // 지점 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "지점 관리");
        return "branch/create";
    }

    // 지점 등록 처리
    @PostMapping("/create")
    public String createPost(BranchDto branch, HttpSession session, Model model) {
        // 세션에서 로그인한 사용자 정보를 가져옴
        UserDto loggedInUser = (UserDto) session.getAttribute("loggedInUser");
        branch.setCreatedBy(loggedInUser.getId());
        branch.setUpdatedBy(loggedInUser.getId());
        
        // 지점 등록
        boolean result = branchService.create(branch);
        if (result) {
            model.addAttribute("message", "지점 등록이 완료되었습니다.");
            return "redirect:/branches";
        } else {
            model.addAttribute("message", "지점 등록에 실패했습니다.");
            return "redirect:/branches/create";
        }
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

        // 지점 관리자 정보 조회

        List<UserDto> branchManagers = userService.getBranchManagers(, 100, 100, "", "branchManager");
        model.addAttribute("branchManagers", branchManagers);

        return "branch/list";
    }

    // 지점 상세
    @GetMapping("/{branchId}")
    public String readGet(@PathVariable int branchId, Model model) {
        BranchDto branch = branchService.read(branchId);
        model.addAttribute("branch", branch);
        model.addAttribute("pageTitle", "지점 상세");
        return "branch/read";
    }

    // 지점 수정
    @GetMapping("/{branchId}/update")
    public String updateGet(@PathVariable int branchId, Model model) {   
        BranchDto branch = branchService.read(branchId);
        model.addAttribute("branch", branch);
        model.addAttribute("pageTitle", "지점 수정");
        return "branch/update";
    }

    // 지점 수정 처리
    @PostMapping("/{branchId}/update")
    public String updatePost(@PathVariable int branchId, BranchDto branch, HttpSession session, Model model) {
        // 지점 존재 여부 확인
        branch.setBranchId(branchId);
        branch = branchService.read(branchId);
        if (branch == null) {
            return "redirect:/branches";
        }

        // 세션에서 로그인한 사용자 정보를 가져옴
        UserDto loggedInUser = (UserDto) session.getAttribute("loggedInUser");
        branch.setUpdatedBy(loggedInUser.getId());

        // 지점 수정
        boolean result = branchService.update(branch);
        if (result) {
            model.addAttribute("message", "지점 수정이 완료되었습니다.");
            return "redirect:/branches/" + branchId;
        } else {
            model.addAttribute("message", "지점 수정에 실패했습니다.");
            return "redirect:/branches/" + branchId + "/update";
        }
    }

    // 지점 삭제
    @PostMapping("/{branchId}/delete")
    public String deletePost(@PathVariable int branchId, Model model) {
        // 지점 존재 여부 확인
        BranchDto branch = branchService.read(branchId);
        if (branch == null) {
            return "redirect:/branches";
        }

        // 지점 삭제
        boolean result = branchService.delete(branchId);
        if (result) {
            model.addAttribute("message", "지점 삭제가 완료되었습니다.");
            return "redirect:/branches";
        } else {
            model.addAttribute("message", "지점 삭제에 실패했습니다.");
            return "redirect:/branches/" + branchId;
        }
    }
}


