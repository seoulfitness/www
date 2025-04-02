package kr.seoulfitness.admin.regularAdmissionHistory;

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
@RequestMapping("/admin/regularAdmissionHistory")
public class RegularAdmissionHistoryController {

    @Autowired
    private RegularAdmissionHistoryService regularAdmissionHistoryService;

    @Autowired
    private AdmissionService admissionService;

    // 정시 한국사 점수 정보 존재 여부 확인
    public boolean isRegularAdmissionHistoryExists(Map<String, Object> params) {
        return regularAdmissionHistoryService.read(params) != null;
    }

    // 정시 한국사 점수 정보 등록
    @GetMapping("/create")
    public String create(@RequestParam("admissionId") int admissionId, Model model) {
        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);
        
        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");
        return "admin/regularAdmissionHistory/create";
    }

    // 정시 한국사 점수 정보 등록 처리
    @PostMapping("/create")
    public String create(
        @RequestParam("admissionId") int admissionId, 
        RegularAdmissionHistoryDto regularAdmissionHistory, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        regularAdmissionHistory.setCreatedBy((String) session.getAttribute("userId"));
        regularAdmissionHistory.setUpdatedBy((String) session.getAttribute("userId"));
        RegularAdmissionHistoryDto createdRegularAdmissionHistory = regularAdmissionHistoryService.create(regularAdmissionHistory);
        if (createdRegularAdmissionHistory != null) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 한국사 점수 정보 등록이 완료되었습니다.");
            return "redirect:/admin/admissions/" + admissionId + "#regularAdmissionHistory";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 한국사 점수 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmissionHistory", regularAdmissionHistory);
        return "redirect:/admin/regularAdmissionHistory/create" + "?admissionId=" + admissionId;
    }

    // 정시 한국사 점수 정보 조회
    @GetMapping("/{regularAdmissionHistoryId}")
    public String read(@PathVariable int regularAdmissionHistoryId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionHistoryId", regularAdmissionHistoryId);
        RegularAdmissionHistoryDto regularAdmissionHistory = regularAdmissionHistoryService.read(params);
        model.addAttribute("regularAdmissionHistory", regularAdmissionHistory);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmissionHistory.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/regularAdmissionHistory/read";
    }

    // 정시 한국사 점수 정보 수정
    @GetMapping("/{regularAdmissionHistoryId}/update")
    public String update(@PathVariable int regularAdmissionHistoryId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionHistoryId", regularAdmissionHistoryId);
        RegularAdmissionHistoryDto regularAdmissionHistory = regularAdmissionHistoryService.read(params);
        model.addAttribute("regularAdmissionHistory", regularAdmissionHistory);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmissionHistory.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/regularAdmissionHistory/update";
    }
    
    // 정시 한국사 점수 정보 수정 처리
    @PostMapping("/{regularAdmissionHistoryId}/update")
    public String update(
        @PathVariable int regularAdmissionHistoryId,
        RegularAdmissionHistoryDto regularAdmissionHistory,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 정시 한국사 점수 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionHistoryId", regularAdmissionHistoryId);
        if (!isRegularAdmissionHistoryExists(params)) {
            return "redirect:/admin/regularAdmissionHistory";
        }

        regularAdmissionHistory.setUpdatedBy((String) session.getAttribute("userId"));
        if (regularAdmissionHistoryService.update(regularAdmissionHistory)) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 한국사 점수 정보 수정이 완료되었습니다.");
            return "redirect:/admin/admissions/" + regularAdmissionHistory.getAdmissionId() + "#regularAdmissionHistory";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 한국사 점수 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmissionHistory", regularAdmissionHistory);
        return "redirect:/admin/regularAdmissionHistory/" + regularAdmissionHistoryId + "/update";
    }
}
