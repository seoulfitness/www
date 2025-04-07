package kr.seoulfitness.admin.earlyAdmission;

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
@RequestMapping("/admin/earlyAdmissions")
public class EarlyAdmissionController {

    @Autowired
    private EarlyAdmissionService earlyAdmissionService;

    @Autowired
    private AdmissionService admissionService;

    // 수시 정보 존재 여부 확인
    public boolean isEarlyAdmissionExists(Map<String, Object> params) {
        return earlyAdmissionService.read(params) != null;
    }

    // 수시 정보 등록
    @GetMapping("/create")
    public String createGet(@RequestParam("admissionId") int admissionId, Model model) {
        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);

        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");
        return "admin/earlyAdmission/create";
    }

    // 수시 정보 등록 처리
    @PostMapping("/create")
    public String createPost(
        @RequestParam("admissionId") int admissionId, 
        EarlyAdmissionDto earlyAdmission, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        earlyAdmission.setCreatedBy((String) session.getAttribute("userId"));
        earlyAdmission.setUpdatedBy((String) session.getAttribute("userId"));
        EarlyAdmissionDto createdEarlyAdmission = earlyAdmissionService.create(earlyAdmission);
        if (createdEarlyAdmission != null) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 정보 등록이 완료되었습니다.");
            return "redirect:/admin/admissions/" + admissionId + "#earlyAdmission";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmission", earlyAdmission);
        return "redirect:/admin/earlyAdmissions/create" + "?admissionId=" + admissionId;
    }

    // 수시 정보 조회
    @GetMapping("/{earlyAdmissionId}")
    public String read(@PathVariable int earlyAdmissionId, Model model) {
        // 수시 정보
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionId", earlyAdmissionId);
        EarlyAdmissionDto earlyAdmission = earlyAdmissionService.read(params);
        model.addAttribute("earlyAdmission", earlyAdmission);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmission.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/earlyAdmission/read";
    }

    // 수시 정보 수정
    @GetMapping("/{earlyAdmissionId}/update")
    public String updatePost(@PathVariable int earlyAdmissionId, Model model) {
        // 수시 정보
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionId", earlyAdmissionId);
        EarlyAdmissionDto earlyAdmission = earlyAdmissionService.read(params);
        model.addAttribute("earlyAdmission", earlyAdmission);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmission.getAdmissionId());
        model.addAttribute("admission", admission);
        return "admin/earlyAdmission/edit";
    }
    
    // 수시 정보 수정 처리
    @PostMapping("/{earlyAdmissionId}/update")
    public String updatePost(
        @PathVariable int earlyAdmissionId,
        EarlyAdmissionDto earlyAdmission,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 수시 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionId", earlyAdmissionId);
        if (!isEarlyAdmissionExists(params)) {
            return "redirect:/admin/admissions";
        }

        earlyAdmission.setUpdatedBy((String) session.getAttribute("userId"));
        if (earlyAdmissionService.update(earlyAdmission)) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 정보 수정이 완료되었습니다.");
            return "redirect:/admin/admissions/" + earlyAdmission.getAdmissionId() + "#earlyAdmission";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmission", earlyAdmission);
        return "redirect:/admin/earlyAdmissions/" + earlyAdmissionId + "/update";
    }
}
