package kr.seoulfitness.admin.earlyAdmissionInternal;

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
@RequestMapping("/admin/earlyAdmissionInternal")
public class EarlyAdmissionInternalController {

    @Autowired
    private EarlyAdmissionInternalService earlyAdmissionInternalService;

    @Autowired
    private AdmissionService admissionService;

    // 수시 내신 점수 정보 존재 여부 확인
    public boolean isEarlyAdmissionInternalExists(Map<String, Object> params) {
        return earlyAdmissionInternalService.read(params) != null;
    }

    // 수시 내신 점수 정보 등록
    @GetMapping("/create")
    public String create(@RequestParam("admissionId") int admissionId, Model model) {
        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);
        
        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");
        return "admin/earlyAdmissionInternal/create";
    }

    // 수시 내신 점수 정보 등록 처리
    @PostMapping("/create")
    public String create(
        @RequestParam("admissionId") int admissionId, 
        EarlyAdmissionInternalDto earlyAdmissionInternal, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        earlyAdmissionInternal.setCreatedBy((String) session.getAttribute("userId"));
        earlyAdmissionInternal.setUpdatedBy((String) session.getAttribute("userId"));
        EarlyAdmissionInternalDto createdEarlyAdmissionInternal = earlyAdmissionInternalService.create(earlyAdmissionInternal);
        if (createdEarlyAdmissionInternal != null) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 내신 점수 정보 등록이 완료되었습니다.");
            return "redirect:/admin/earlyAdmissionInternal/" + createdEarlyAdmissionInternal.getEarlyAdmissionInternalId();
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 내신 점수 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmissionInternal", earlyAdmissionInternal);
        return "redirect:/admin/earlyAdmissionInternal/create" + "?admissionId=" + admissionId;
    }

    // 수시 내신 점수 정보 조회
    @GetMapping("/{earlyAdmissionInternalId}")
    public String read(@PathVariable int earlyAdmissionInternalId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionInternalId", earlyAdmissionInternalId);
        EarlyAdmissionInternalDto earlyAdmissionInternal = earlyAdmissionInternalService.read(params);
        model.addAttribute("earlyAdmissionInternal", earlyAdmissionInternal);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmissionInternal.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/earlyAdmissionInternal/read";
    }

    // 수시 내신 점수 정보 수정
    @GetMapping("/{earlyAdmissionInternalId}/update")
    public String update(@PathVariable int earlyAdmissionInternalId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionInternalId", earlyAdmissionInternalId);
        EarlyAdmissionInternalDto earlyAdmissionInternal = earlyAdmissionInternalService.read(params);
        model.addAttribute("earlyAdmissionInternal", earlyAdmissionInternal);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmissionInternal.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/earlyAdmissionInternal/update";
    }
    
    // 수시 내신 점수 정보 수정 처리
    @PostMapping("/{earlyAdmissionInternalId}/update")
    public String update(
        @PathVariable int earlyAdmissionInternalId,
        EarlyAdmissionInternalDto earlyAdmissionInternal,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 수시 내신 점수 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionInternalId", earlyAdmissionInternalId);
        if (!isEarlyAdmissionInternalExists(params)) {
            return "redirect:/admin/earlyAdmissionInternal";
        }

        earlyAdmissionInternal.setUpdatedBy((String) session.getAttribute("userId"));
        if (earlyAdmissionInternalService.update(earlyAdmissionInternal)) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 내신 점수 정보 수정이 완료되었습니다.");
            return "redirect:/admin/earlyAdmissionInternal/" + earlyAdmissionInternalId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 내신 점수 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmissionInternal", earlyAdmissionInternal);
        return "redirect:/admin/earlyAdmissionInternal/" + earlyAdmissionInternalId + "/update";
    }
}
