package kr.seoulfitness.admin.district;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class DistrictService {
    
    @Autowired
    private DistrictDao districtDao;

    // 구/군 등록
    public boolean create(DistrictDto district) {        
        return districtDao.insertDistrict(district) > 0;
    }

    // 구/군 목록
    public Map<String, Object> list(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = districtDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<DistrictDto> districts = districtDao.getDistricts(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("districts", districts);
        result.put("pagination", pagination);

        return result;
    }

    // 구/군 상세보기
    public DistrictDto read(int districtId) {
        return districtDao.getDistrict(districtId);
    }

    // 구/군 수정
    public boolean update(DistrictDto district) {        
        return districtDao.updateDistrict(district) > 0;
    }

    // 구/군 삭제
    public boolean delete(int districtId) {
        return districtDao.deleteDistrict(districtId) > 0;
    }
}
