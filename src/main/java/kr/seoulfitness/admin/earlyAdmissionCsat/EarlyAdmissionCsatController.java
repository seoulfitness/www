package kr.seoulfitness.admin.earlyAdmissionCsat;

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
@RequestMapping("/admin/earlyAdmissionCsat")
public class EarlyAdmissionCsatController {

    @Autowired
    private EarlyAdmissionCsatService earlyAdmissionCsatService;

    @Autowired
    private AdmissionService admissionService;

    // 수시 정보 존재 여부 확인
    public boolean isEarlyAdmissionCsatExists(Map<String, Object> params) {
        return earlyAdmissionCsatService.read(params) != null;
    }

    // 수시 정보 등록
    @GetMapping("/create")
    public String createGet(@RequestParam("admissionId") int admissionId, Model model) {
        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);
        
        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");
        return "admin/earlyAdmissionCsat/create";
    }

    // 수시 정보 등록 처리
    @PostMapping("/create")
    public String createPost(
        @RequestParam("admissionId") int admissionId, 
        EarlyAdmissionCsatDto earlyAdmissionCsat, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        earlyAdmissionCsat.setCreatedBy((String) session.getAttribute("userId"));
        earlyAdmissionCsat.setUpdatedBy((String) session.getAttribute("userId"));
        EarlyAdmissionCsatDto createdEarlyAdmissionCsat = earlyAdmissionCsatService.create(earlyAdmissionCsat);
        if (createdEarlyAdmissionCsat != null) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 수능 정보 등록이 완료되었습니다.");
            return "redirect:/admin/admissions/" + admissionId + "#earlyAdmissionCsat";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 수능 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmissionCsat", earlyAdmissionCsat);
        return "redirect:/admin/earlyAdmissionCsat/create" + "?admissionId=" + admissionId;
    }

    // 수시 정보 조회
    @GetMapping("/{earlyAdmissionCsatId}")
    public String read(@PathVariable int earlyAdmissionCsatId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionCsatId", earlyAdmissionCsatId);
        EarlyAdmissionCsatDto earlyAdmissionCsat = earlyAdmissionCsatService.read(params);
        model.addAttribute("earlyAdmissionCsat", earlyAdmissionCsat);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmissionCsat.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/earlyAdmissionCsat/read";
    }

    // 수시 정보 수정
    @GetMapping("/{earlyAdmissionCsatId}/update")
    public String updateGet(@PathVariable int earlyAdmissionCsatId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionCsatId", earlyAdmissionCsatId);
        EarlyAdmissionCsatDto earlyAdmissionCsat = earlyAdmissionCsatService.read(params);
        model.addAttribute("earlyAdmissionCsat", earlyAdmissionCsat);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmissionCsat.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/earlyAdmissionCsat/update";
    }
    
    // 수시 정보 수정 처리
    @PostMapping("/{earlyAdmissionCsatId}/update")
    public String updatePost(
        @PathVariable int earlyAdmissionCsatId,
        EarlyAdmissionCsatDto earlyAdmissionCsat,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 수시 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionCsatId", earlyAdmissionCsatId);
        if (!isEarlyAdmissionCsatExists(params)) {
            return "redirect:/admin/admissions";
        }

        earlyAdmissionCsat.setUpdatedBy((String) session.getAttribute("userId"));
        if (earlyAdmissionCsatService.update(earlyAdmissionCsat)) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 수능 정보 수정이 완료되었습니다.");
            return "redirect:/admin/admissions/" + earlyAdmissionCsat.getAdmissionId() + "#earlyAdmissionCsat";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 수능 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmissionCsat", earlyAdmissionCsat);
        return "redirect:/admin/earlyAdmissionCsat/" + earlyAdmissionCsatId + "/update";
    }
}
