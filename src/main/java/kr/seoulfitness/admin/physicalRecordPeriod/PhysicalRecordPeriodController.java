package kr.seoulfitness.admin.physicalRecordPeriod;

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
@RequestMapping("/admin/physicalRecordPeriods")
public class PhysicalRecordPeriodController {

    @Autowired
    private PhysicalRecordPeriodService physicalRecordPeriodService;

    // 실기 기록 등록 기간 존재 여부 확인
    public boolean isPhysicalRecordPeriodExists(int physicalRecordPeriodId) {
        return physicalRecordPeriodService.read(physicalRecordPeriodId) != null;
    }

    // 실기 기록 등록 기간 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "실기 기록 등록 기간 관리");
        model.addAttribute("activePage", "physicalRecordPeriod");
        return "admin/physicalRecordPeriod/create";
    }

    // 실기 기록 등록 기간 등록 처리
    @PostMapping("/create")
    public String createPost(PhysicalRecordPeriodDto physicalRecordPeriod, HttpSession session, RedirectAttributes redirectAttributes) {
        physicalRecordPeriod.setCreatedBy((String) session.getAttribute("userId"));
        physicalRecordPeriod.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = physicalRecordPeriodService.create(physicalRecordPeriod);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "실기 기록 등록 기간 등록이 완료되었습니다.");
            return "redirect:/admin/physicalRecordPeriods";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "실기 기록 등록 기간 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("physicalRecordPeriod", physicalRecordPeriod);
        return "redirect:/admin/physicalRecordPeriods/create";
    }

    // 실기 기록 등록 기간 목록
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

        // 실기 기록 등록 기간 목록 조회
        Map<String, Object> result = physicalRecordPeriodService.list(params);
        model.addAttribute("physicalRecordPeriods", result.get("physicalRecordPeriods"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "실기 기록 등록 기간 관리");
        model.addAttribute("activePage", "physicalRecordPeriod");

        return "admin/physicalRecordPeriod/list";
    }

    // 실기 기록 등록 기간 상세
    @GetMapping("/{physicalRecordPeriodId}")
    public String read(@PathVariable int physicalRecordPeriodId, Model model) {
        // 실기 기록 등록 기간 존재 여부 확인
        if (!isPhysicalRecordPeriodExists(physicalRecordPeriodId)) {
            return "redirect:/admin/physicalRecordPeriods";
        }

        PhysicalRecordPeriodDto physicalRecordPeriod = physicalRecordPeriodService.read(physicalRecordPeriodId);
        model.addAttribute("physicalRecordPeriod", physicalRecordPeriod);
        model.addAttribute("pageTitle", "실기 기록 등록 기간 관리");
        model.addAttribute("activePage", "physicalRecordPeriod");
        return "admin/physicalRecordPeriod/read";
    }

    // 실기 기록 등록 기간 수정
    @GetMapping("/{physicalRecordPeriodId}/update")
    public String updateGet(@PathVariable int physicalRecordPeriodId, Model model) {   
        // 실기 기록 등록 기간 존재 여부 확인
        if (!isPhysicalRecordPeriodExists(physicalRecordPeriodId)) {
            return "redirect:/admin/physicalRecordPeriods";
        }

        PhysicalRecordPeriodDto physicalRecordPeriod = physicalRecordPeriodService.read(physicalRecordPeriodId);
        model.addAttribute("physicalRecordPeriod", physicalRecordPeriod);
        model.addAttribute("pageTitle", "실기 기록 등록 기간 관리");
        model.addAttribute("activePage", "physicalRecordPeriod");
        return "admin/physicalRecordPeriod/update";
    }

    // 실기 기록 등록 기간 수정 처리
    @PostMapping("/{physicalRecordPeriodId}/update")
    public String updatePost(@PathVariable int physicalRecordPeriodId, PhysicalRecordPeriodDto physicalRecordPeriod, HttpSession session, RedirectAttributes redirectAttributes) {
        // 실기 기록 등록 기간 존재 여부 확인
        if (!isPhysicalRecordPeriodExists(physicalRecordPeriodId)) {
            return "redirect:/admin/physicalRecordPeriods";
        }

        physicalRecordPeriod.setPhysicalRecordPeriodId(physicalRecordPeriodId);
        physicalRecordPeriod.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = physicalRecordPeriodService.update(physicalRecordPeriod);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "실기 기록 등록 기간 수정이 완료되었습니다.");
            return "redirect:/admin/physicalRecordPeriods/" + physicalRecordPeriodId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "실기 기록 등록 기간 수정에 실패했습니다.");
        return "redirect:/admin/physicalRecordPeriods/" + physicalRecordPeriodId + "/update";
    }

    // 실기 기록 등록 기간 삭제
    @PostMapping("/{physicalRecordPeriodId}/delete")
    public String delete(@PathVariable int physicalRecordPeriodId, RedirectAttributes redirectAttributes) {
        // 실기 기록 등록 기간 존재 여부 확인
        if (!isPhysicalRecordPeriodExists(physicalRecordPeriodId)) {
            return "redirect:/admin/physicalRecordPeriods";
        }

        boolean result = physicalRecordPeriodService.delete(physicalRecordPeriodId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "실기 기록 등록 기간 삭제가 완료되었습니다.");
            return "redirect:/admin/physicalRecordPeriods";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "실기 기록 등록 기간 삭제에 실패했습니다.");
        return "redirect:/admin/physicalRecordPeriods/" + physicalRecordPeriodId;
    }
}
