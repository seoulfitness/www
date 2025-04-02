<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <%-- 정시 입시 한국사 정보 없음 --%>
    <c:when test="${empty regularAdmissionHistory}">
        <div class="card mb-4" id="regularAdmissionHistory">
            <div class="card-header">
                정시 입시 한국사 정보
            </div>
            <div class="card-body">
                <div class="alert alert-danger">
                    정시 입시 한국사 정보가 없습니다. 정시 입시 한국사 정보를 입력해주세요.
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a class="btn btn-outline-danger" href="/admin/regularAdmissionHistory/create?admissionId=${admission.admissionId}">한국사 정보 입력</a>
            </div>
        </div>
    </c:when>
    <%--// 정시 입시 한국사 정보 없음 --%>

    <%-- 정시 입시 한국사 정보 있음 --%>
    <c:otherwise>
        <div class="card mb-4" id="regularAdmissionHistory">
            <div class="card-header">
                정시 입시 한국사 정보
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover mb-3">
                        <tbody>
                            <c:forEach var="i" begin="1" end="9">
                                <tr>
                                    <th class="align-middle col-2">한국사 등급 ${i} 반영 점수</th>
                                    <td class="align-middle col-10">
                                        ${regularAdmissionHistory['subject21ReflectedGrade'.concat(i)]}점
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th class="align-middle col-2">메모</th>
                                <td class="align-middle col-10">
                                    <c:if test="${not empty regularAdmissionHistory.regularAdmissionHistoryMemo}">
                                        ${regularAdmissionHistory.regularAdmissionHistoryMemo}
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
                                <td class="align-middle col-10"><fmt:formatDate value="${regularAdmissionHistory.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">등록한 사람</th>
                                <td class="align-middle col-10">${regularAdmissionHistory.createdUserName} (${regularAdmissionHistory.createdBy}, ${regularAdmissionHistory.createdUserPhone})</td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정일시</th>
                                <td class="align-middle col-10"><fmt:formatDate value="${regularAdmissionHistory.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정한 사람</th>
                                <td class="align-middle col-10">${regularAdmissionHistory.updatedUserName} (${regularAdmissionHistory.updatedBy}, ${regularAdmissionHistory.updatedUserPhone})</td>
                            </tr>
                        </tbody>
                    </table>
                    <%--// 등록일시, 수정일시 --%>
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a href="/admin/regularAdmissionHistory/${regularAdmissionHistory.regularAdmissionHistoryId}/update" class="btn btn-outline-warning">정시 입시 한국사 정보 수정</a>
            </div>
        </div>
    </c:otherwise>
    <%--// 정시 입시 한국사 정보 있음 --%>
</c:choose>
