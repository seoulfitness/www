package kr.seoulfitness.admin.physicalRecordPeriod;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class PhysicalRecordPeriodDao {

    @Autowired
    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(PhysicalRecordPeriodDao.class);

    // 실기 기록 등록 기간 등록
    public int insertPhysicalRecordPeriod(PhysicalRecordPeriodDto physicalRecordPeriodDto) {
        int result = -1;

        try {
            result = sqlSession.insert("physicalRecordPeriod.insertPhysicalRecordPeriod", physicalRecordPeriodDto);
        } catch (DataAccessException e) {
            logger.error("실기 기록 등록 기간 등록 오류 : {}", e.getMessage(), e);
        }
        return result;
    }

    // 실기 기록 등록 기간 목록 조회
    public List<PhysicalRecordPeriodDto> getPhysicalRecordPeriods(Map<String, Object> params) {
        List<PhysicalRecordPeriodDto> physicalRecordPeriods = null;

        try {
            physicalRecordPeriods = sqlSession.selectList("physicalRecordPeriod.getPhysicalRecordPeriods", params);
        } catch (DataAccessException e) {
            logger.error("실기 기록 등록 기간 목록 조회 오류 : {}", e.getMessage(), e);
        }
        return physicalRecordPeriods;
    }

    // 실기 기록 등록 기간 상세 조회
    public PhysicalRecordPeriodDto getPhysicalRecordPeriod(int physicalRecordPeriodId) {
        PhysicalRecordPeriodDto physicalRecordPeriodDto = null;

        try {
            physicalRecordPeriodDto = sqlSession.selectOne("physicalRecordPeriod.getPhysicalRecordPeriod", physicalRecordPeriodId);
        } catch (DataAccessException e) {
            logger.error("실기 기록 등록 기간 상세 조회 오류 : {}", e.getMessage(), e);
        }
        return physicalRecordPeriodDto;
    }

    // 실기 기록 등록 기간 수정
    public int updatePhysicalRecordPeriod(PhysicalRecordPeriodDto physicalRecordPeriodDto) {
        int result = -1;

        try {
            result = sqlSession.update("physicalRecordPeriod.updatePhysicalRecordPeriod", physicalRecordPeriodDto);
        } catch (DataAccessException e) {
            logger.error("실기 기록 등록 기간 수정 오류 : {}", e.getMessage(), e);
        }
        return result;
    }   

    // 실기 기록 등록 기간 삭제
    public int deletePhysicalRecordPeriod(int physicalRecordPeriodId) {
        int result = -1;

        try {
            result = sqlSession.delete("physicalRecordPeriod.deletePhysicalRecordPeriod", physicalRecordPeriodId);
        } catch (DataAccessException e) {
            logger.error("실기 기록 등록 기간 삭제 오류 : {}", e.getMessage(), e);
        }
        return result;
    }

    // 실기 기록 등록 기간 총 개수 조회
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = 0;

        try {
            totalCount = sqlSession.selectOne("physicalRecordPeriod.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("실기 기록 등록 기간 총 개수 조회 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
