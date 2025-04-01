<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${admission.earlyAdmission == 'Y'}">
    <h5 class="mb-2 text-primary text-sm">수시 입시 정보</h5>
    <c:choose>
        <%-- 수시 입시 정보 없음 --%>
        <c:when test="${empty earlyAdmission}">
            <table class="table table-bordered table-hover mb-5">
                <tbody>
                    <tr>
                        <td>
                            <a class="btn btn-outline-danger btn-sm" href="/admin/earlyAdmissions/create?admissionId=${admission.admissionId}">수시 입시 정보 입력</a> 
                        </td>
                    </tr>
                </tbody>
            </table>
        </c:when>
        <%--// 수시 입시 정보 없음 --%>

        <%-- 수시 입시 정보 있음 --%>
        <c:otherwise>
            <table class="table table-bordered table-hover mb-3">
                <tbody>
                    <tr>
                        <th>구분</th>
                        <td>점수 반영</td>
                        <td>점수</td>
                    </tr>      
                    <%-- 수능 점수 반영 --%>              
                    <c:choose>
                        <c:when test="${earlyAdmission.useCsatReflectedScore == 'Y'}">
                            <tr>
                                <th class="align-middle col-2">수능</th>    
                                <td class="align-middle col-5">
                                    <span class="btn btn-sm btn-success">반영</span>
                                </td>
                                <td class="align-middle col-5">
                                    ${earlyAdmission.csatReflectedScore}점
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <th class="align-middle col-2">수능</th>    
                                <td class="align-middle col-5">
                                    <span class="btn btn-sm btn-outline-danger">미반영</span>
                                </td>
                                <td class="align-middle col-5"></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    <%--// 수능 점수 반영 --%>

                    <%-- 실기 점수 반영 --%>
                    <c:choose>
                        <c:when test="${earlyAdmission.usePhysicalReflectedScore == 'Y'}">
                            <tr>
                                <th class="align-middle col-2">실기</th>
                                <td class="align-middle col-5">
                                    <span class="btn btn-sm btn-success">반영</span>
                                </td>
                                <td class="align-middle col-5">
                                    ${earlyAdmission.physicalReflectedScore}점
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <th class="align-middle col-2">실기</th>
                                <td class="align-middle col-5">
                                    <span class="btn btn-sm btn-outline-danger">미반영</span>
                                </td>
                                <td class="align-middle col-5"></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    <%--// 실기 점수 반영 --%>

                    <%-- 내신 점수 반영 --%>
                    <c:choose>
                        <c:when test="${earlyAdmission.useInternalReflectedScore == 'Y'}">
                            <tr>
                                <th class="align-middle col-2">내신</th>
                                <td class="align-middle col-5">
                                    <span class="btn btn-sm btn-success">반영</span>
                                </td>
                                <td class="align-middle col-5">
                                    ${earlyAdmission.internalReflectedScore}점
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <th class="align-middle col-2">내신</th>
                                <td class="align-middle col-5">
                                    <span class="btn btn-sm btn-outline-danger">미반영</span>
                                </td>
                                <td class="align-middle col-5"></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    <%--// 내신 점수 반영 --%>

                    <%-- 면접 점수 반영 --%>
                    <c:choose>
                        <c:when test="${earlyAdmission.useInterviewReflectedScore == 'Y'}">
                            <tr>
                                <th class="align-middle col-2">면접</th>
                                <td class="align-middle col-5">
                                    <span class="btn btn-sm btn-success">반영</span>
                                </td>
                                <td class="align-middle col-5">
                                    ${earlyAdmission.interviewReflectedScore}점
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <th class="align-middle col-2">면접</th>
                                <td class="align-middle col-5">
                                    <span class="btn btn-sm btn-outline-danger">미반영</span>
                                </td>
                                <td class="align-middle col-5"></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    <%--// 면접 점수 반영 --%>

                    <tr>
                        <th class="align-middle col-2">모집 인원</th>
                        <td class="align-middle col-10" colspan="2">${earlyAdmission.acceptedCount}</td>
                    </tr>
                    <tr>
                        <th class="align-middle col-2">메모</th>
                        <td class="align-middle col-10" colspan="2">
                            <c:if test="${not empty earlyAdmission.earlyAdmissionMemo}">
                                ${earlyAdmission.earlyAdmissionMemo}
                            </c:if>
                        </td>
                    </tr>   
                </tbody>
            </table>        
            <div class="mb-5">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a href="/admin/earlyAdmissions/${earlyAdmission.earlyAdmissionId}/update" class="btn btn-outline-warning">수시 입시 정보 수정</a>
            </div>
        </c:otherwise>
        <%--// 수시 입시 정보 있음 --%>
    </c:choose>
</c:if>