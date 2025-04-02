<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <%-- 정시 입시 정보 없음 --%>
    <c:when test="${empty regularAdmission}">
        <div class="card mb-4" id="regularAdmission">
            <div class="card-header">
                정시 입시 정보
            </div>
            <div class="card-body">
                <div class="alert alert-danger">
                    정시 입시 정보가 없습니다. 정시 입시 정보를 입력해주세요.
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a class="btn btn-outline-danger" href="/admin/regularAdmissions/create?admissionId=${admission.admissionId}">정시 입시 정보 입력</a> 
            </div>
        </div>
    </c:when>
    <%--// 정시 입시 정보 없음 --%>

    <%-- 정시 입시 정보 있음 --%>
    <c:otherwise>
        <div class="card mb-4" id="regularAdmission">
            <div class="card-header">
                정시 입시 정보
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover mb-3">
                        <tbody>
                            <tr>
                                <th>구분</th>
                                <td>점수 반영</td>
                                <td>점수</td>
                            </tr>      
                            <%-- 수능 점수 반영 --%>              
                            <c:choose>
                                <c:when test="${regularAdmission.useCsatReflectedScore == 'Y'}">
                                    <tr>
                                        <th class="align-middle col-2">수능</th>    
                                        <td class="align-middle col-2">
                                            <span class="btn btn-sm btn-success">반영</span>
                                        </td>
                                        <td class="align-middle col-8">
                                            ${regularAdmission.csatReflectedScore}점
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <th class="align-middle col-2">수능</th>    
                                        <td class="align-middle col-2">
                                            <span class="btn btn-sm btn-outline-danger">미반영</span>
                                        </td>
                                        <td class="align-middle col-8"></td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            <%--// 수능 점수 반영 --%>
    
                            <%-- 실기 점수 반영 --%>
                            <c:choose>
                                <c:when test="${regularAdmission.usePhysicalReflectedScore == 'Y'}">
                                    <tr>
                                        <th class="align-middle col-2">실기</th>
                                        <td class="align-middle col-2">
                                            <span class="btn btn-sm btn-success">반영</span>
                                        </td>
                                        <td class="align-middle col-8">
                                            ${regularAdmission.physicalReflectedScore}점
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <th class="align-middle col-2">실기</th>
                                        <td class="align-middle col-2">
                                            <span class="btn btn-sm btn-outline-danger">미반영</span>
                                        </td>
                                        <td class="align-middle col-8"></td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            <%--// 실기 점수 반영 --%>
    
                            <%-- 내신 점수 반영 --%>
                            <c:choose>
                                <c:when test="${regularAdmission.useInternalReflectedScore == 'Y'}">
                                    <tr>
                                        <th class="align-middle col-2">내신</th>
                                        <td class="align-middle col-2">
                                            <span class="btn btn-sm btn-success">반영</span>
                                        </td>
                                        <td class="align-middle col-8">
                                            ${regularAdmission.internalReflectedScore}점
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <th class="align-middle col-2">내신</th>
                                        <td class="align-middle col-2">
                                            <span class="btn btn-sm btn-outline-danger">미반영</span>
                                        </td>
                                        <td class="align-middle col-8"></td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            <%--// 내신 점수 반영 --%>
    
                            <%-- 면접 점수 반영 --%>
                            <c:choose>
                                <c:when test="${regularAdmission.useInterviewReflectedScore == 'Y'}">
                                    <tr>
                                        <th class="align-middle col-2">면접</th>
                                        <td class="align-middle col-2">
                                            <span class="btn btn-sm btn-success">반영</span>
                                        </td>
                                        <td class="align-middle col-8">
                                            ${regularAdmission.interviewReflectedScore}점
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <th class="align-middle col-2">면접</th>
                                        <td class="align-middle col-2">
                                            <span class="btn btn-sm btn-outline-danger">미반영</span>
                                        </td>
                                        <td class="align-middle col-8"></td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            <%--// 면접 점수 반영 --%>
    
                            <tr>
                                <th class="align-middle col-2">모집 인원</th>
                                <td class="align-middle col-10" colspan="2">${regularAdmission.acceptedCount}</td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">메모</th>
                                <td class="align-middle col-10" colspan="2">
                                    <c:if test="${not empty regularAdmission.regularAdmissionMemo}">
                                        ${regularAdmission.regularAdmissionMemo}
                                    </c:if>
                                </td>
                            </tr>   
                        </tbody>
                    </table>   

                    <%-- 등록일시, 수정일시 --%>
                    <table class="table table-bordered table-hover mb-3">
                        <tbody>
                            <tr class="border-top">
                                <th class="align-middle col-2">등록일시</th>
                                <td class="align-middle col-10"><fmt:formatDate value="${regularAdmission.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">등록한 사람</th>
                                <td class="align-middle col-10">${regularAdmission.createdUserName} (${regularAdmission.createdBy}, ${regularAdmission.createdUserPhone})</td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정일시</th>
                                <td class="align-middle col-10"><fmt:formatDate value="${regularAdmission.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정한 사람</th>
                                <td class="align-middle col-10">${regularAdmission.updatedUserName} (${regularAdmission.updatedBy}, ${regularAdmission.updatedUserPhone})</td>
                            </tr>
                        </tbody>
                    </table>
                    <%--// 등록일시, 수정일시 --%>
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a href="/admin/regularAdmissions/${regularAdmission.regularAdmissionId}/update" class="btn btn-outline-warning">정시 입시 정보 수정</a>
            </div>
        </div>
    </c:otherwise>
    <%--// 정시 입시 정보 있음 --%>
</c:choose>
