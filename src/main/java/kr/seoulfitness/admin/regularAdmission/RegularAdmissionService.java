package kr.seoulfitness.admin.regularAdmission;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularAdmissionService {
    
    @Autowired
    private RegularAdmissionDao regularAdmissionDao;

    // 수시 모집 등록
    public RegularAdmissionDto create(RegularAdmissionDto regularAdmission) {        
        return regularAdmissionDao.insertRegularAdmission(regularAdmission);
    }

    // 수시 모집 상세보기
    public RegularAdmissionDto read(Map<String, Object> params) {
        return regularAdmissionDao.getRegularAdmission(params);
    }

    // 수시 모집 수정
    public boolean update(RegularAdmissionDto regularAdmission) {        
        return regularAdmissionDao.updateRegularAdmission(regularAdmission) > 0;
    }
    
}
