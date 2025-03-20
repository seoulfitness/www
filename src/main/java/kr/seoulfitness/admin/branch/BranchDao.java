package kr.seoulfitness.admin.branch;

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
public class BranchDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(BranchDao.class);

    // 지점 등록
    public int create(BranchDto branch) {
        int result = -1;

        try {
            result = sqlSession.insert("branchMapper.create", branch);
        } catch (DataAccessException e) {
            logger.error("지점 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 지점 목록
    public List<BranchDto> list(int offset, int listCountPerPage, String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("listCountPerPage", listCountPerPage);
        params.put("keyword", keyword);

        List<BranchDto> branches = null;

        try {
            branches = sqlSession.selectList("branchMapper.list", params);
        } catch (DataAccessException e) {
            logger.error("지점 목록 오류 : {}", e.getMessage(), e);
        }

        return branches;
    }

    // 지점 상세보기
    public BranchDto read(int branchId) {
        BranchDto branch = null;
        
        try {
            branch = sqlSession.selectOne("branchMapper.read", branchId);
        } catch (DataAccessException e) {
            logger.error("지점 상세 오류 : {}", e.getMessage(), e);
        }

        return branch;
    }

    // 지점 수정
    public int update(BranchDto branch) {
        int result = -1;

        try {
            result = sqlSession.update("branchMapper.update", branch);
        } catch (DataAccessException e) {
            logger.error("지점 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 지점 삭제
    public int delete(int branchId) {
        int result = -1;

        try {
            result = sqlSession.delete("branchMapper.delete", branchId);
        } catch (DataAccessException e) {
            logger.error("지점 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 지점 수
    public int totalCount(String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);

        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("branchMapper.totalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 지점 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
