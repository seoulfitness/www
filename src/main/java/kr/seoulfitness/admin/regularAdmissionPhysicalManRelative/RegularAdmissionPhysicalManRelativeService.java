package kr.seoulfitness.admin.regularAdmissionPhysicalManRelative;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularAdmissionPhysicalManRelativeService {
    
    @Autowired
    private RegularAdmissionPhysicalManRelativeDao regularAdmissionPhysicalManRelativeDao;

    // 정시 모집 실기 남자 상대평가 점수 등록
    public RegularAdmissionPhysicalManRelativeDto create(RegularAdmissionPhysicalManRelativeDto regularAdmissionPhysicalManRelative) {        
        return regularAdmissionPhysicalManRelativeDao.insertRegularAdmissionPhysicalManRelative(regularAdmissionPhysicalManRelative);
    }

    // 정시 모집 실기 남자 상대평가 점수 목록
    public List<RegularAdmissionPhysicalManRelativeDto> getRegularAdmissionPhysicalManRelativeList(Map<String, Object> params) {
        return regularAdmissionPhysicalManRelativeDao.getRegularAdmissionPhysicalManRelativeList(params);
    }

    // 정시 모집 실기 남자 상대평가 점수 정보 상세
    public RegularAdmissionPhysicalManRelativeDto read(Map<String, Object> params) {
        return regularAdmissionPhysicalManRelativeDao.getRegularAdmissionPhysicalManRelative(params);
    }

    // 정시 모집 실기 남자 상대평가 점수 수정
    public boolean update(RegularAdmissionPhysicalManRelativeDto regularAdmissionPhysicalManRelative) {        
        return regularAdmissionPhysicalManRelativeDao.updateRegularAdmissionPhysicalManRelative(regularAdmissionPhysicalManRelative) > 0;
    }

    // 정시 모집 실기 남자 상대평가 점수 삭제
    public boolean delete(RegularAdmissionPhysicalManRelativeDto regularAdmissionPhysicalManRelative) {
        return regularAdmissionPhysicalManRelativeDao.deleteRegularAdmissionPhysicalManRelative(regularAdmissionPhysicalManRelative) > 0;
    }
    
}
