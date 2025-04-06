package kr.seoulfitness.admin.regularAdmissionPhysicalWomanRelative;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularAdmissionPhysicalWomanRelativeService {
    
    @Autowired
    private RegularAdmissionPhysicalWomanRelativeDao regularAdmissionPhysicalWomanRelativeDao;

    // 정시 모집 실기 여자 상대평가 점수 등록
    public RegularAdmissionPhysicalWomanRelativeDto create(RegularAdmissionPhysicalWomanRelativeDto regularAdmissionPhysicalWomanRelative) {        
        return regularAdmissionPhysicalWomanRelativeDao.insertRegularAdmissionPhysicalWomanRelative(regularAdmissionPhysicalWomanRelative);
    }

    // 정시 모집 실기 여자 상대평가 점수 목록
    public List<RegularAdmissionPhysicalWomanRelativeDto> getRegularAdmissionPhysicalWomanRelativeList(Map<String, Object> params) {
        return regularAdmissionPhysicalWomanRelativeDao.getRegularAdmissionPhysicalWomanRelativeList(params);
    }

    // 정시 모집 실기 여자 상대평가 점수 정보 상세
    public RegularAdmissionPhysicalWomanRelativeDto read(Map<String, Object> params) {
        return regularAdmissionPhysicalWomanRelativeDao.getRegularAdmissionPhysicalWomanRelative(params);
    }

    // 정시 모집 실기 여자 상대평가 점수 수정
    public boolean update(RegularAdmissionPhysicalWomanRelativeDto regularAdmissionPhysicalWomanRelative) {        
        return regularAdmissionPhysicalWomanRelativeDao.updateRegularAdmissionPhysicalWomanRelative(regularAdmissionPhysicalWomanRelative) > 0;
    }

    // 정시 모집 실기 여자 상대평가 점수 삭제
    public boolean delete(RegularAdmissionPhysicalWomanRelativeDto regularAdmissionPhysicalWomanRelative) {
        return regularAdmissionPhysicalWomanRelativeDao.deleteRegularAdmissionPhysicalWomanRelative(regularAdmissionPhysicalWomanRelative) > 0;
    }
    
}
