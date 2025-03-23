package kr.seoulfitness.admin.district;

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

import kr.seoulfitness.admin.province.ProvinceService;

@Controller
@RequestMapping("/admin/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private ProvinceService provinceService;

    // 구/군 존재 여부 확인
    public boolean isDistrictExists(int districtId) {
        return districtService.find(districtId) != null;
    }

    // 구/군 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        // 시/도 목록
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", 1);
        params.put("listCountPerPage", 100);
        params.put("pageCountPerPage", 100);
        params.put("keyword", null);

        Map<String, Object> provinceResult = provinceService.findAll(params);
        model.addAttribute("provinces", provinceResult.get("provinces"));

        model.addAttribute("pageTitle", "구/군 관리");
        model.addAttribute("activePage", "districts");
        return "admin/district/create";
    }

    // 구/군 등록 처리
    @PostMapping("/create")
    public String createPost(DistrictDto district, HttpSession session, RedirectAttributes redirectAttributes) {
        // 구/군 등록
        district.setCreatedBy((String) session.getAttribute("userId"));
        district.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = districtService.create(district);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "구/군 등록이 완료되었습니다.");
            return "redirect:/admin/districts";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "구/군 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("district", district);
        return "redirect:/admin/districts/create";
    }

    // 구/군 목록
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

        Map<String, Object> result = districtService.findAll(params);
        model.addAttribute("districts", result.get("districts"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", "구/군 관리");
        model.addAttribute("activePage", "districts");

        return "admin/district/list";
    }

    // 구/군 상세
    @GetMapping("/{districtId}")
    public String view(@PathVariable int districtId, Model model) {
        // 구/군 존재 여부 확인
        if (!isDistrictExists(districtId)) {
            return "redirect:/admin/districts";
        }

        // 구/군 상세
        DistrictDto district = districtService.find(districtId);
        model.addAttribute("district", district);
        model.addAttribute("pageTitle", "구/군 관리");
        model.addAttribute("activePage", "districts");
        return "admin/district/read";
    }

    // 구/군 수정
    @GetMapping("/{districtId}/update")
    public String editForm(@PathVariable int districtId, Model model) {   
        // 구/군 존재 여부 확인
        if (!isDistrictExists(districtId)) {
            return "redirect:/admin/districts";
        }

        // 시/도 목록
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", 1);
        params.put("listCountPerPage", 100);
        params.put("pageCountPerPage", 100);
        params.put("keyword", null);

        Map<String, Object> provinceResult = provinceService.findAll(params);
        model.addAttribute("provinces", provinceResult.get("provinces"));

        // 구/군 수정
        DistrictDto district = districtService.find(districtId);
        model.addAttribute("district", district);
        model.addAttribute("pageTitle", "구/군 관리");
        model.addAttribute("activePage", "districts");
        return "admin/district/update";
    }

    // 구/군 수정 처리
    @PostMapping("/{districtId}/update")
    public String update(@PathVariable int districtId, DistrictDto district, HttpSession session, RedirectAttributes redirectAttributes) {
        // 구/군 존재 여부 확인
        if (!isDistrictExists(districtId)) {
            return "redirect:/admin/districts";
        }

        // 구/군 수정
        district.setDistrictId(districtId);
        district.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = districtService.update(district);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "구/군 수정이 완료되었습니다.");
            return "redirect:/admin/districts/" + districtId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "구/군 수정에 실패했습니다.");
        return "redirect:/admin/districts/" + districtId + "/update";
    }

    // 구/군 삭제
    @PostMapping("/{districtId}/delete")
    public String delete(@PathVariable int districtId, RedirectAttributes redirectAttributes) {
        // 구/군 존재 여부 확인
        if (!isDistrictExists(districtId)) {
            return "redirect:/admin/districts";
        }

        // 구/군 삭제
        boolean result = districtService.delete(districtId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "구/군 삭제가 완료되었습니다.");
            return "redirect:/admin/districts";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "구/군 삭제에 실패했습니다.");
        return "redirect:/admin/districts/" + districtId;
    }
}
