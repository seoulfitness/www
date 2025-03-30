package kr.seoulfitness.admin.regularAdmissionInternal;

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
@RequestMapping("/admin/regularAdmissionInternal")
public class RegularAdmissionInternalController {

    @Autowired
    private RegularAdmissionInternalService regularAdmissionInternalService;

    @Autowired
    private AdmissionService admissionService;

    // 정시 내신 점수 정보 존재 여부 확인
    public boolean isRegularAdmissionInternalExists(Map<String, Object> params) {
        return regularAdmissionInternalService.read(params) != null;
    }

    // 정시 내신 점수 정보 등록
    @GetMapping("/create")
    public String create(@RequestParam("admissionId") int admissionId, Model model) {
        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);
        
        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");
        return "admin/regularAdmissionInternal/create";
    }

    // 정시 내신 점수 정보 등록 처리
    @PostMapping("/create")
    public String create(
        @RequestParam("admissionId") int admissionId, 
        RegularAdmissionInternalDto regularAdmissionInternal, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        regularAdmissionInternal.setCreatedBy((String) session.getAttribute("userId"));
        regularAdmissionInternal.setUpdatedBy((String) session.getAttribute("userId"));
        RegularAdmissionInternalDto createdRegularAdmissionInternal = regularAdmissionInternalService.create(regularAdmissionInternal);
        if (createdRegularAdmissionInternal != null) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 내신 점수 정보 등록이 완료되었습니다.");
            return "redirect:/admin/regularAdmissionInternal/" + createdRegularAdmissionInternal.getRegularAdmissionInternalId();
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 내신 점수 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmissionInternal", regularAdmissionInternal);
        return "redirect:/admin/regularAdmissionInternal/create" + "?admissionId=" + admissionId;
    }

    // 정시 내신 점수 정보 조회
    @GetMapping("/{regularAdmissionInternalId}")
    public String read(@PathVariable int regularAdmissionInternalId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionInternalId", regularAdmissionInternalId);
        RegularAdmissionInternalDto regularAdmissionInternal = regularAdmissionInternalService.read(params);
        model.addAttribute("regularAdmissionInternal", regularAdmissionInternal);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmissionInternal.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/regularAdmissionInternal/read";
    }

    // 정시 내신 점수 정보 수정
    @GetMapping("/{regularAdmissionInternalId}/update")
    public String update(@PathVariable int regularAdmissionInternalId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionInternalId", regularAdmissionInternalId);
        RegularAdmissionInternalDto regularAdmissionInternal = regularAdmissionInternalService.read(params);
        model.addAttribute("regularAdmissionInternal", regularAdmissionInternal);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmissionInternal.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/regularAdmissionInternal/update";
    }
    
    // 정시 내신 점수 정보 수정 처리
    @PostMapping("/{regularAdmissionInternalId}/update")
    public String update(
        @PathVariable int regularAdmissionInternalId,
        RegularAdmissionInternalDto regularAdmissionInternal,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 정시 내신 점수 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionInternalId", regularAdmissionInternalId);
        if (!isRegularAdmissionInternalExists(params)) {
            return "redirect:/admin/regularAdmissionInternal";
        }

        regularAdmissionInternal.setUpdatedBy((String) session.getAttribute("userId"));
        if (regularAdmissionInternalService.update(regularAdmissionInternal)) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 내신 점수 정보 수정이 완료되었습니다.");
            return "redirect:/admin/regularAdmissionInternal/" + regularAdmissionInternalId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 내신 점수 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmissionInternal", regularAdmissionInternal);
        return "redirect:/admin/regularAdmissionInternal/" + regularAdmissionInternalId + "/update";
    }
}
