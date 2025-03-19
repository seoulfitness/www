package kr.seoulfitness.libs;

import lombok.Data;

@Data
public class ExtDto {
    private String createdAt;       // 사용자 생성일
    private String updatedAt;       // 사용자 수정일
    private int createdBy;       // 사용자 생성자
    private int updatedBy;       // 사용자 수정자
}
