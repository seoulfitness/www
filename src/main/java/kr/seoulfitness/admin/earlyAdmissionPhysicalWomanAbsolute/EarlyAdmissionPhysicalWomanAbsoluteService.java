package kr.seoulfitness.admin.earlyAdmissionPhysicalWomanAbsolute;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarlyAdmissionPhysicalWomanAbsoluteService {
    
    @Autowired
    private EarlyAdmissionPhysicalWomanAbsoluteDao earlyAdmissionPhysicalWomanAbsoluteDao;

    // 수시 모집 실기 여자 절대평가 점수 등록
    public EarlyAdmissionPhysicalWomanAbsoluteDto create(EarlyAdmissionPhysicalWomanAbsoluteDto earlyAdmissionPhysicalWomanAbsolute) {        
        return earlyAdmissionPhysicalWomanAbsoluteDao.insertEarlyAdmissionPhysicalWomanAbsolute(earlyAdmissionPhysicalWomanAbsolute);
    }

    // 수시 모집 실기 여자 절대평가 점수 목록
    public List<EarlyAdmissionPhysicalWomanAbsoluteDto> getEarlyAdmissionPhysicalWomanAbsoluteList(Map<String, Object> params) {
        return earlyAdmissionPhysicalWomanAbsoluteDao.getEarlyAdmissionPhysicalWomanAbsoluteList(params);
    }

    // 수시 모집 실기 여자 절대평가 점수 정보 상세
    public EarlyAdmissionPhysicalWomanAbsoluteDto read(Map<String, Object> params) {
        return earlyAdmissionPhysicalWomanAbsoluteDao.getEarlyAdmissionPhysicalWomanAbsolute(params);
    }

    // 수시 모집 실기 여자 절대평가 점수 수정
    public boolean update(EarlyAdmissionPhysicalWomanAbsoluteDto earlyAdmissionPhysicalWomanAbsolute) {        
        return earlyAdmissionPhysicalWomanAbsoluteDao.updateEarlyAdmissionPhysicalWomanAbsolute(earlyAdmissionPhysicalWomanAbsolute) > 0;
    }

    // 수시 모집 실기 여자 절대평가 점수 삭제
    public boolean delete(EarlyAdmissionPhysicalWomanAbsoluteDto earlyAdmissionPhysicalWomanAbsolute) {
        return earlyAdmissionPhysicalWomanAbsoluteDao.deleteEarlyAdmissionPhysicalWomanAbsolute(earlyAdmissionPhysicalWomanAbsolute) > 0;
    }
    
}
