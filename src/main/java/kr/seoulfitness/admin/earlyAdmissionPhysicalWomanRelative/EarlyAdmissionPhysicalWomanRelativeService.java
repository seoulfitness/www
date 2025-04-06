package kr.seoulfitness.admin.earlyAdmissionPhysicalWomanRelative;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarlyAdmissionPhysicalWomanRelativeService {
    
    @Autowired
    private EarlyAdmissionPhysicalWomanRelativeDao earlyAdmissionPhysicalWomanRelativeDao;

    // 수시 모집 실기 여자 상대평가 점수 등록
    public EarlyAdmissionPhysicalWomanRelativeDto create(EarlyAdmissionPhysicalWomanRelativeDto earlyAdmissionPhysicalWomanRelative) {        
        return earlyAdmissionPhysicalWomanRelativeDao.insertEarlyAdmissionPhysicalWomanRelative(earlyAdmissionPhysicalWomanRelative);
    }

    // 수시 모집 실기 여자 상대평가 점수 목록
    public List<EarlyAdmissionPhysicalWomanRelativeDto> getEarlyAdmissionPhysicalWomanRelativeList(Map<String, Object> params) {
        return earlyAdmissionPhysicalWomanRelativeDao.getEarlyAdmissionPhysicalWomanRelativeList(params);
    }

    // 수시 모집 실기 여자 상대평가 점수 정보 상세
    public EarlyAdmissionPhysicalWomanRelativeDto read(Map<String, Object> params) {
        return earlyAdmissionPhysicalWomanRelativeDao.getEarlyAdmissionPhysicalWomanRelative(params);
    }

    // 수시 모집 실기 여자 상대평가 점수 수정
    public boolean update(EarlyAdmissionPhysicalWomanRelativeDto earlyAdmissionPhysicalWomanRelative) {        
        return earlyAdmissionPhysicalWomanRelativeDao.updateEarlyAdmissionPhysicalWomanRelative(earlyAdmissionPhysicalWomanRelative) > 0;
    }

    // 수시 모집 실기 여자 상대평가 점수 삭제
    public boolean delete(EarlyAdmissionPhysicalWomanRelativeDto earlyAdmissionPhysicalWomanRelative) {
        return earlyAdmissionPhysicalWomanRelativeDao.deleteEarlyAdmissionPhysicalWomanRelative(earlyAdmissionPhysicalWomanRelative) > 0;
    }
    
}
