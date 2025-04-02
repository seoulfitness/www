package kr.seoulfitness.admin.earlyAdmissionPhysical;

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
@RequestMapping("/admin/earlyAdmissionPhysical")
public class EarlyAdmissionPhysicalController {

    @Autowired
    private EarlyAdmissionPhysicalService earlyAdmissionPhysicalService;

    @Autowired
    private AdmissionService admissionService;

    // 수시 실기 점수 정보 존재 여부 확인
    public boolean isEarlyAdmissionPhysicalExists(Map<String, Object> params) {
        return earlyAdmissionPhysicalService.read(params) != null;
    }

    // 수시 실기 점수 정보 등록
    @GetMapping("/create")
    public String create(@RequestParam("admissionId") int admissionId, Model model) {
        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);
        
        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");
        return "admin/earlyAdmissionPhysical/create";
    }

    // 수시 실기 점수 정보 등록 처리
    @PostMapping("/create")
    public String create(
        @RequestParam("admissionId") int admissionId, 
        EarlyAdmissionPhysicalDto earlyAdmissionPhysical, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        earlyAdmissionPhysical.setCreatedBy((String) session.getAttribute("userId"));
        earlyAdmissionPhysical.setUpdatedBy((String) session.getAttribute("userId"));
        EarlyAdmissionPhysicalDto createdEarlyAdmissionPhysical = earlyAdmissionPhysicalService.create(earlyAdmissionPhysical);
        if (createdEarlyAdmissionPhysical != null) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 실기 점수 정보 등록이 완료되었습니다.");
            return "redirect:/admin/admissions/" + admissionId + "#earlyAdmissionPhysical";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 실기 점수 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmissionPhysical", earlyAdmissionPhysical);
        return "redirect:/admin/earlyAdmissionPhysical/create" + "?admissionId=" + admissionId;
    }

    // 수시 실기 점수 정보 조회
    @GetMapping("/{earlyAdmissionPhysicalId}")
    public String read(@PathVariable int earlyAdmissionPhysicalId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionPhysicalId", earlyAdmissionPhysicalId);
        EarlyAdmissionPhysicalDto earlyAdmissionPhysical = earlyAdmissionPhysicalService.read(params);
        model.addAttribute("earlyAdmissionPhysical", earlyAdmissionPhysical);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmissionPhysical.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/earlyAdmissionPhysical/read";
    }

    // 수시 실기 점수 정보 수정
    @GetMapping("/{earlyAdmissionPhysicalId}/update")
    public String update(@PathVariable int earlyAdmissionPhysicalId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionPhysicalId", earlyAdmissionPhysicalId);
        EarlyAdmissionPhysicalDto earlyAdmissionPhysical = earlyAdmissionPhysicalService.read(params);
        model.addAttribute("earlyAdmissionPhysical", earlyAdmissionPhysical);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmissionPhysical.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/earlyAdmissionPhysical/update";
    }
    
    // 수시 실기 점수 정보 수정 처리
    @PostMapping("/{earlyAdmissionPhysicalId}/update")
    public String update(
        @PathVariable int earlyAdmissionPhysicalId,
        EarlyAdmissionPhysicalDto earlyAdmissionPhysical,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 수시 실기 점수 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionPhysicalId", earlyAdmissionPhysicalId);
        if (!isEarlyAdmissionPhysicalExists(params)) {
            return "redirect:/admin/earlyAdmissionPhysical";
        }

        earlyAdmissionPhysical.setUpdatedBy((String) session.getAttribute("userId"));
        if (earlyAdmissionPhysicalService.update(earlyAdmissionPhysical)) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 실기 점수 정보 수정이 완료되었습니다.");
            return "redirect:/admin/admissions/" + earlyAdmissionPhysical.getAdmissionId() + "#earlyAdmissionPhysical";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 실기 점수 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmissionPhysical", earlyAdmissionPhysical);
        return "redirect:/admin/earlyAdmissionPhysical/" + earlyAdmissionPhysicalId + "/update";
    }
}
