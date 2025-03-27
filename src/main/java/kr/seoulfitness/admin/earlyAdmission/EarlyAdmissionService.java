package kr.seoulfitness.admin.earlyAdmission;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarlyAdmissionService {
    
    @Autowired
    private EarlyAdmissionDao earlyAdmissionDao;

    // 수시 모집 등록
    public EarlyAdmissionDto create(EarlyAdmissionDto earlyAdmission) {        
        return earlyAdmissionDao.insertEarlyAdmission(earlyAdmission);
    }

    // 수시 모집 상세보기
    public EarlyAdmissionDto read(Map<String, Object> params) {
        return earlyAdmissionDao.getEarlyAdmission(params);
    }

    // 수시 모집 수정
    public boolean update(EarlyAdmissionDto earlyAdmission) {        
        return earlyAdmissionDao.updateEarlyAdmission(earlyAdmission) > 0;
    }
    
}
