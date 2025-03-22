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
        return districtDao.create(district) > 0;
    }

    // 구/군 목록
    public Map<String, Object> list(int currentPage, int listCountPerPage, int pageCountPerPage, String keyword) {
        // 전체 게시글 수 조회
        int totalCount = districtDao.totalCount(keyword);

        // 페이지네이션 정보 생성
        Pagination pagination = new Pagination(currentPage, listCountPerPage, pageCountPerPage, totalCount);

        // 페이징된 게시글 목록 조회
        List<DistrictDto> districts = districtDao.list(pagination.offset(), listCountPerPage, keyword);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("districts", districts);
        result.put("pagination", pagination);
        result.put("keyword", keyword);

        return result;
    }

    // 구/군 상세보기
    public DistrictDto read(int districtId) {
        return districtDao.read(districtId);
    }

    // 구/군 수정
    public boolean update(DistrictDto district) {        
        return districtDao.update(district) > 0;
    }

    // 구/군 삭제
    public boolean delete(int districtId) {
        return districtDao.delete(districtId) > 0;
    }
}
