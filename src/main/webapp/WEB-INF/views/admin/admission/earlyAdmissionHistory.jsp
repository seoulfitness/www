<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${admission.earlyAdmission == 'Y'}">
    <h5 class="mb-2 text-primary text-sm">수시 한국사 정보</h5>
    <c:choose>
        <%-- 수시 한국사 정보 없음 --%>
        <c:when test="${empty earlyAdmissionHistory}">
            <table class="table table-bordered table-hover mb-5">
                <tbody>
                    <tr>
                        <td>
                            <a class="btn btn-outline-danger btn-sm" href="/admin/earlyAdmissionHistory/create?admissionId=${admission.admissionId}">한국사 정보 입력</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </c:when>
        <%--// 수시 한국사 정보 없음 --%>

        <%-- 수시 한국사 정보 있음 --%>
        <c:otherwise>
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
            <div class="mb-5">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a href="/admin/earlyAdmissionHistory/${earlyAdmissionHistory.earlyAdmissionHistoryId}/update" class="btn btn-outline-warning">수시 입시 한국사 정보 수정</a>
            </div>                                               
        </c:otherwise>
        <%--// 수시 한국사 정보 있음 --%>
    </c:choose>
</c:if>