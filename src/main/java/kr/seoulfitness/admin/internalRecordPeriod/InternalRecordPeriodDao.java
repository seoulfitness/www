package kr.seoulfitness.admin.internalRecordPeriod;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class InternalRecordPeriodDao {

    @Autowired
    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(InternalRecordPeriodDao.class);

    // 내신 기록 등록 기간 등록
    public int insertInternalRecordPeriod(InternalRecordPeriodDto internalRecordPeriodDto) {
        int result = -1;

        try {
            result = sqlSession.insert("internalRecordPeriod.insertInternalRecordPeriod", internalRecordPeriodDto);
        } catch (DataAccessException e) {
            logger.error("내신 기록 등록 기간 등록 오류 : {}", e.getMessage(), e);
        }
        return result;
    }

    // 내신 기록 등록 기간 목록 조회
    public List<InternalRecordPeriodDto> getInternalRecordPeriods(Map<String, Object> params) {
        List<InternalRecordPeriodDto> internalRecordPeriods = null;

        try {
            internalRecordPeriods = sqlSession.selectList("internalRecordPeriod.getInternalRecordPeriods", params);
        } catch (DataAccessException e) {
            logger.error("내신 기록 등록 기간 목록 조회 오류 : {}", e.getMessage(), e);
        }
        return internalRecordPeriods;
    }

    // 내신 기록 등록 기간 상세 조회
    public InternalRecordPeriodDto getInternalRecordPeriod(int internalRecordPeriodId) {
        InternalRecordPeriodDto internalRecordPeriodDto = null;

        try {
            internalRecordPeriodDto = sqlSession.selectOne("internalRecordPeriod.getInternalRecordPeriod", internalRecordPeriodId);
        } catch (DataAccessException e) {
            logger.error("내신 기록 등록 기간 상세 조회 오류 : {}", e.getMessage(), e);
        }
        return internalRecordPeriodDto;
    }

    // 내신 기록 등록 기간 수정
    public int updateInternalRecordPeriod(InternalRecordPeriodDto internalRecordPeriodDto) {
        int result = -1;

        try {
            result = sqlSession.update("internalRecordPeriod.updateInternalRecordPeriod", internalRecordPeriodDto);
        } catch (DataAccessException e) {
            logger.error("내신 기록 등록 기간 수정 오류 : {}", e.getMessage(), e);
        }
        return result;
    }   

    // 내신 기록 등록 기간 삭제
    public int deleteInternalRecordPeriod(int internalRecordPeriodId) {
        int result = -1;

        try {
            result = sqlSession.delete("internalRecordPeriod.deleteInternalRecordPeriod", internalRecordPeriodId);
        } catch (DataAccessException e) {
            logger.error("내신 기록 등록 기간 삭제 오류 : {}", e.getMessage(), e);
        }
        return result;
    }

    // 내신 기록 등록 기간 총 개수 조회
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = 0;

        try {
            totalCount = sqlSession.selectOne("internalRecordPeriod.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("내신 기록 등록 기간 총 개수 조회 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
