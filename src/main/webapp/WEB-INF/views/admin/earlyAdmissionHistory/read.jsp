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
                                        ${admission.admissionYear}년 ${admission.admissionType}군 ${admission.schoolName} ${admission.departmentName} 수시 입시 한국사 정보
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <%-- 수시 입시 한국사 정보 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle col-2">한국사 등급 1 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            ${earlyAdmissionHistory.subject20ReflectedGrade1}점
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">한국사 등급 2 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            ${earlyAdmissionHistory.subject20ReflectedGrade2}점
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">한국사 등급 3 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            ${earlyAdmissionHistory.subject20ReflectedGrade3}점
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">한국사 등급 4 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            ${earlyAdmissionHistory.subject20ReflectedGrade4}점
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">한국사 등급 5 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            ${earlyAdmissionHistory.subject20ReflectedGrade5}점
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">한국사 등급 6 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            ${earlyAdmissionHistory.subject20ReflectedGrade6}점
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">한국사 등급 7 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            ${earlyAdmissionHistory.subject20ReflectedGrade7}점
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">한국사 등급 8 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            ${earlyAdmissionHistory.subject20ReflectedGrade8}점
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">한국사 등급 9 반영 점수</th>
                                                        <td class="align-middle col-10">
                                                            ${earlyAdmissionHistory.subject20ReflectedGrade9}점
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">메모</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${not empty earlyAdmissionCsat.earlyAdmissionCsatMemo}">
                                                                ${earlyAdmissionCsat.earlyAdmissionCsatMemo}
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
                                                        <td class="align-middle col-10"><fmt:formatDate value="${earlyAdmissionHistory.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">등록한 사람</th>
                                                        <td class="align-middle col-10">${earlyAdmissionHistory.createdUserName} (${earlyAdmissionHistory.createdBy}, ${earlyAdmissionHistory.createdUserPhone})</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${earlyAdmissionHistory.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정한 사람</th>
                                                        <td class="align-middle col-10">${earlyAdmissionHistory.updatedUserName} (${earlyAdmissionHistory.updatedBy}, ${earlyAdmissionHistory.updatedUserPhone})</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 등록일시, 수정일시 --%>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <a href="/admin/admissions/${earlyAdmissionHistory.admissionId}" class="btn btn-primary">입시 요강 정보</a>
                                        <a href="/admin/earlyAdmissionHistory/${earlyAdmissionHistory.earlyAdmissionHistoryId}/update" class="btn btn-outline-warning">수시 입시 한국사 정보 수정</a>
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
