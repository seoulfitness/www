package kr.seoulfitness.admin.department;

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

@Controller
@RequestMapping("/admin/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 학과 존재 여부 확인
    public boolean isDepartmentExists(int departmentId) {
        return departmentService.read(departmentId) != null;
    }

    // 학과 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "학과 관리");
        model.addAttribute("activePage", "departments");
        return "admin/department/create";
    }

    // 학과 등록 처리
    @PostMapping("/create")
    public String createPost(DepartmentDto department, HttpSession session, RedirectAttributes redirectAttributes) {
        department.setCreatedBy((String) session.getAttribute("userId"));
        department.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = departmentService.create(department);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "학과 등록이 완료되었습니다.");
            return "redirect:/admin/departments";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "학과 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("department", department);
        return "redirect:/admin/departments/create";
    }

    // 학과 목록
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

        // 학과 목록 조회
        Map<String, Object> result = departmentService.list(params);
        model.addAttribute("departments", result.get("departments"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "학과 관리");
        model.addAttribute("activePage", "departments");

        return "admin/department/list";
    }

    // 학과 상세
    @GetMapping("/{departmentId}")
    public String read(@PathVariable int departmentId, Model model) {
        // 학과 존재 여부 확인
        if (!isDepartmentExists(departmentId)) {
            return "redirect:/admin/departments";
        }

        DepartmentDto department = departmentService.read(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("pageTitle", "학과 관리");
        model.addAttribute("activePage", "departments");
        return "admin/department/read";
    }

    // 학과 수정
    @GetMapping("/{departmentId}/update")
    public String editForm(@PathVariable int departmentId, Model model) {   
        // 학과 존재 여부 확인
        if (!isDepartmentExists(departmentId)) {
            return "redirect:/admin/departments";
        }

        DepartmentDto department = departmentService.read(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("pageTitle", "학과 관리");
        model.addAttribute("activePage", "departments");
        return "admin/department/update";
    }

    // 학과 수정 처리
    @PostMapping("/{departmentId}/update")
    public String update(@PathVariable int departmentId, DepartmentDto department, HttpSession session, RedirectAttributes redirectAttributes) {
        // 학과 존재 여부 확인
        if (!isDepartmentExists(departmentId)) {
            return "redirect:/admin/departments";
        }

        department.setDepartmentId(departmentId);
        department.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = departmentService.update(department);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "학과 수정이 완료되었습니다.");
            return "redirect:/admin/departments/" + departmentId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "학과 수정에 실패했습니다.");
        return "redirect:/admin/departments/" + departmentId + "/update";
    }

    // 학과 삭제
    @PostMapping("/{departmentId}/delete")
    public String delete(@PathVariable int departmentId, RedirectAttributes redirectAttributes) {
        // 학과 존재 여부 확인
        if (!isDepartmentExists(departmentId)) {
            return "redirect:/admin/departments";
        }

        boolean result = departmentService.delete(departmentId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "학과 삭제가 완료되었습니다.");
            return "redirect:/admin/departments";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "학과 삭제에 실패했습니다.");
        return "redirect:/admin/departments/" + departmentId;
    }
}
