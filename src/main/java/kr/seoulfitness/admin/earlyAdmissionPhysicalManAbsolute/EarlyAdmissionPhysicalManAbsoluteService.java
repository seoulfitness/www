package kr.seoulfitness.admin.earlyAdmissionPhysicalManAbsolute;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarlyAdmissionPhysicalManAbsoluteService {
    
    @Autowired
    private EarlyAdmissionPhysicalManAbsoluteDao earlyAdmissionPhysicalManAbsoluteDao;

    // 수시 모집 실기 남자 절대평가 점수 등록
    public EarlyAdmissionPhysicalManAbsoluteDto create(EarlyAdmissionPhysicalManAbsoluteDto earlyAdmissionPhysicalManAbsolute) {        
        return earlyAdmissionPhysicalManAbsoluteDao.insertEarlyAdmissionPhysicalManAbsolute(earlyAdmissionPhysicalManAbsolute);
    }

    // 수시 모집 실기 남자 절대평가 점수 목록
    public List<EarlyAdmissionPhysicalManAbsoluteDto> getEarlyAdmissionPhysicalManAbsoluteList(Map<String, Object> params) {
        return earlyAdmissionPhysicalManAbsoluteDao.getEarlyAdmissionPhysicalManAbsoluteList(params);
    }

    // 수시 모집 실기 남자 절대평가 점수 정보 상세
    public EarlyAdmissionPhysicalManAbsoluteDto read(Map<String, Object> params) {
        return earlyAdmissionPhysicalManAbsoluteDao.getEarlyAdmissionPhysicalManAbsolute(params);
    }

    // 수시 모집 실기 남자 절대평가 점수 수정
    public boolean update(EarlyAdmissionPhysicalManAbsoluteDto earlyAdmissionPhysicalManAbsolute) {        
        return earlyAdmissionPhysicalManAbsoluteDao.updateEarlyAdmissionPhysicalManAbsolute(earlyAdmissionPhysicalManAbsolute) > 0;
    }

    // 수시 모집 실기 남자 절대평가 점수 삭제
    public boolean delete(EarlyAdmissionPhysicalManAbsoluteDto earlyAdmissionPhysicalManAbsolute) {
        return earlyAdmissionPhysicalManAbsoluteDao.deleteEarlyAdmissionPhysicalManAbsolute(earlyAdmissionPhysicalManAbsolute) > 0;
    }
    
}
