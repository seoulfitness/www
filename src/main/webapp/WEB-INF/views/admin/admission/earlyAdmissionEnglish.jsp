<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <%-- 수시 입시 영어 정보 없음 --%>
    <c:when test="${empty earlyAdmissionEnglish}">
        <div class="card mb-4" id="earlyAdmissionEnglish">
            <div class="card-header">
                수시 입시 영어 정보
            </div>
            <div class="card-body">
                <div class="alert alert-danger">
                    수시 입시 영어 정보가 없습니다. 수시 입시 영어 정보를 입력해주세요.
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a class="btn btn-outline-danger" href="/admin/earlyAdmissionEnglish/create?admissionId=${admission.admissionId}">영어 정보 입력</a>
            </div>
        </div>
    </c:when>
    <%--// 수시 입시 영어 정보 없음 --%>

    <%-- 수시 입시 영어 정보 있음 --%>
    <c:otherwise>
        <div class="card mb-4" id="earlyAdmissionEnglish">
            <div class="card-header">
                수시 입시 영어 정보
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover mb-3">
                        <tbody>
                            <c:forEach var="i" begin="1" end="9">
                                <tr>
                                    <th class="align-middle col-2">영어 등급 ${i} 반영 점수</th>
                                    <td class="align-middle col-10">
                                        ${earlyAdmissionEnglish['subject20ReflectedGrade'.concat(i)]}점
                                    </td>
                                </tr>
                            </c:forEach>
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
                    <%-- 등록일시, 수정일시 --%>
                    <table class="table table-bordered table-hover mb-3">
                        <tbody>
                            <tr class="border-top">
                                <th class="align-middle col-2">등록일시</th>
                                <td class="align-middle col-10"><fmt:formatDate value="${earlyAdmissionEnglish.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">등록한 사람</th>
                                <td class="align-middle col-10">${earlyAdmissionEnglish.createdUserName} (${earlyAdmissionEnglish.createdBy}, ${earlyAdmissionEnglish.createdUserPhone})</td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정일시</th>
                                <td class="align-middle col-10"><fmt:formatDate value="${earlyAdmissionEnglish.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정한 사람</th>
                                <td class="align-middle col-10">${earlyAdmissionEnglish.updatedUserName} (${earlyAdmissionEnglish.updatedBy}, ${earlyAdmissionEnglish.updatedUserPhone})</td>
                            </tr>
                        </tbody>
                    </table>
                    <%--// 등록일시, 수정일시 --%>
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a href="/admin/earlyAdmissionEnglish/${earlyAdmissionEnglish.earlyAdmissionEnglishId}/update" class="btn btn-outline-warning">수시 입시 영어 정보 수정</a>
            </div>
        </div>
    </c:otherwise>
    <%--// 수시 입시 영어 정보 있음 --%>
</c:choose>
