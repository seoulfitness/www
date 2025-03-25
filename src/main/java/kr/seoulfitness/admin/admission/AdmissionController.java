package kr.seoulfitness.admin.admission;

import java.util.HashMap;
import java.util.List;
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
import kr.seoulfitness.admin.earlyAdmission.EarlyAdmissionDto;
import kr.seoulfitness.admin.earlyAdmission.EarlyAdmissionService;
import kr.seoulfitness.admin.school.SchoolService;

@Controller
@RequestMapping("/admin/admissions")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EarlyAdmissionService earlyAdmissionService;

    // 대입시 요강 존재 여부 확인
    public boolean isAdmissionExists(int admissionId) {
        return admissionService.find(admissionId) != null;
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
        model.addAttribute("activePage", "admissions");
        return "admin/admission/create";
    }

    // 대입시 요강 등록 처리
    @PostMapping("/create")
    public String createPost(AdmissionDto admission, HttpSession session, RedirectAttributes redirectAttributes) {
        // 대입시 요강 등록
        admission.setCreatedBy((String) session.getAttribute("userId"));
        admission.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = admissionService.create(admission);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "대입시 요강 등록이 완료되었습니다.");
            return "redirect:/admin/admissions";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "대입시 요강 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("admission", admission);
        return "redirect:/admin/admissions/create";
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

        Map<String, Object> result = admissionService.findAll(params);

        // 입시 요강 목록
        List<AdmissionDto> admissions = (List<AdmissionDto>) result.get("admissions");
        for (AdmissionDto admission : admissions) {
            // 수시 모집 여부 확인
            if (admission.getEarlyAdmission().equals("Y")) {
                // 수시 모집 상세보기
                Map<String, Object> earlyAdmissionParams = new HashMap<>();
                earlyAdmissionParams.put("admissionId", admission.getAdmissionId());
                EarlyAdmissionDto earlyAdmission = earlyAdmissionService.find(earlyAdmissionParams);
                admission.setEarlyAdmissionDto(earlyAdmission);
            }
        }
        
        model.addAttribute("admissions", result.get("admissions"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "대입시 요강 관리");
        model.addAttribute("activePage", "admissions");

        return "admin/admission/list";
    }

    // 대입시 요강 상세
    @GetMapping("/{admissionId}")
    public String view(@PathVariable int admissionId, Model model) {
        // 대입시 요강 존재 여부 확인
        if (!isAdmissionExists(admissionId)) {
            return "redirect:/admin/admissions";
        }

        // 대입시 요강 상세
        AdmissionDto admission = admissionService.find(admissionId);
        model.addAttribute("admission", admission);
        model.addAttribute("pageTitle", "대입시 요강 관리");
        model.addAttribute("activePage", "admissions");
        return "admin/admission/read";
    }

    // 대입시 요강 수정
    @GetMapping("/{admissionId}/update")
    public String editForm(@PathVariable int admissionId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {   
        // 대입시 요강 존재 여부 확인
        if (!isAdmissionExists(admissionId)) {
            return "redirect:/admin/admissions";
        }

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

        // 대입시 요강 상세
        AdmissionDto admission = admissionService.find(admissionId);
        model.addAttribute("admission", admission);
        model.addAttribute("pageTitle", "대입시 요강 관리");
        model.addAttribute("activePage", "admissions");
        return "admin/admission/update";
    }

    // 대입시 요강 수정 처리
    @PostMapping("/{admissionId}/update")
    public String update(@PathVariable int admissionId, AdmissionDto admission, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        // 대입시 요강 존재 여부 확인
        if (!isAdmissionExists(admissionId)) {
            return "redirect:/admin/admissions";
        }
        
        // 대입시 요강 수정
        admission.setAdmissionId(admissionId);
        admission.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = admissionService.update(admission);
        if (!result) {
            model.addAttribute("admission", admission);
            redirectAttributes.addFlashAttribute("errorMessage", "대입시 요강 수정에 실패했습니다.");
            return "redirect:/admin/admissions/" + admissionId + "/update";
        }

        redirectAttributes.addFlashAttribute("successMessage", "대입시 요강 수정이 완료되었습니다.");
        return "redirect:/admin/admissions/" + admissionId;
    }

    // 대입시 요강 삭제
    @PostMapping("/{admissionId}/delete")
    public String delete(@PathVariable int admissionId, RedirectAttributes redirectAttributes) {
        // 대입시 요강 존재 여부 확인
        if (!isAdmissionExists(admissionId)) {
            return "redirect:/admin/admissions";
        }

        // 대입시 요강 삭제
        boolean result = admissionService.delete(admissionId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "대입시 요강 삭제가 완료되었습니다.");
            return "redirect:/admin/admissions";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "대입시 요강 삭제에 실패했습니다.");
        return "redirect:/admin/admissions/" + admissionId;
    }
}
