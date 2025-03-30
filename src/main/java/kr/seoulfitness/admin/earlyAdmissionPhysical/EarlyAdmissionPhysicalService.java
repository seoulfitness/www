package kr.seoulfitness.admin.earlyAdmissionPhysical;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarlyAdmissionPhysicalService {
    
    @Autowired
    private EarlyAdmissionPhysicalDao earlyAdmissionPhysicalDao;

    // 수시 모집 실기 등록
    public EarlyAdmissionPhysicalDto create(EarlyAdmissionPhysicalDto earlyAdmissionPhysical) {        
        return earlyAdmissionPhysicalDao.insertEarlyAdmissionPhysical(earlyAdmissionPhysical);
    }

    // 수시 모집 실기 상세보기
    public EarlyAdmissionPhysicalDto read(Map<String, Object> params) {
        return earlyAdmissionPhysicalDao.getEarlyAdmissionPhysical(params);
    }

    // 수시 모집 실기 수정
    public boolean update(EarlyAdmissionPhysicalDto earlyAdmissionPhysical) {        
        return earlyAdmissionPhysicalDao.updateEarlyAdmissionPhysical(earlyAdmissionPhysical) > 0;
    }
    
}
