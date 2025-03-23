package kr.seoulfitness.admin.csatSubject;

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
@RequestMapping("/admin/csatSubjects")
public class CsatSubjectController {

    @Autowired
    private CsatSubjectService csatSubjectService;

    // 학과 존재 여부 확인
    public boolean isCsatSubjectExists(int csatSubjectId) {
        return csatSubjectService.find(csatSubjectId) != null;
    }

    // 학과 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "수능 교과목");
        model.addAttribute("activePage", "csatSubjects");
        return "admin/csatSubject/create";
    }

    // 학과 등록 처리
    @PostMapping("/create")
    public String createPost(CsatSubjectDto csatSubject, HttpSession session, RedirectAttributes redirectAttributes) {
        csatSubject.setCreatedBy((String) session.getAttribute("userId"));
        csatSubject.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = csatSubjectService.create(csatSubject);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "수능 교과목 등록이 완료되었습니다.");
            return "redirect:/admin/csatSubjects";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수능 교과목 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("csatSubject", csatSubject);
        return "redirect:/admin/csatSubjects/create";
    }

    // 학과 목록
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

        // 학과 목록 조회
        Map<String, Object> result = csatSubjectService.findAll(params);
        model.addAttribute("csatSubjects", result.get("csatSubjects"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "수능 교과목");
        model.addAttribute("activePage", "csatSubjects");

        return "admin/csatSubject/list";
    }

    // 학과 상세
    @GetMapping("/{csatSubjectId}")
    public String view(@PathVariable int csatSubjectId, Model model) {
        // 학과 존재 여부 확인
        if (!isCsatSubjectExists(csatSubjectId)) {
            return "redirect:/admin/csatSubjects";
        }

        CsatSubjectDto csatSubject = csatSubjectService.find(csatSubjectId);
        model.addAttribute("csatSubject", csatSubject);
        model.addAttribute("pageTitle", "수능 교과목");
        model.addAttribute("activePage", "csatSubjects");
        return "admin/csatSubject/read";
    }

    // 학과 수정
    @GetMapping("/{csatSubjectId}/update")
    public String editForm(@PathVariable int csatSubjectId, Model model) {   
        // 학과 존재 여부 확인
        if (!isCsatSubjectExists(csatSubjectId)) {
            return "redirect:/admin/csatSubjects";
        }

        CsatSubjectDto csatSubject = csatSubjectService.find(csatSubjectId);
        model.addAttribute("csatSubject", csatSubject);
        model.addAttribute("pageTitle", "수능 교과목");
        model.addAttribute("activePage", "csatSubjects");
        return "admin/csatSubject/update";
    }

    // 학과 수정 처리
    @PostMapping("/{csatSubjectId}/update")
    public String update(@PathVariable int csatSubjectId, CsatSubjectDto csatSubject, HttpSession session, RedirectAttributes redirectAttributes) {
        // 학과 존재 여부 확인
        if (!isCsatSubjectExists(csatSubjectId)) {
            return "redirect:/admin/csatSubjects";
        }

        csatSubject.setCsatSubjectId(csatSubjectId);
        csatSubject.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = csatSubjectService.update(csatSubject);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "수능 교과목 수정이 완료되었습니다.");
            return "redirect:/admin/csatSubjects/" + csatSubjectId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수능 교과목 수정에 실패했습니다.");
        return "redirect:/admin/csatSubjects/" + csatSubjectId + "/update";
    }

    // 학과 삭제
    @PostMapping("/{csatSubjectId}/delete")
    public String delete(@PathVariable int csatSubjectId, RedirectAttributes redirectAttributes) {
        // 학과 존재 여부 확인
        if (!isCsatSubjectExists(csatSubjectId)) {
            return "redirect:/admin/csatSubjects";
        }

        boolean result = csatSubjectService.delete(csatSubjectId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "수능 교과목 삭제가 완료되었습니다.");
            return "redirect:/admin/csatSubjects";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수능 교과목 삭제에 실패했습니다.");
        return "redirect:/admin/csatSubjects/" + csatSubjectId;
    }
}
