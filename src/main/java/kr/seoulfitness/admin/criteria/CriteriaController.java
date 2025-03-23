package kr.seoulfitness.admin.criteria;

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

import kr.seoulfitness.admin.department.DepartmentService;
import kr.seoulfitness.admin.school.SchoolService;

@Controller
@RequestMapping("/admin/criterias")
public class CriteriaController {

    @Autowired
    private CriteriaService criteriaService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private DepartmentService departmentService;

    // 대입시 요강 존재 여부 확인
    public boolean isCriteriaExists(int criteriaId) {
        return criteriaService.find(criteriaId) != null;
    }

    // 대입시 요강 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        // 학교 목록 조회
        Map<String, Object> schoolParams = new HashMap<>();
        schoolParams.put("currentPage", 1);
        schoolParams.put("listCountPerPage", 1000);
        schoolParams.put("pageCountPerPage", 1000);
        Map<String, Object> schoolResult = schoolService.findAll(schoolParams);
        model.addAttribute("schools", schoolResult.get("schools"));

        // 학과 목록 조회
        Map<String, Object> departmentParams = new HashMap<>();
        departmentParams.put("currentPage", 1);
        departmentParams.put("listCountPerPage", 1000);
        departmentParams.put("pageCountPerPage", 1000);
        Map<String, Object> departmentResult = departmentService.findAll(departmentParams);
        model.addAttribute("departments", departmentResult.get("departments"));

        model.addAttribute("pageTitle", "대입시 요강 관리");
        model.addAttribute("activePage", "criterias");
        return "admin/criteria/create";
    }

    // 대입시 요강 등록 처리
    @PostMapping("/create")
    public String createPost(CriteriaDto criteria, HttpSession session, RedirectAttributes redirectAttributes) {
        // 대입시 요강 등록
        criteria.setCreatedBy((String) session.getAttribute("userId"));
        criteria.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = criteriaService.create(criteria);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "대입시 요강 등록이 완료되었습니다.");
            return "redirect:/admin/criterias";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "대입시 요강 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("criteria", criteria);
        return "redirect:/admin/criterias/create";
    }

    // 대입시 요강 목록
    @GetMapping("")
    public String list(
        @RequestParam(value = "page", defaultValue = "1") int currentPage, 
        @RequestParam(required = false) String keyword,
        Model model
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("listCountPerPage", 10);
        params.put("pageCountPerPage", 5);
        params.put("keyword", keyword);

        Map<String, Object> result = criteriaService.findAll(params);
        model.addAttribute("criterias", result.get("criterias"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "대입시 요강 관리");
        model.addAttribute("activePage", "criterias");

        return "admin/criteria/list";
    }

    // 대입시 요강 상세
    @GetMapping("/{criteriaId}")
    public String view(@PathVariable int criteriaId, Model model) {
        // 대입시 요강 존재 여부 확인
        if (!isCriteriaExists(criteriaId)) {
            return "redirect:/admin/criterias";
        }

        // 대입시 요강 상세
        CriteriaDto criteria = criteriaService.find(criteriaId);
        model.addAttribute("criteria", criteria);
        model.addAttribute("pageTitle", "대입시 요강 관리");
        model.addAttribute("activePage", "criterias");
        return "admin/criteria/read";
    }

    // 대입시 요강 수정
    @GetMapping("/{criteriaId}/update")
    public String editForm(@PathVariable int criteriaId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {   
        // 대입시 요강 존재 여부 확인
        if (!isCriteriaExists(criteriaId)) {
            return "redirect:/admin/criterias";
        }

        // 대입시 요강 상세
        CriteriaDto criteria = criteriaService.find(criteriaId);
        model.addAttribute("criteria", criteria);
        model.addAttribute("pageTitle", "대입시 요강 관리");
        model.addAttribute("activePage", "criterias");
        return "admin/criteria/update";
    }

    // 대입시 요강 수정 처리
    @PostMapping("/{criteriaId}/update")
    public String update(@PathVariable int criteriaId, CriteriaDto criteria, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        // 대입시 요강 존재 여부 확인
        if (!isCriteriaExists(criteriaId)) {
            return "redirect:/admin/criterias";
        }
        
        // 대입시 요강 수정
        criteria.setCriteriaId(criteriaId);
        criteria.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = criteriaService.update(criteria);
        if (!result) {
            model.addAttribute("criteria", criteria);
            redirectAttributes.addFlashAttribute("errorMessage", "대입시 요강 수정에 실패했습니다.");
            return "redirect:/admin/criterias/" + criteriaId + "/update";
        }

        redirectAttributes.addFlashAttribute("successMessage", "대입시 요강 수정이 완료되었습니다.");
        return "redirect:/admin/criterias/" + criteriaId;
    }

    // 대입시 요강 삭제
    @PostMapping("/{criteriaId}/delete")
    public String delete(@PathVariable int criteriaId, RedirectAttributes redirectAttributes) {
        // 대입시 요강 존재 여부 확인
        if (!isCriteriaExists(criteriaId)) {
            return "redirect:/admin/criterias";
        }

        // 대입시 요강 삭제
        boolean result = criteriaService.delete(criteriaId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "대입시 요강 삭제가 완료되었습니다.");
            return "redirect:/admin/criterias";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "대입시 요강 삭제에 실패했습니다.");
        return "redirect:/admin/criterias/" + criteriaId;
    }
}
