package kr.seoulfitness.admin.branchManager;

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

    // 지점에 지점관리자 등록
    public int insertBranchManager(BranchManagerDto branchManager) {
        int result = -1;

        try {
            result = sqlSession.insert("branchManagerMapper.insertBranchManager", branchManager);
        } catch (DataAccessException e) {
            logger.error("지점 관리자 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 지점 관리자 목록
    public List<BranchManagerDto> getBranchManagers(Map<String, Object> params) {
        List<BranchManagerDto> branchManagers = null;

        try {
            branchManagers = sqlSession.selectList("branchManagerMapper.getBranchManagers", params);
        } catch (DataAccessException e) {
            logger.error("지점 관리자 목록 오류 : {}", e.getMessage(), e);
        }

        return branchManagers;
    }

    // 지점 관리자 상세보기
    public BranchManagerDto getBranchManager(int branchManagerId) {
        BranchManagerDto branchManager = null;
        
        try {
            branchManager = sqlSession.selectOne("branchManagerMapper.getBranchManager", branchManagerId);
        } catch (DataAccessException e) {
            logger.error("지점 관리자 상세 오류 : {}", e.getMessage(), e);
        }

        return branchManager;
    }

    // 지점 관리자 수정
    public int updateBranchManager(BranchManagerDto branchManager) {
        int result = -1;

        try {
            result = sqlSession.update("branchManagerMapper.updateBranchManager", branchManager);
        } catch (DataAccessException e) {
            logger.error("지점 관리자 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 지점 관리자 삭제
    public int deleteBranchManager(Map<String, Object> params) {
        int result = -1;

        try {
            result = sqlSession.delete("branchManagerMapper.deleteBranchManager", params);
        } catch (DataAccessException e) {
            logger.error("지점 관리자 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 지점 관리자 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = 0;
        
        try {
            totalCount = sqlSession.selectOne("branchManagerMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 지점 관리자 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
