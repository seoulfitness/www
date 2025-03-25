package kr.seoulfitness.admin.earlyAdmission;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/early-admissions")
public class EarlyAdmissionController {

    @Autowired
    private EarlyAdmissionService earlyAdmissionService;

    // 입시(수시) 요강 정보 조회
    @GetMapping("/api/{admissionId}")
    public ResponseEntity<Map<String, Object>> earlyAdmissionGet(@PathVariable("admissionId") int admissionId) {
        // 입시(수시) 요강 정보 조회
        Map<String, Object> earlyAdmissionParams = new HashMap<>();
        earlyAdmissionParams.put("admissionId", admissionId);
        EarlyAdmissionDto earlyAdmission = earlyAdmissionService.find(earlyAdmissionParams);

        // JSON 형식으로 데이터 반환
        Map<String, Object> response = new HashMap<>();
        response.put("earlyAdmission", earlyAdmission);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 입시(수시) 요강 정보 수정
    @PostMapping("/api/{admissionId}/update")
    public ResponseEntity<Map<String, Object>> earlyAdmissionUpdate(
        @PathVariable("admissionId") int admissionId, 
        @RequestBody EarlyAdmissionDto earlyAdmission, 
        HttpSession session
    ) {
        // 입시(수시) 요강 정보 수정
        earlyAdmission.setCreatedBy((String) session.getAttribute("userId"));
        earlyAdmission.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = earlyAdmissionService.update(earlyAdmission);

        // JSON 형식으로 데이터 반환
        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
