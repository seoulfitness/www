package kr.seoulfitness.branchManager.school;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/branchManager/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    // 파일 업로드 경로
    private final String uploadPath = System.getProperty("os.name").toLowerCase().contains("win") 
        ? "c:/upload/schools/" 
        : "/home/ubuntu/upload/schools/";

    private static final Logger logger = LoggerFactory.getLogger(SchoolController.class);

    // 대학교 존재 여부 확인
    public boolean isSchoolExists(int schoolId) {
        return schoolService.read(schoolId) != null;
    }

    // 대학교 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "대학교 관리");
        model.addAttribute("active_page", "schools");
        return "branchManager/school/create";
    }

    // 대학교 등록 처리
    @PostMapping("/create")
    public String createPost(SchoolDto school, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            // 업로드 파일
            MultipartFile uploadFile = school.getSchoolLogoFile();

            // 업로드 파일이 존재하는 경우
            if (uploadFile != null && !uploadFile.isEmpty()) {
                // 업로드 파일 이름
                String originalFileName = uploadFile.getOriginalFilename();
                String fileName = UUID.randomUUID().toString() + "_" + originalFileName;

                // 업로드 디렉토리가 없으면 생성
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // 파일 저장
                File fileToUpload = new File(uploadPath + File.separator + fileName);
                uploadFile.transferTo(fileToUpload);

                // 파일 정보 설정
                school.setSchoolLogoFileName(fileName);
                school.setSchoolLogoOriginalFileName(originalFileName);
            }

            // 대학교 등록
            school.setCreatedBy((String) session.getAttribute("userId"));
            school.setUpdatedBy((String) session.getAttribute("userId"));
            boolean result = schoolService.create(school);
            if (result) {
                redirectAttributes.addFlashAttribute("successMessage", "대학교 등록이 완료되었습니다.");
                return "redirect:/branchManager/schools";
            }

            redirectAttributes.addFlashAttribute("errorMessage", "대학교 등록에 실패했습니다.");
            redirectAttributes.addFlashAttribute("school", school);
            return "redirect:/branchManager/schools/create";
        } catch (IOException | IllegalStateException e) {
            logger.error("대학교 등록 중 오류 발생 : {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "파일 업로드 중 오류가 발생했습니다.");
            return "redirect:/branchManager/schools/create";
        }
    }

    // 대학교 목록
    @GetMapping("")
    public String listGet(
        @RequestParam(value = "page", defaultValue = "1") int currentPage, 
        @RequestParam(required = false) String keyword,
        Model model
    ) {
        int listCountPerPage = 10;  // 한 페이지에서 불러올 게시글 수
        int pageCountPerPage = 5;   // 한 페이지에서 보여질 페이지 수
        Map<String, Object> result = schoolService.list(currentPage, listCountPerPage, pageCountPerPage, keyword);
        model.addAttribute("schools", result.get("schools"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "대학교 관리");
        model.addAttribute("active_page", "schools");

        return "branchManager/school/list";
    }

    // 대학교 상세
    @GetMapping("/{schoolId}")
    public String readGet(@PathVariable int schoolId, Model model) {
        // 대학교 존재 여부 확인
        if (!isSchoolExists(schoolId)) {
            return "redirect:/branchManager/schools";
        }

        // 대학교 상세
        SchoolDto school = schoolService.read(schoolId);
        model.addAttribute("school", school);
        model.addAttribute("pageTitle", "대학교 관리");
        model.addAttribute("active_page", "schools");
        return "branchManager/school/read";
    }

    // 대학교 수정
    @GetMapping("/{schoolId}/update")
    public String updateGet(@PathVariable int schoolId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {   
        // 대학교 존재 여부 확인
        if (!isSchoolExists(schoolId)) {
            return "redirect:/branchManager/schools";
        }

        // 대학교 상세
        SchoolDto school = schoolService.read(schoolId);
        model.addAttribute("school", school);
        model.addAttribute("pageTitle", "대학교 관리");
        model.addAttribute("active_page", "schools");
        return "branchManager/school/update";
    }

    // 대학교 수정 처리
    @PostMapping("/{schoolId}/update")
    public String updatePost(@PathVariable int schoolId, SchoolDto school, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        // 대학교 존재 여부 확인
        if (!isSchoolExists(schoolId)) {
            return "redirect:/branchManager/schools";
        }

        try {
            // 업로드 파일
            MultipartFile uploadFile = school.getSchoolLogoFile();

            // 파일 삭제 체크되어 있는 경우
            if (school.getDeleteFile() != null && school.getDeleteFile().equals("Y")) {
                File fileToDelete = new File(uploadPath + File.separator + school.getSchoolLogoFileName());
                if (fileToDelete.exists()) {
                    fileToDelete.delete();
                }

                school.setSchoolLogoFileName(null);
                school.setSchoolLogoOriginalFileName(null);
            }

            // 업로드 파일이 존재하는 경우
            if (uploadFile != null && !uploadFile.isEmpty()) {
                // 기존 파일 삭제
                SchoolDto existsSchool = schoolService.read(schoolId);
                if (existsSchool.getSchoolLogoFileName() != null) {
                    File fileToDelete = new File(uploadPath + File.separator + existsSchool.getSchoolLogoFileName());
                    if (fileToDelete.exists()) {
                        fileToDelete.delete();
                    }
                }

                // 업로드 파일 이름
                String originalFileName = uploadFile.getOriginalFilename();
                String fileName = UUID.randomUUID().toString() + "_" + originalFileName;

                // 업로드 디렉토리가 없으면 생성
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // 파일 저장
                File fileToUpload = new File(uploadPath + File.separator + fileName);
                uploadFile.transferTo(fileToUpload);

                // 파일 정보 설정
                school.setSchoolLogoFileName(fileName);
                school.setSchoolLogoOriginalFileName(originalFileName);
            }

            // 대학교 수정
            school.setSchoolId(schoolId);
            school.setUpdatedBy((String) session.getAttribute("userId"));
            boolean result = schoolService.update(school);
            if (!result) {
                model.addAttribute("school", school);
                redirectAttributes.addFlashAttribute("errorMessage", "대학교 수정에 실패했습니다.");
                return "redirect:/branchManager/schools/" + schoolId + "/update";
            }

            redirectAttributes.addFlashAttribute("successMessage", "대학교 수정이 완료되었습니다.");
            return "redirect:/branchManager/schools/" + schoolId;
        } catch (IOException | IllegalStateException e) {
            logger.error("대학교 수정 중 오류 발생 : {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "파일 업로드 중 오류가 발생했습니다.");
            return "redirect:/branchManager/schools/" + schoolId + "/update";
        }
    }

    // 대학교 삭제
    @PostMapping("/{schoolId}/delete")
    public String deletePost(@PathVariable int schoolId, RedirectAttributes redirectAttributes) {
        // 대학교 존재 여부 확인
        if (!isSchoolExists(schoolId)) {
            return "redirect:/branchManager/schools";
        }

        // 대학교 삭제
        boolean result = schoolService.delete(schoolId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "대학교 삭제가 완료되었습니다.");
            return "redirect:/branchManager/schools";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "대학교 삭제에 실패했습니다.");
        return "redirect:/branchManager/schools/" + schoolId;
    }
}
