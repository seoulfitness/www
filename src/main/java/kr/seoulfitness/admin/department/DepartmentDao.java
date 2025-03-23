package kr.seoulfitness.admin.department;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(DepartmentDao.class);

    // 학과 등록
    public int insertDepartment(DepartmentDto department) {
        int result = -1;

        try {
            result = sqlSession.insert("departmentMapper.insertDepartment", department);
        } catch (DataAccessException e) {
            logger.error("학과 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 학과 목록
    public List<DepartmentDto> getDepartments(Map<String, Object> params) {
        List<DepartmentDto> departments = null;

        try {
            departments = sqlSession.selectList("departmentMapper.getDepartments", params);
        } catch (DataAccessException e) {
            logger.error("학과 목록 오류 : {}", e.getMessage(), e);
        }

        return departments;
    }

    // 학과 상세보기
    public DepartmentDto getDepartment(int departmentId) {
        DepartmentDto department = null;
        
        try {
            department = sqlSession.selectOne("departmentMapper.getDepartment", departmentId);
        } catch (DataAccessException e) {
            logger.error("학과 상세 오류 : {}", e.getMessage(), e);
        }

        return department;
    }

    // 학과 수정
    public int updateDepartment(DepartmentDto department) {
        int result = -1;

        try {
            result = sqlSession.update("departmentMapper.updateDepartment", department);
        } catch (DataAccessException e) {
            logger.error("학과 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 학과 삭제
    public int deleteDepartment(int departmentId) {
        int result = -1;

        try {
            result = sqlSession.delete("departmentMapper.deleteDepartment", departmentId);
        } catch (DataAccessException e) {
            logger.error("학과 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 학과 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("departmentMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 학과 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
