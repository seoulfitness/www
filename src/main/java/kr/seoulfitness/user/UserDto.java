package kr.seoulfitness.user;

import kr.seoulfitness.libs.ExtDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends ExtDto {
    private int id;                 // 사용자 구분자   
    private String userId;          // 사용자 아이디   
    private String password;        // 사용자 비밀번호
    private String name;            // 사용자 이름
    private String email;           // 사용자 이메일
    private String phone;           // 사용자 전화번호
    private String profileImage;    // 사용자 프로필 이미지
    private String role;            // 사용자 역할
    private String status;          // 사용자 상태
    private String deleteRequest;   // 사용자 삭제 요청
    private String deleteRequestedAt; // 사용자 삭제 요청일
}

/*
CREATE TABLE USERS (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL,
    NAME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    PHONE VARCHAR(20) NOT NULL,
    PROFILE_IMAGE VARCHAR(255),
    ROLE VARCHAR(20) NOT NULL DEFAULT 'NORMAL_USER',
    STATUS CHAR(1) NOT NULL DEFAULT 'Y',
    DELETE_REQUEST CHAR(1) DEFAULT 'N',
    DELETE_REQUESTED_AT DATETIME,
    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CREATED_BY INT,
    UPDATED_BY INT
);
*/