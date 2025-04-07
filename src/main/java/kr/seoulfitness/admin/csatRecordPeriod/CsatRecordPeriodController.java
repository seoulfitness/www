package kr.seoulfitness.admin.csatRecordPeriod;

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

@Controller
@RequestMapping("/admin/csatRecordPeriods")
public class CsatRecordPeriodController {

    @Autowired
    private CsatRecordPeriodService csatRecordPeriodService;

    // 수능 기록 등록 기간 존재 여부 확인
    public boolean isCsatRecordPeriodExists(int csatRecordPeriodId) {
        return csatRecordPeriodService.read(csatRecordPeriodId) != null;
    }

    // 수능 기록 등록 기간 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "수능 기록 등록 기간 관리");
        model.addAttribute("activePage", "csatRecordPeriod");
        return "admin/csatRecordPeriod/create";
    }

    // 수능 기록 등록 기간 등록 처리
    @PostMapping("/create")
    public String createPost(CsatRecordPeriodDto csatRecordPeriod, HttpSession session, RedirectAttributes redirectAttributes) {
        csatRecordPeriod.setCreatedBy((String) session.getAttribute("userId"));
        csatRecordPeriod.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = csatRecordPeriodService.create(csatRecordPeriod);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "수능 기록 등록 기간 등록이 완료되었습니다.");
            return "redirect:/admin/csatRecordPeriods";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수능 기록 등록 기간 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("csatRecordPeriod", csatRecordPeriod);
        return "redirect:/admin/csatRecordPeriods/create";
    }

    // 수능 기록 등록 기간 목록
    @GetMapping("")
    public String list(
        @RequestParam(value = "page", defaultValue = "1") int currentPage, 
        @RequestParam(required = false) String keyword,
        Model model
    ) {
        // 파라미터 생성
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("listCountPerPage", 10);
        params.put("pageCountPerPage", 5);
        params.put("keyword", keyword);

        // 수능 기록 등록 기간 목록 조회
        Map<String, Object> result = csatRecordPeriodService.list(params);
        model.addAttribute("csatRecordPeriods", result.get("csatRecordPeriods"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "수능 기록 등록 기간 관리");
        model.addAttribute("activePage", "csatRecordPeriod");

        return "admin/csatRecordPeriod/list";
    }

    // 수능 기록 등록 기간 상세
    @GetMapping("/{csatRecordPeriodId}")
    public String read(@PathVariable int csatRecordPeriodId, Model model) {
        // 수능 기록 등록 기간 존재 여부 확인
        if (!isCsatRecordPeriodExists(csatRecordPeriodId)) {
            return "redirect:/admin/csatRecordPeriods";
        }

        CsatRecordPeriodDto csatRecordPeriod = csatRecordPeriodService.read(csatRecordPeriodId);
        model.addAttribute("csatRecordPeriod", csatRecordPeriod);
        model.addAttribute("pageTitle", "수능 기록 등록 기간 관리");
        model.addAttribute("activePage", "csatRecordPeriod");
        return "admin/csatRecordPeriod/read";
    }

    // 수능 기록 등록 기간 수정
    @GetMapping("/{csatRecordPeriodId}/update")
    public String updateGet(@PathVariable int csatRecordPeriodId, Model model) {   
        // 수능 기록 등록 기간 존재 여부 확인
        if (!isCsatRecordPeriodExists(csatRecordPeriodId)) {
            return "redirect:/admin/csatRecordPeriods";
        }

        CsatRecordPeriodDto csatRecordPeriod = csatRecordPeriodService.read(csatRecordPeriodId);
        model.addAttribute("csatRecordPeriod", csatRecordPeriod);
        model.addAttribute("pageTitle", "수능 기록 등록 기간 관리");
        model.addAttribute("activePage", "csatRecordPeriod");
        return "admin/csatRecordPeriod/update";
    }

    // 수능 기록 등록 기간 수정 처리
    @PostMapping("/{csatRecordPeriodId}/update")
    public String updatePost(@PathVariable int csatRecordPeriodId, CsatRecordPeriodDto csatRecordPeriod, HttpSession session, RedirectAttributes redirectAttributes) {
        // 수능 기록 등록 기간 존재 여부 확인
        if (!isCsatRecordPeriodExists(csatRecordPeriodId)) {
            return "redirect:/admin/csatRecordPeriods";
        }

        csatRecordPeriod.setCsatRecordPeriodId(csatRecordPeriodId);
        csatRecordPeriod.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = csatRecordPeriodService.update(csatRecordPeriod);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "수능 기록 등록 기간 수정이 완료되었습니다.");
            return "redirect:/admin/csatRecordPeriods/" + csatRecordPeriodId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수능 기록 등록 기간 수정에 실패했습니다.");
        return "redirect:/admin/csatRecordPeriods/" + csatRecordPeriodId + "/update";
    }

    // 수능 기록 등록 기간 삭제
    @PostMapping("/{csatRecordPeriodId}/delete")
    public String delete(@PathVariable int csatRecordPeriodId, RedirectAttributes redirectAttributes) {
        // 수능 기록 등록 기간 존재 여부 확인
        if (!isCsatRecordPeriodExists(csatRecordPeriodId)) {
            return "redirect:/admin/csatRecordPeriods";
        }

        boolean result = csatRecordPeriodService.delete(csatRecordPeriodId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "수능 기록 등록 기간 삭제가 완료되었습니다.");
            return "redirect:/admin/csatRecordPeriods";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수능 기록 등록 기간 삭제에 실패했습니다.");
        return "redirect:/admin/csatRecordPeriods/" + csatRecordPeriodId;
    }
}
