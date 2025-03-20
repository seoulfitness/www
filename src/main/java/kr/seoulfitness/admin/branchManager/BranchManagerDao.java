package kr.seoulfitness.admin.branchManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class BranchManagerDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(BranchManagerDao.class);

    // 지점 관리자 등록
    public int create(BranchManagerDto branchManager) {
        int result = -1;

        try {
            result = sqlSession.insert("branchManagerMapper.create", branchManager);
        } catch (DataAccessException e) {
            logger.error("지점 관리자 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 지점 관리자 목록
    public List<BranchManagerDto> list(int offset, int listCountPerPage, String keyword, int branchId) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("listCountPerPage", listCountPerPage);
        params.put("keyword", keyword);
        params.put("branchId", branchId);

        List<BranchManagerDto> branchManagers = null;

        try {
            branchManagers = sqlSession.selectList("branchManagerMapper.list", params);
        } catch (DataAccessException e) {
            logger.error("지점 관리자 목록 오류 : {}", e.getMessage(), e);
        }

        return branchManagers;
    }

    // 지점 관리자 상세보기
    public BranchManagerDto read(int branchManagerId) {
        BranchManagerDto branchManager = null;
        
        try {
            branchManager = sqlSession.selectOne("branchManagerMapper.read", branchManagerId);
        } catch (DataAccessException e) {
            logger.error("지점 관리자 상세 오류 : {}", e.getMessage(), e);
        }

        return branchManager;
    }

    // 지점 관리자 수정
    public int update(BranchManagerDto branchManager) {
        int result = -1;

        try {
            result = sqlSession.update("branchManagerMapper.update", branchManager);
        } catch (DataAccessException e) {
            logger.error("지점 관리자 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 지점 관리자 삭제
    public int delete(int branchManagerId, int branchId) {
        int result = -1;

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("branchManagerId", branchManagerId);
            params.put("branchId", branchId);
            result = sqlSession.delete("branchManagerMapper.delete", params);
        } catch (DataAccessException e) {
            logger.error("지점 관리자 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 지점 관리자 수
    public int totalCount(String keyword, int branchId) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("branchId", branchId);

        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("branchManagerMapper.totalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 지점 관리자 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
