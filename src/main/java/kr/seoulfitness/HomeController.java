package kr.seoulfitness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.seoulfitness.admin.admission.AdmissionDto;
import kr.seoulfitness.admin.admission.AdmissionService;
import kr.seoulfitness.admin.earlyAdmission.EarlyAdmissionDto;
import kr.seoulfitness.admin.earlyAdmission.EarlyAdmissionService;
import kr.seoulfitness.admin.regularAdmission.RegularAdmissionDto;
import kr.seoulfitness.admin.regularAdmission.RegularAdmissionService;

/**
 * HomeController: 클라이언트 요청을 처리하는 스프링 MVC 컨트롤러
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private EarlyAdmissionService earlyAdmissionService;

    @Autowired
    private RegularAdmissionService regularAdmissionService;

    @SuppressWarnings("unchecked")
    @GetMapping("")
    public String home(
        @RequestParam(value = "page", defaultValue = "1") int currentPage, 
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String searchAdmission,
        @RequestParam(required = false) String searchScope,
        Model model
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("listCountPerPage", 1000);
        params.put("pageCountPerPage", 1000);
        params.put("keyword", keyword);
        params.put("searchAdmission", searchAdmission);
        params.put("searchScope", searchScope);
        
        // 입시 요강 목록
        Map<String, Object> result = admissionService.list(params);
        List<AdmissionDto> admissions = (List<AdmissionDto>) result.get("admissions");
        for (AdmissionDto admission : admissions) {
            // 수시 모집 여부 확인
            if (admission.getEarlyAdmission().equals("Y")) {
                // 수시 모집 상세보기
                Map<String, Object> earlyAdmissionParams = new HashMap<>();
                earlyAdmissionParams.put("admissionId", admission.getAdmissionId());
                EarlyAdmissionDto earlyAdmission = earlyAdmissionService.read(earlyAdmissionParams);
                admission.setEarlyAdmissionDto(earlyAdmission);
            }

            // 정시 모집 여부 확인
            if (admission.getRegularAdmission().equals("Y")) {
                // 정시 모집 상세보기
                Map<String, Object> regularAdmissionParams = new HashMap<>();
                regularAdmissionParams.put("admissionId", admission.getAdmissionId());
                RegularAdmissionDto regularAdmission = regularAdmissionService.read(regularAdmissionParams);
                admission.setRegularAdmissionDto(regularAdmission);
            }
        }
        
        // 페이지 정보
        model.addAttribute("admissions", result.get("admissions"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchScope", searchScope);
        model.addAttribute("searchAdmission", searchAdmission);
        model.addAttribute("activePage", "home");

        return "home";
    }
}
