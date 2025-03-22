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
        return provinceDao.create(province) > 0;
    }

    // 시/도 목록
    public Map<String, Object> list(int currentPage, int listCountPerPage, int pageCountPerPage, String keyword) {
        // 전체 게시글 수 조회
        int totalCount = provinceDao.totalCount(keyword);

        // 페이지네이션 정보 생성
        Pagination pagination = new Pagination(currentPage, listCountPerPage, pageCountPerPage, totalCount);

        // 페이징된 게시글 목록 조회
        List<ProvinceDto> provinces = provinceDao.list(pagination.offset(), listCountPerPage, keyword);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("provinces", provinces);
        result.put("pagination", pagination);
        result.put("keyword", keyword);

        return result;
    }

    // 시/도 상세보기
    public ProvinceDto read(int provinceId) {
        return provinceDao.read(provinceId);
    }

    // 시/도 수정
    public boolean update(ProvinceDto province) {        
        return provinceDao.update(province) > 0;
    }

    // 시/도 삭제
    public boolean delete(int provinceId) {
        return provinceDao.delete(provinceId) > 0;
    }
}
