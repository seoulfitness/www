<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <%@ include file="../../base/head.jsp" %>
    <body class="nav-fixed">
        <%@ include file="../../base/nav.jsp" %>
        <div id="layoutSidenav">
            <%@ include file="../../base/layoutSidenav_nav.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <%@ include file="../../base/simple_header.jsp" %>
                    <%-- Main page content--%>
                    <div class="container-fluid px-4 mt-4">
                        <%-- 메시지 --%>
                        <%@ include file="../../base/message.jsp" %>
                        <%--// 메시지 --%>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        ${admission.admissionYear}년 ${admission.admissionType}군 ${admission.schoolName} ${admission.departmentName} 입시 요강 정보
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <%-- 입시 요강 정보 --%>
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
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="btn btn-danger btn-sm">수시 입시 미진행</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수시 입시 정보</th>
                                                        <td class="align-middle col-10">                                                            
                                                            <c:if test="${admission.earlyAdmission == 'Y'}">
                                                                <%-- 수시 입시 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty earlyAdmission}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/earlyAdmissions/${earlyAdmission.earlyAdmissionId}">수시 입시 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty earlyAdmission}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/earlyAdmissions/create?admissionId=${admission.admissionId}">수시 입시 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 수시 수능 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty earlyAdmissionCsat}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/earlyAdmissionCsat/${earlyAdmissionCsat.earlyAdmissionCsatId}">수능 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty earlyAdmissionCsat}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/earlyAdmissionCsat/create?admissionId=${admission.admissionId}">수능 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 수시 영어 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty earlyAdmissionEnglish}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/earlyAdmissionEnglish/${earlyAdmissionEnglish.earlyAdmissionId}">영어 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty earlyAdmissionEnglish}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/earlyAdmissionEnglish/create?admissionId=${admission.admissionId}">영어 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 수시 한국사 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty earlyAdmissionHistory}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/earlyAdmissionHistory/${earlyAdmissionHistory.earlyAdmissionId}">한국사 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty earlyAdmissionHistory}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/earlyAdmissionHistory/create?admissionId=${admission.admissionId}">한국사 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 수시 실기 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty earlyAdmissionPhysical}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/earlyAdmissionPhysical/${earlyAdmissionPhysical.earlyAdmissionId}">실기 정보</a>
                                                                    </c:when>
                                                                    <c:when test="${empty earlyAdmissionPhysical}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/earlyAdmissionPhysical/create?admissionId=${admission.admissionId}">실기 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 수시 내신 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty earlyAdmissionInternal}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/earlyAdmissionInternal/${earlyAdmissionInternal.earlyAdmissionId}">내신 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty earlyAdmissionInternal}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/earlyAdmissionInternal/create?admissionId=${admission.admissionId}">내신 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 수시 면접 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty earlyAdmissionInterview}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/earlyAdmissionInterview/${earlyAdmissionInterview.earlyAdmissionId}">면접 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty earlyAdmissionInterview}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/earlyAdmissionInterview/create?admissionId=${admission.admissionId}">면접 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">정시 입시</th>
                                                        <td class="align-middle col-10">
                                                            <c:choose>
                                                                <c:when test="${admission.regularAdmission == 'Y'}">
                                                                    <span class="btn btn-success btn-sm">정시 입시 진행</span>  
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="btn btn-danger btn-sm">정시 입시 미진행</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">정시 점수</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${admission.regularAdmission == 'Y'}">
                                                                <%-- 정시 입시 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty regularAdmission}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/regularAdmissions/${regularAdmission.regularAdmissionId}">정시 입시 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty regularAdmission}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/regularAdmissions/create?admissionId=${admission.admissionId}">정시 입시 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 정시 수능 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty regularAdmissionCsat}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/regularAdmissionCsat/${regularAdmissionCsat.regularAdmissionCsatId}">수능 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty regularAdmissionCsat}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/regularAdmissionCsat/create?admissionId=${admission.admissionId}">수능 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 정시 영어 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty regularAdmissionEnglish}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/regularAdmissionEnglish/${regularAdmissionEnglish.regularAdmissionEnglishId}">영어 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty regularAdmissionEnglish}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/regularAdmissionEnglish/create?admissionId=${admission.admissionId}">영어 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 정시 한국사 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty regularAdmissionHistory}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/regularAdmissionHistory/${regularAdmissionHistory.regularAdmissionHistoryId}">한국사 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty regularAdmissionHistory}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/regularAdmissionHistory/create?admissionId=${admission.admissionId}">한국사 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 정시 실기 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty regularAdmissionPhysical}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/regularAdmissionPhysical/${regularAdmissionPhysical.regularAdmissionPhysicalId}">실기 정보</a>
                                                                    </c:when>
                                                                    <c:when test="${empty regularAdmissionPhysical}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/regularAdmissionPhysical/create?admissionId=${admission.admissionId}">실기 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 정시 내신 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty regularAdmissionInternal}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/regularAdmissionInternal/${regularAdmissionInternal.regularAdmissionInternalId}">내신 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty regularAdmissionInternal}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/regularAdmissionInternal/create?admissionId=${admission.admissionId}">내신 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>

                                                                <%-- 정시 면접 정보 --%>
                                                                <c:choose>
                                                                    <c:when test="${not empty regularAdmissionInterview}">
                                                                        <a class="btn btn-primary btn-sm" href="/admin/regularAdmissionInterview/${regularAdmissionInterview.regularAdmissionInterviewId}">면접 정보</a>                                                                        
                                                                    </c:when>
                                                                    <c:when test="${empty regularAdmissionInterview}">
                                                                        <a class="btn btn-outline-danger btn-sm" href="/admin/regularAdmissionInterview/create?admissionId=${admission.admissionId}">면접 정보 입력</a>                                                                        
                                                                    </c:when>
                                                                </c:choose>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">메모</th>
                                                        <td class="align-middle col-10">${admission.admissionMemo}</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 입시 요강 정보 --%>

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
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../../base/footer.jsp" %>
            </div>
        </div>

        <%-- 삭제 모달 --%>
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="deleteForm" action="/admin/admissions/${admission.admissionId}/delete" method="POST">
                        <%-- modal-header --%>
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-danger" id="deleteModalModalLabel">
                                <strong>입시 요강 삭제</strong>
                            </h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <%--// modal-header --%>

                        <%-- modal-body --%>
                        <div class="modal-body">
                            <div class="mb-3">
                                <p class="text-danger">삭제된 데이터는 복구할 수 없습니다.</p>
                                <p>정말 삭제하시겠습니까?</p>
                            </div>
                        </div>
                        <%--// modal-body --%>

                        <%-- modal-footer --%>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-outline-danger">삭제</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                        </div>
                        <%--// modal-footer --%>
                    </form>
                </div>
            </div>
        </div>
        <%-- 삭제 모달 --%>

        <%@ include file="../../base/script.jsp" %>
    </body>
</html>
