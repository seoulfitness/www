<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <%-- 수시 입시 한국사 정보 없음 --%>
    <c:when test="${empty earlyAdmissionHistory}">
        <div class="card mb-4" id="earlyAdmissionHistory">
            <div class="card-header">
                수시 입시 한국사 정보
            </div>
            <div class="card-body">
                <div class="alert alert-danger">
                    수시 입시 한국사 정보가 없습니다. 수시 입시 한국사 정보를 입력해주세요.
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a class="btn btn-outline-danger" href="/admin/earlyAdmissionHistory/create?admissionId=${admission.admissionId}">한국사 정보 입력</a>
            </div>
        </div>
    </c:when>
    <%--// 수시 입시 한국사 정보 없음 --%>

    <%-- 수시 입시 한국사 정보 있음 --%>
    <c:otherwise>
        <div class="card mb-4" id="earlyAdmissionHistory">
            <div class="card-header">
                수시 입시 한국사 정보
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover mb-3">
                        <tbody>
                            <c:forEach var="i" begin="1" end="9">
                                <tr>
                                    <th class="align-middle col-2">한국사 등급 ${i} 반영 점수</th>
                                    <td class="align-middle col-10">
                                        ${earlyAdmissionHistory['subject21ReflectedGrade'.concat(i)]}점
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th class="align-middle col-2">메모</th>
                                <td class="align-middle col-10">
                                    <c:if test="${not empty earlyAdmissionHistory.earlyAdmissionHistoryMemo}">
                                        ${earlyAdmissionHistory.earlyAdmissionHistoryMemo}
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
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a href="/admin/earlyAdmissionHistory/${earlyAdmissionHistory.earlyAdmissionHistoryId}/update" class="btn btn-outline-warning">수시 입시 한국사 정보 수정</a>
            </div>
        </div>
    </c:otherwise>
    <%--// 수시 입시 한국사 정보 있음 --%>
</c:choose>
