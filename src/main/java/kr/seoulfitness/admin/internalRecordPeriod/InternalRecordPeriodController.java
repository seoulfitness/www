package kr.seoulfitness.admin.internalRecordPeriod;

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
@RequestMapping("/admin/internalRecordPeriods")
public class InternalRecordPeriodController {

    @Autowired
    private InternalRecordPeriodService internalRecordPeriodService;

    // 내신 기록 등록 기간 존재 여부 확인
    public boolean isInternalRecordPeriodExists(int internalRecordPeriodId) {
        return internalRecordPeriodService.read(internalRecordPeriodId) != null;
    }

    // 내신 기록 등록 기간 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "내신 기록 등록 기간 관리");
        model.addAttribute("activePage", "internalRecordPeriod");
        return "admin/internalRecordPeriod/create";
    }

    // 내신 기록 등록 기간 등록 처리
    @PostMapping("/create")
    public String createPost(InternalRecordPeriodDto internalRecordPeriod, HttpSession session, RedirectAttributes redirectAttributes) {
        internalRecordPeriod.setCreatedBy((String) session.getAttribute("userId"));
        internalRecordPeriod.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = internalRecordPeriodService.create(internalRecordPeriod);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "내신 기록 등록 기간 등록이 완료되었습니다.");
            return "redirect:/admin/internalRecordPeriods";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "내신 기록 등록 기간 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("internalRecordPeriod", internalRecordPeriod);
        return "redirect:/admin/internalRecordPeriods/create";
    }

    // 내신 기록 등록 기간 목록
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

        // 내신 기록 등록 기간 목록 조회
        Map<String, Object> result = internalRecordPeriodService.list(params);
        model.addAttribute("internalRecordPeriods", result.get("internalRecordPeriods"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "내신 기록 등록 기간 관리");
        model.addAttribute("activePage", "internalRecordPeriod");

        return "admin/internalRecordPeriod/list";
    }

    // 내신 기록 등록 기간 상세
    @GetMapping("/{internalRecordPeriodId}")
    public String read(@PathVariable int internalRecordPeriodId, Model model) {
        // 내신 기록 등록 기간 존재 여부 확인
        if (!isInternalRecordPeriodExists(internalRecordPeriodId)) {
            return "redirect:/admin/internalRecordPeriods";
        }

        InternalRecordPeriodDto internalRecordPeriod = internalRecordPeriodService.read(internalRecordPeriodId);
        model.addAttribute("internalRecordPeriod", internalRecordPeriod);
        model.addAttribute("pageTitle", "내신 기록 등록 기간 관리");
        model.addAttribute("activePage", "internalRecordPeriod");
        return "admin/internalRecordPeriod/read";
    }

    // 내신 기록 등록 기간 수정
    @GetMapping("/{internalRecordPeriodId}/update")
    public String updateGet(@PathVariable int internalRecordPeriodId, Model model) {   
        // 내신 기록 등록 기간 존재 여부 확인
        if (!isInternalRecordPeriodExists(internalRecordPeriodId)) {
            return "redirect:/admin/internalRecordPeriods";
        }

        InternalRecordPeriodDto internalRecordPeriod = internalRecordPeriodService.read(internalRecordPeriodId);
        model.addAttribute("internalRecordPeriod", internalRecordPeriod);
        model.addAttribute("pageTitle", "내신 기록 등록 기간 관리");
        model.addAttribute("activePage", "internalRecordPeriod");
        return "admin/internalRecordPeriod/update";
    }

    // 내신 기록 등록 기간 수정 처리
    @PostMapping("/{internalRecordPeriodId}/update")
    public String updatePost(@PathVariable int internalRecordPeriodId, InternalRecordPeriodDto internalRecordPeriod, HttpSession session, RedirectAttributes redirectAttributes) {
        // 내신 기록 등록 기간 존재 여부 확인
        if (!isInternalRecordPeriodExists(internalRecordPeriodId)) {
            return "redirect:/admin/internalRecordPeriods";
        }

        internalRecordPeriod.setInternalRecordPeriodId(internalRecordPeriodId);
        internalRecordPeriod.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = internalRecordPeriodService.update(internalRecordPeriod);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "내신 기록 등록 기간 수정이 완료되었습니다.");
            return "redirect:/admin/internalRecordPeriods/" + internalRecordPeriodId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "내신 기록 등록 기간 수정에 실패했습니다.");
        return "redirect:/admin/internalRecordPeriods/" + internalRecordPeriodId + "/update";
    }

    // 내신 기록 등록 기간 삭제
    @PostMapping("/{internalRecordPeriodId}/delete")
    public String delete(@PathVariable int internalRecordPeriodId, RedirectAttributes redirectAttributes) {
        // 내신 기록 등록 기간 존재 여부 확인
        if (!isInternalRecordPeriodExists(internalRecordPeriodId)) {
            return "redirect:/admin/internalRecordPeriods";
        }

        boolean result = internalRecordPeriodService.delete(internalRecordPeriodId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "내신 기록 등록 기간 삭제가 완료되었습니다.");
            return "redirect:/admin/internalRecordPeriods";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "내신 기록 등록 기간 삭제에 실패했습니다.");
        return "redirect:/admin/internalRecordPeriods/" + internalRecordPeriodId;
    }
}
