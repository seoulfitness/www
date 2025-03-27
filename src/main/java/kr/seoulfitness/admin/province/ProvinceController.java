package kr.seoulfitness.admin.province;

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
@RequestMapping("/admin/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    // 시/도 존재 여부 확인
    public boolean isProvinceExists(int provinceId) {
        return provinceService.read(provinceId) != null;
    }

    // 시/도 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "시/도 관리");
        model.addAttribute("activePage", "provinces");
        return "admin/province/create";
    }

    // 시/도 등록 처리
    @PostMapping("/create")
    public String createPost(ProvinceDto province, HttpSession session, RedirectAttributes redirectAttributes) {
        // 시/도 등록
        province.setCreatedBy((String) session.getAttribute("userId"));
        province.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = provinceService.create(province);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "시/도 등록이 완료되었습니다.");
            return "redirect:/admin/provinces";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "시/도 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("province", province);
        return "redirect:/admin/provinces/create";
    }

    // 시/도 목록
    @GetMapping("")
    public String list(
        @RequestParam(value = "page", defaultValue = "1") int currentPage, 
        @RequestParam(required = false) String keyword,
        Model model
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("listCountPerPage", 10); // 한 페이지에서 불러올 게시글 수
        params.put("pageCountPerPage", 5); // 한 페이지에서 보여질 페이지 수
        params.put("keyword", keyword);

        Map<String, Object> result = provinceService.list(params);
        model.addAttribute("provinces", result.get("provinces"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "시/도 관리");
        model.addAttribute("activePage", "provinces");

        return "admin/province/list";
    }

    // 시/도 상세
    @GetMapping("/{provinceId}")
    public String read(@PathVariable int provinceId, Model model) {
        // 시/도 존재 여부 확인
        if (!isProvinceExists(provinceId)) {
            return "redirect:/admin/provinces";
        }

        // 시/도 상세
        ProvinceDto province = provinceService.read(provinceId);
        model.addAttribute("province", province);
        model.addAttribute("pageTitle", "시/도 관리");
        model.addAttribute("activePage", "provinces");
        return "admin/province/read";
    }

    // 시/도 수정
    @GetMapping("/{provinceId}/update")
    public String editForm(@PathVariable int provinceId, Model model) {   
        // 시/도 존재 여부 확인
        if (!isProvinceExists(provinceId)) {
            return "redirect:/admin/provinces";
        }

        // 시/도 수정
        ProvinceDto province = provinceService.read(provinceId);
        model.addAttribute("province", province);
        model.addAttribute("pageTitle", "시/도 관리");
        model.addAttribute("activePage", "provinces");
        return "admin/province/update";
    }

    // 시/도 수정 처리
    @PostMapping("/{provinceId}/update")
    public String update(@PathVariable int provinceId, ProvinceDto province, HttpSession session, RedirectAttributes redirectAttributes) {
        // 시/도 존재 여부 확인
        if (!isProvinceExists(provinceId)) {
            return "redirect:/admin/provinces";
        }

        // 시/도 수정
        province.setProvinceId(provinceId);
        province.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = provinceService.update(province);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "시/도 수정이 완료되었습니다.");
            return "redirect:/admin/provinces/" + provinceId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "시/도 수정에 실패했습니다.");
        return "redirect:/admin/provinces/" + provinceId + "/update";
    }

    // 시/도 삭제
    @PostMapping("/{provinceId}/delete")
    public String delete(@PathVariable int provinceId, RedirectAttributes redirectAttributes) {
        // 시/도 존재 여부 확인
        if (!isProvinceExists(provinceId)) {
            return "redirect:/admin/provinces";
        }

        // 시/도 삭제
        boolean result = provinceService.delete(provinceId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "시/도 삭제가 완료되었습니다.");
            return "redirect:/admin/provinces";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "시/도 삭제에 실패했습니다.");
        return "redirect:/admin/provinces/" + provinceId;
    }
}
