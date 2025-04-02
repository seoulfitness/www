package kr.seoulfitness.admin.earlyAdmissionHistory;

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
@RequestMapping("/admin/earlyAdmissionHistory")
public class EarlyAdmissionHistoryController {

    @Autowired
    private EarlyAdmissionHistoryService earlyAdmissionHistoryService;

    @Autowired
    private AdmissionService admissionService;

    // 수시 한국사 점수 정보 존재 여부 확인
    public boolean isEarlyAdmissionHistoryExists(Map<String, Object> params) {
        return earlyAdmissionHistoryService.read(params) != null;
    }

    // 수시 한국사 점수 정보 등록
    @GetMapping("/create")
    public String create(@RequestParam("admissionId") int admissionId, Model model) {
        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);
        
        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");
        return "admin/earlyAdmissionHistory/create";
    }

    // 수시 한국사 점수 정보 등록 처리
    @PostMapping("/create")
    public String create(
        @RequestParam("admissionId") int admissionId, 
        EarlyAdmissionHistoryDto earlyAdmissionHistory, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        earlyAdmissionHistory.setCreatedBy((String) session.getAttribute("userId"));
        earlyAdmissionHistory.setUpdatedBy((String) session.getAttribute("userId"));
        EarlyAdmissionHistoryDto createdEarlyAdmissionHistory = earlyAdmissionHistoryService.create(earlyAdmissionHistory);
        if (createdEarlyAdmissionHistory != null) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 한국사 정보 등록이 완료되었습니다.");
            return "redirect:/admin/admissions/" + admissionId + "#earlyAdmissionHistory";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 한국사 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmissionHistory", earlyAdmissionHistory);
        return "redirect:/admin/earlyAdmissionHistory/create" + "?admissionId=" + admissionId;
    }

    // 수시 한국사 점수 정보 조회
    @GetMapping("/{earlyAdmissionHistoryId}")
    public String read(@PathVariable int earlyAdmissionHistoryId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionHistoryId", earlyAdmissionHistoryId);
        EarlyAdmissionHistoryDto earlyAdmissionHistory = earlyAdmissionHistoryService.read(params);
        model.addAttribute("earlyAdmissionHistory", earlyAdmissionHistory);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmissionHistory.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/earlyAdmissionHistory/read";
    }

    // 수시 한국사 점수 정보 수정
    @GetMapping("/{earlyAdmissionHistoryId}/update")
    public String update(@PathVariable int earlyAdmissionHistoryId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionHistoryId", earlyAdmissionHistoryId);
        EarlyAdmissionHistoryDto earlyAdmissionHistory = earlyAdmissionHistoryService.read(params);
        model.addAttribute("earlyAdmissionHistory", earlyAdmissionHistory);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmissionHistory.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/earlyAdmissionHistory/update";
    }
    
    // 수시 한국사 점수 정보 수정 처리
    @PostMapping("/{earlyAdmissionHistoryId}/update")
    public String update(
        @PathVariable int earlyAdmissionHistoryId,
        EarlyAdmissionHistoryDto earlyAdmissionHistory,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 수시 한국사 점수 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionHistoryId", earlyAdmissionHistoryId);
        if (!isEarlyAdmissionHistoryExists(params)) {
            return "redirect:/admin/earlyAdmissionHistory";
        }

        earlyAdmissionHistory.setUpdatedBy((String) session.getAttribute("userId"));
        if (earlyAdmissionHistoryService.update(earlyAdmissionHistory)) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 한국사 정보 수정이 완료되었습니다.");
            return "redirect:/admin/admissions/" + earlyAdmissionHistory.getAdmissionId() + "#earlyAdmissionHistory";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 한국사 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmissionHistory", earlyAdmissionHistory);
        return "redirect:/admin/earlyAdmissionHistory/" + earlyAdmissionHistoryId + "/update";
    }
}
