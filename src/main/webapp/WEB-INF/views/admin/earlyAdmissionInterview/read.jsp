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
                                        수시 입시 정보
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <%-- 수시 입시 정보 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr class="border-top">
                                                        <th class="align-middle col-2">수능 점수 반영</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${earlyAdmission.useCsatReflectedScore == 'Y'}">
                                                                <span class="btn btn-sm btn-success">반영</span>
                                                            </c:if>
                                                            <c:if test="${earlyAdmission.useCsatReflectedScore == 'N'}">
                                                                <span class="btn btn-sm btn-danger">미반영</span>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수능 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${earlyAdmission.useCsatReflectedScore == 'Y'}">
                                                                ${earlyAdmission.csatReflectedScore}점
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">실기 점수 반영</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${earlyAdmission.usePhysicalReflectedScore == 'Y'}">
                                                                <span class="btn btn-sm btn-success">반영</span>
                                                            </c:if>
                                                            <c:if test="${earlyAdmission.usePhysicalReflectedScore == 'N'}">
                                                                <span class="btn btn-sm btn-danger">미반영</span>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">실기 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${earlyAdmission.usePhysicalReflectedScore == 'Y'}">
                                                                ${earlyAdmission.physicalReflectedScore}점
                                                            </c:if>
                                                        </td>
                                                    </tr>                        
                                                    <tr>
                                                        <th class="align-middle col-2">내신 점수 반영</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${earlyAdmission.useInternalReflectedScore == 'Y'}">
                                                                <span class="btn btn-sm btn-success">반영</span>
                                                            </c:if>
                                                            <c:if test="${earlyAdmission.useInternalReflectedScore == 'N'}">
                                                                <span class="btn btn-sm btn-danger">미반영</span>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">내신 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${earlyAdmission.useInternalReflectedScore == 'Y'}">
                                                                ${earlyAdmission.internalReflectedScore}점
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">면접 점수 반영</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${earlyAdmission.useInterviewReflectedScore == 'Y'}">
                                                                <span class="btn btn-sm btn-success">반영</span>
                                                            </c:if>
                                                            <c:if test="${earlyAdmission.useInterviewReflectedScore == 'N'}">
                                                                <span class="btn btn-sm btn-danger">미반영</span>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">면접 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${earlyAdmission.useInterviewReflectedScore == 'Y'}">
                                                                ${earlyAdmission.interviewReflectedScore}점
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">모집 인원</th>
                                                        <td class="align-middle col-10">${earlyAdmission.acceptedCount}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">메모</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${not empty earlyAdmission.earlyAdmissionMemo}">
                                                                ${earlyAdmission.earlyAdmissionMemo}
                                                            </c:if>
                                                        </td>
                                                    </tr>   
                                                </tbody>
                                            </table>
                                            <%--// 수시 입시 정보 --%>

                                            <%-- 등록일시, 수정일시 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr class="border-top">
                                                        <th class="align-middle col-2">등록일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${earlyAdmission.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">등록한 사람</th>
                                                        <td class="align-middle col-10">${earlyAdmission.createdUserName} (${earlyAdmission.createdBy}, ${earlyAdmission.createdUserPhone})</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${earlyAdmission.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정한 사람</th>
                                                        <td class="align-middle col-10">${earlyAdmission.updatedUserName} (${earlyAdmission.updatedBy}, ${earlyAdmission.updatedUserPhone})</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 등록일시, 수정일시 --%>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <a href="/admin/admissions/${earlyAdmission.admissionId}" class="btn btn-primary">입시 요강 정보</a>
                                        <a href="/admin/earlyAdmissions/${earlyAdmission.earlyAdmissionId}/update" class="btn btn-outline-warning">수시 입시 정보 수정</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../../base/footer.jsp" %>
            </div>
        </div>

        <%@ include file="../../base/script.jsp" %>
    </body>
</html>
