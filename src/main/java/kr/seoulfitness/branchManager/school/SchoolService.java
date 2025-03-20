package kr.seoulfitness.branchManager.school;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class SchoolService {
    
    @Autowired
    private SchoolDao schoolDao;

    // 학교 등록
    public boolean create(SchoolDto school) {
        return schoolDao.create(school) > 0;
    }

    // 학교 목록
    public Map<String, Object> list(int currentPage, int listCountPerPage, int pageCountPerPage, String keyword) {
        // 전체 게시글 수 조회
        int totalCount = schoolDao.totalCount(keyword);

        // 페이지네이션 정보 생성
        Pagination pagination = new Pagination(currentPage, listCountPerPage, pageCountPerPage, totalCount);

        // 페이징된 게시글 목록 조회
        List<SchoolDto> schools = schoolDao.list(pagination.offset(), listCountPerPage, keyword);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("schools", schools);
        result.put("pagination", pagination);
        result.put("keyword", keyword);

        return result;
    }

    // 학교 상세보기
    public SchoolDto read(int schoolId) {
        return schoolDao.read(schoolId);
    }

    // 학교 수정
    public boolean update(SchoolDto school) {
        return schoolDao.update(school) > 0;
    }

    // 학교 삭제
    public boolean delete(int schoolId) {
        return schoolDao.delete(schoolId) > 0;
    }
}
