package kr.seoulfitness.admin.department;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class DepartmentService {
    
    @Autowired
    private DepartmentDao departmentDao;

    // 학과 등록
    public boolean create(DepartmentDto department) {
        return departmentDao.insertDepartment(department) > 0;
    }

    // 학과 목록
    public Map<String, Object> findAll(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = departmentDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<DepartmentDto> departments = departmentDao.getDepartments(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("departments", departments);
        result.put("pagination", pagination);

        return result;
    }

    // 학과 상세보기
    public DepartmentDto find(int departmentId) {
        return departmentDao.getDepartment(departmentId);
    }

    // 학과 수정
    public boolean update(DepartmentDto department) {
        return departmentDao.updateDepartment(department) > 0;
    }

    // 학과 삭제
    public boolean delete(int departmentId) {
        return departmentDao.deleteDepartment(departmentId) > 0;
    }
}
