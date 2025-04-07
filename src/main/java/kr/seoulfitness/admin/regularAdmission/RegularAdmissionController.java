package kr.seoulfitness.admin.regularAdmission;

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

import kr.seoulfitness.admin.admission.AdmissionDto;
import kr.seoulfitness.admin.admission.AdmissionService;
@Controller
@RequestMapping("/admin/regularAdmissions")
public class RegularAdmissionController {

    @Autowired
    private RegularAdmissionService regularAdmissionService;

    @Autowired
    private AdmissionService admissionService;

    // 수시 정보 존재 여부 확인
    public boolean isRegularAdmissionExists(Map<String, Object> params) {
        return regularAdmissionService.read(params) != null;
    }

    // 수시 정보 등록
    @GetMapping("/create")
    public String createGet(@RequestParam("admissionId") int admissionId, Model model) {
        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);

        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");
        return "admin/regularAdmission/create";
    }

    // 수시 정보 등록 처리
    @PostMapping("/create")
    public String createPost(
        @RequestParam("admissionId") int admissionId, 
        RegularAdmissionDto regularAdmission, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        regularAdmission.setCreatedBy((String) session.getAttribute("userId"));
        regularAdmission.setUpdatedBy((String) session.getAttribute("userId"));
        RegularAdmissionDto createdRegularAdmission = regularAdmissionService.create(regularAdmission);
        if (createdRegularAdmission != null) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 정보 등록이 완료되었습니다.");
            return "redirect:/admin/admissions/" + admissionId + "#regularAdmission";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmission", regularAdmission);
        return "redirect:/admin/regularAdmissions/create" + "?admissionId=" + admissionId;
    }

    // 수시 정보 조회
    @GetMapping("/{regularAdmissionId}")
    public String read(@PathVariable int regularAdmissionId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionId", regularAdmissionId);
        RegularAdmissionDto regularAdmission = regularAdmissionService.read(params);
        model.addAttribute("regularAdmission", regularAdmission);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmission.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/regularAdmission/read";
    }

    // 수시 정보 수정
    @GetMapping("/{regularAdmissionId}/update")
    public String updateGet(@PathVariable int regularAdmissionId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionId", regularAdmissionId);
        RegularAdmissionDto regularAdmission = regularAdmissionService.read(params);
        model.addAttribute("regularAdmission", regularAdmission);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmission.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/regularAdmission/edit";
    }
    
    // 수시 정보 수정 처리
    @PostMapping("/{regularAdmissionId}/update")
    public String updatePost(
        @PathVariable int regularAdmissionId,
        RegularAdmissionDto regularAdmission,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 수시 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionId", regularAdmissionId);
        if (!isRegularAdmissionExists(params)) {
            return "redirect:/admin/admissions";
        }

        regularAdmission.setUpdatedBy((String) session.getAttribute("userId"));
        if (regularAdmissionService.update(regularAdmission)) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 정보 수정이 완료되었습니다.");
            return "redirect:/admin/admissions/" + regularAdmission.getAdmissionId() + "#regularAdmission";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmission", regularAdmission);
        return "redirect:/admin/regularAdmissions/" + regularAdmissionId + "/update";
    }
}
