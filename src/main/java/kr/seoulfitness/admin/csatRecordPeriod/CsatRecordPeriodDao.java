package kr.seoulfitness.admin.csatRecordPeriod;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class CsatRecordPeriodDao {

    @Autowired
    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(CsatRecordPeriodDao.class);

    // 수능 기록 등록 기간 등록
    public int insertCsatRecordPeriod(CsatRecordPeriodDto csatRecordPeriodDto) {
        int result = -1;

        try {
            result = sqlSession.insert("csatRecordPeriod.insertCsatRecordPeriod", csatRecordPeriodDto);
        } catch (DataAccessException e) {
            logger.error("수능 기록 등록 기간 등록 오류 : {}", e.getMessage(), e);
        }
        return result;
    }

    // 수능 기록 등록 기간 목록 조회
    public List<CsatRecordPeriodDto> getCsatRecordPeriods(Map<String, Object> params) {
        List<CsatRecordPeriodDto> csatRecordPeriods = null;

        try {
            csatRecordPeriods = sqlSession.selectList("csatRecordPeriod.getCsatRecordPeriods", params);
        } catch (DataAccessException e) {
            logger.error("수능 기록 등록 기간 목록 조회 오류 : {}", e.getMessage(), e);
        }
        return csatRecordPeriods;
    }

    // 수능 기록 등록 기간 상세 조회
    public CsatRecordPeriodDto getCsatRecordPeriod(int csatRecordPeriodId) {
        CsatRecordPeriodDto csatRecordPeriodDto = null;

        try {
            csatRecordPeriodDto = sqlSession.selectOne("csatRecordPeriod.getCsatRecordPeriod", csatRecordPeriodId);
        } catch (DataAccessException e) {
            logger.error("수능 기록 등록 기간 상세 조회 오류 : {}", e.getMessage(), e);
        }
        return csatRecordPeriodDto;
    }

    // 수능 기록 등록 기간 수정
    public int updateCsatRecordPeriod(CsatRecordPeriodDto csatRecordPeriodDto) {
        int result = -1;

        try {
            result = sqlSession.update("csatRecordPeriod.updateCsatRecordPeriod", csatRecordPeriodDto);
        } catch (DataAccessException e) {
            logger.error("수능 기록 등록 기간 수정 오류 : {}", e.getMessage(), e);
        }
        return result;
    }   

    // 수능 기록 등록 기간 삭제
    public int deleteCsatRecordPeriod(int csatRecordPeriodId) {
        int result = -1;

        try {
            result = sqlSession.delete("csatRecordPeriod.deleteCsatRecordPeriod", csatRecordPeriodId);
        } catch (DataAccessException e) {
            logger.error("수능 기록 등록 기간 삭제 오류 : {}", e.getMessage(), e);
        }
        return result;
    }

    // 수능 기록 등록 기간 총 개수 조회
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = 0;

        try {
            totalCount = sqlSession.selectOne("csatRecordPeriod.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("수능 기록 등록 기간 총 개수 조회 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
