package kr.seoulfitness.admin.branch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class BranchDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(BranchDao.class);

    // 지점 등록
    public int insertBranch(BranchDto branch) {
        int result = -1;

        try {
            result = sqlSession.insert("branchMapper.insertBranch", branch);
        } catch (DataAccessException e) {
            logger.error("지점 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 지점 목록
    public List<BranchDto> getBranches(Map<String, Object> params) {
        List<BranchDto> branches = null;

        try {
            branches = sqlSession.selectList("branchMapper.getBranches", params);
        } catch (DataAccessException e) {
            logger.error("지점 목록 오류 : {}", e.getMessage(), e);
        }

        return branches;
    }

    // 지점 상세보기
    public BranchDto getBranch(int branchId) {
        BranchDto branch = null;
        
        try {
            branch = sqlSession.selectOne("branchMapper.getBranch", branchId);
        } catch (DataAccessException e) {
            logger.error("지점 상세 오류 : {}", e.getMessage(), e);
        }

        return branch;
    }

    // 지점 수정
    public int updateBranch(BranchDto branch) {
        int result = -1;

        try {
            result = sqlSession.update("branchMapper.updateBranch", branch);
        } catch (DataAccessException e) {
            logger.error("지점 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 지점 삭제
    public int deleteBranch(int branchId) {
        int result = -1;

        try {
            result = sqlSession.delete("branchMapper.deleteBranch", branchId);
        } catch (DataAccessException e) {
            logger.error("지점 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 지점 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("branchMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 지점 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
