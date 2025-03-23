package kr.seoulfitness.admin.province;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class ProvinceService {
    
    @Autowired
    private ProvinceDao provinceDao;

    // 시/도 등록
    public boolean create(ProvinceDto province) {        
        return provinceDao.insertProvince(province) > 0;
    }

    // 시/도 목록
    public Map<String, Object> findAll(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = provinceDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<ProvinceDto> provinces = provinceDao.getProvinces(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("provinces", provinces);
        result.put("pagination", pagination);

        return result;
    }

    // 시/도 상세보기
    public ProvinceDto find(int provinceId) {
        return provinceDao.getProvince(provinceId);
    }

    // 시/도 수정
    public boolean update(ProvinceDto province) {        
        return provinceDao.updateProvince(province) > 0;
    }

    // 시/도 삭제
    public boolean delete(int provinceId) {
        return provinceDao.deleteProvince(provinceId) > 0;
    }
}
