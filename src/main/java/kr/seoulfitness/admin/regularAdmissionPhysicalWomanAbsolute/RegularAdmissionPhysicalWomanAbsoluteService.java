package kr.seoulfitness.admin.regularAdmissionPhysicalWomanAbsolute;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularAdmissionPhysicalWomanAbsoluteService {
    
    @Autowired
    private RegularAdmissionPhysicalWomanAbsoluteDao regularAdmissionPhysicalWomanAbsoluteDao;

    // 정시 모집 실기 여자 절대평가 점수 등록
    public RegularAdmissionPhysicalWomanAbsoluteDto create(RegularAdmissionPhysicalWomanAbsoluteDto regularAdmissionPhysicalWomanAbsolute) {        
        return regularAdmissionPhysicalWomanAbsoluteDao.insertRegularAdmissionPhysicalWomanAbsolute(regularAdmissionPhysicalWomanAbsolute);
    }

    // 정시 모집 실기 여자 절대평가 점수 목록
    public List<RegularAdmissionPhysicalWomanAbsoluteDto> getRegularAdmissionPhysicalWomanAbsoluteList(Map<String, Object> params) {
        return regularAdmissionPhysicalWomanAbsoluteDao.getRegularAdmissionPhysicalWomanAbsoluteList(params);
    }

    // 정시 모집 실기 여자 절대평가 점수 정보 상세
    public RegularAdmissionPhysicalWomanAbsoluteDto read(Map<String, Object> params) {
        return regularAdmissionPhysicalWomanAbsoluteDao.getRegularAdmissionPhysicalWomanAbsolute(params);
    }

    // 정시 모집 실기 여자 절대평가 점수 수정
    public boolean update(RegularAdmissionPhysicalWomanAbsoluteDto regularAdmissionPhysicalWomanAbsolute) {        
        return regularAdmissionPhysicalWomanAbsoluteDao.updateRegularAdmissionPhysicalWomanAbsolute(regularAdmissionPhysicalWomanAbsolute) > 0;
    }

    // 정시 모집 실기 여자 절대평가 점수 삭제
    public boolean delete(RegularAdmissionPhysicalWomanAbsoluteDto regularAdmissionPhysicalWomanAbsolute) {
        return regularAdmissionPhysicalWomanAbsoluteDao.deleteRegularAdmissionPhysicalWomanAbsolute(regularAdmissionPhysicalWomanAbsolute) > 0;
    }
    
}
