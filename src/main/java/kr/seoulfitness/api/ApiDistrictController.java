package kr.seoulfitness.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.seoulfitness.admin.district.DistrictDto;
import kr.seoulfitness.admin.district.DistrictService;

@Controller
@RequestMapping("/api/districts")
public class ApiDistrictController {

    @Autowired
    private DistrictService districtService;

    // 구/군 목록
    @GetMapping("")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public List<DistrictDto> getDistricts(@RequestParam(required = false) Integer provinceId) {
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", 1);
        params.put("listCountPerPage", 100);
        params.put("pageCountPerPage", 10);
        params.put("keyword", null);
        params.put("provinceId", provinceId);
    
        Map<String, Object> result = districtService.findAll(params);
        return (List<DistrictDto>) result.get("districts");
    }
}
