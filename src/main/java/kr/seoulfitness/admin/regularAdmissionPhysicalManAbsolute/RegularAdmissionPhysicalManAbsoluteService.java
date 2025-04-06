package kr.seoulfitness.admin.regularAdmissionPhysicalManAbsolute;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularAdmissionPhysicalManAbsoluteService {
    
    @Autowired
    private RegularAdmissionPhysicalManAbsoluteDao regularAdmissionPhysicalManAbsoluteDao;

    // 정시 모집 실기 남자 절대평가 점수 등록
    public RegularAdmissionPhysicalManAbsoluteDto create(RegularAdmissionPhysicalManAbsoluteDto regularAdmissionPhysicalManAbsolute) {        
        return regularAdmissionPhysicalManAbsoluteDao.insertRegularAdmissionPhysicalManAbsolute(regularAdmissionPhysicalManAbsolute);
    }

    // 정시 모집 실기 남자 절대평가 점수 목록
    public List<RegularAdmissionPhysicalManAbsoluteDto> getRegularAdmissionPhysicalManAbsoluteList(Map<String, Object> params) {
        return regularAdmissionPhysicalManAbsoluteDao.getRegularAdmissionPhysicalManAbsoluteList(params);
    }

    // 정시 모집 실기 남자 절대평가 점수 정보 상세
    public RegularAdmissionPhysicalManAbsoluteDto read(Map<String, Object> params) {
        return regularAdmissionPhysicalManAbsoluteDao.getRegularAdmissionPhysicalManAbsolute(params);
    }

    // 정시 모집 실기 남자 절대평가 점수 수정
    public boolean update(RegularAdmissionPhysicalManAbsoluteDto regularAdmissionPhysicalManAbsolute) {        
        return regularAdmissionPhysicalManAbsoluteDao.updateRegularAdmissionPhysicalManAbsolute(regularAdmissionPhysicalManAbsolute) > 0;
    }

    // 정시 모집 실기 남자 절대평가 점수 삭제
    public boolean delete(RegularAdmissionPhysicalManAbsoluteDto regularAdmissionPhysicalManAbsolute) {
        return regularAdmissionPhysicalManAbsoluteDao.deleteRegularAdmissionPhysicalManAbsolute(regularAdmissionPhysicalManAbsolute) > 0;
    }
    
}
