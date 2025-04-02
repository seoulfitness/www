<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="card mb-4 admission">
    <div class="card-header">
        ${admission.admissionYear}년 ${admission.admissionType}군 ${admission.schoolName} ${admission.departmentName} 입시 요강 정보
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered table-hover mb-3">
                <tbody>
                    <tr>
                        <th class="align-middle col-2">연도</th>
                        <td class="align-middle col-10">${admission.admissionYear}년</td>
                    </tr>
                    <tr>
                        <th class="align-middle col-2">구분</th>
                        <td class="align-middle col-10">${admission.admissionType}군</td>
                    </tr>                                                    
                    <tr>
                        <th class="align-middle col-2">학교</th>
                        <td class="align-middle col-10">${admission.schoolName}</td>
                    </tr>
                    <tr>
                        <th class="align-middle col-2">학과</th>
                        <td class="align-middle col-10">${admission.departmentName}</td>
                    </tr>
                    <tr>
                        <th class="align-middle col-2">수시 입시</th>
                        <td class="align-middle col-10">
                            <c:choose>
                                <c:when test="${admission.earlyAdmission == 'Y'}">
                                    <span class="btn btn-success btn-sm">수시 입시 진행</span>  
                                    <a href="#earlyAdmission" class="btn btn-outline-primary btn-sm">입시 정보</a>
                                    <a href="#earlyAdmissionCsat" class="btn btn-outline-primary btn-sm">수능 정보</a>
                                    <a href="#earlyAdmissionEnglish" class="btn btn-outline-primary btn-sm">영어 정보</a>
                                    <a href="#earlyAdmissionHistory" class="btn btn-outline-primary btn-sm">한국사 정보</a>
                                    <a href="#earlyAdmissionPhysical" class="btn btn-outline-primary btn-sm">실기 정보</a>
                                </c:when>
                                <c:otherwise>
                                    <span class="btn btn-danger btn-sm">수시 입시 미진행</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th class="align-middle col-2">정시 입시</th>
                        <td class="align-middle col-10">
                            <c:choose>
                                <c:when test="${admission.regularAdmission == 'Y'}">
                                    <span class="btn btn-success btn-sm">정시 입시 진행</span>  
                                    <a href="#regularAdmission" class="btn btn-outline-primary btn-sm">입시 정보</a>
                                    <a href="#regularAdmissionCsat" class="btn btn-outline-primary btn-sm">수능 정보</a>
                                    <a href="#regularAdmissionEnglish" class="btn btn-outline-primary btn-sm">영어 정보</a>
                                    <a href="#regularAdmissionHistory" class="btn btn-outline-primary btn-sm">한국사 정보</a>
                                    <a href="#regularAdmissionPhysical" class="btn btn-outline-primary btn-sm">실기 정보</a>
                                </c:when>
                                <c:otherwise>
                                    <span class="btn btn-danger btn-sm">정시 입시 미진행</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th class="align-middle col-2">메모</th>
                        <td class="align-middle col-10">${admission.admissionMemo}</td>
                    </tr>
                </tbody>
            </table>
            <%-- 등록일시, 수정일시 --%>
            <table class="table table-bordered table-hover mb-3">
                <tbody>
                    <tr class="border-top">
                        <th class="align-middle col-2">등록일시</th>
                        <td class="align-middle col-10"><fmt:formatDate value="${admission.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                    <tr>
                        <th class="align-middle col-2">등록한 사람</th>
                        <td class="align-middle col-10">${admission.createdUserName} (${admission.createdBy}, ${admission.createdUserPhone})</td>
                    </tr>
                    <tr>
                        <th class="align-middle col-2">수정일시</th>
                        <td class="align-middle col-10"><fmt:formatDate value="${admission.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                    <tr>
                        <th class="align-middle col-2">수정한 사람</th>
                        <td class="align-middle col-10">${admission.updatedUserName} (${admission.updatedBy}, ${admission.updatedUserPhone})</td>
                    </tr>
                </tbody>
            </table>
            <%--// 등록일시, 수정일시 --%>
        </div>
    </div>
    <div class="card-footer">
        <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
        <a href="/admin/admissions/${admission.admissionId}/update" class="btn btn-outline-warning">입시 요강 수정</a>
        <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">입시 요강 삭제</button>
    </div>
</div>