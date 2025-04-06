package kr.seoulfitness.admin.earlyAdmissionPhysicalWomanAbsolute;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/earlyAdmissionPhysicalWomanAbsolute")
public class EarlyAdmissionPhysicalWomanAbsoluteController {

    @Autowired
    private EarlyAdmissionPhysicalWomanAbsoluteService earlyAdmissionPhysicalWomanAbsoluteService;

    // 수시 실기 여자 절대평가 점수 정보 등록 처리
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> create(
        EarlyAdmissionPhysicalWomanAbsoluteDto earlyAdmissionPhysicalWomanAbsolute, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        Map<String, Object> result = new HashMap<>();

        earlyAdmissionPhysicalWomanAbsolute.setCreatedBy((String) session.getAttribute("userId"));
        earlyAdmissionPhysicalWomanAbsolute.setUpdatedBy((String) session.getAttribute("userId"));
        EarlyAdmissionPhysicalWomanAbsoluteDto createdEarlyAdmissionPhysicalWomanAbsolute = earlyAdmissionPhysicalWomanAbsoluteService.create(earlyAdmissionPhysicalWomanAbsolute);
        if (createdEarlyAdmissionPhysicalWomanAbsolute != null) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 실기 여자 절대평가 점수 정보 등록이 완료되었습니다.");
            result.put("success", true);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 실기 여자 절대평가 점수 정보 등록에 실패했습니다.");
            result.put("success", false);
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    // 수시 실기 여자 절대평가 점수 정보 조회
    @GetMapping("/{earlyAdmissionPhysicalAbsoluteId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> read(@PathVariable int earlyAdmissionPhysicalAbsoluteId) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionPhysicalAbsoluteId", earlyAdmissionPhysicalAbsoluteId);
        EarlyAdmissionPhysicalWomanAbsoluteDto earlyAdmissionPhysicalWomanAbsolute = earlyAdmissionPhysicalWomanAbsoluteService.read(params);

        // 수시 실기 여자 절대평가 점수 정보
        Map<String, Object> result = new HashMap<>();
        result.put("earlyAdmissionPhysicalAbsolute", earlyAdmissionPhysicalWomanAbsolute);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    // 수시 실기 여자 절대평가 점수 정보 수정 처리
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> update(
        EarlyAdmissionPhysicalWomanAbsoluteDto earlyAdmissionPhysicalWomanAbsolute,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        Map<String, Object> result = new HashMap<>();

        earlyAdmissionPhysicalWomanAbsolute.setUpdatedBy((String) session.getAttribute("userId"));
        if (earlyAdmissionPhysicalWomanAbsoluteService.update(earlyAdmissionPhysicalWomanAbsolute)) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 실기 여자 절대평가 점수 정보 수정이 완료되었습니다.");
            result.put("success", true);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 실기 여자 절대평가 점수 정보 수정에 실패했습니다.");
            result.put("success", false);
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
}
