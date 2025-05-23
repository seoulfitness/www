<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <%-- 수시 입시 수능 정보 없음 --%>
    <c:when test="${empty earlyAdmissionCsat}">
        <div class="card mb-4" id="earlyAdmissionCsat">
            <div class="card-header">
                수시 입시 수능 정보
            </div>
            <div class="card-body">
                <div class="alert alert-danger">
                    수시 입시 수능 정보가 없습니다. 수시 입시 수능 정보를 입력해주세요.
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a class="btn btn-outline-danger" href="/admin/earlyAdmissionCsat/create?admissionId=${admission.admissionId}">수능 정보 입력</a>
            </div>
        </div>
    </c:when>
    <%--// 수시 입시 수능 정보 없음 --%>

    <%-- 수시 입시 수능 정보 있음 --%>
    <c:otherwise>
        <div class="card mb-4" id="earlyAdmissionCsat">
            <div class="card-header">
                수시 입시 수능 정보
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover mb-3">
                        <tbody>
                            <tr>
                                <th>점수 반영 구분</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${earlyAdmissionCsat.scoreType == '1'}">표준점수</c:when>
                                        <c:when test="${earlyAdmissionCsat.scoreType == '2'}">백분위</c:when>
                                        <c:when test="${earlyAdmissionCsat.scoreType == '3'}">기타방법</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th>탐구 교과목 반영 구분</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${earlyAdmissionCsat.scoreType2 == '1'}">동일 과목 허용</c:when>
                                        <c:when test="${earlyAdmissionCsat.scoreType2 == '2'}">동일 과목 허용 안함</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">국어 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject1ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수학 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject2ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">사회문화 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject3ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">생활과윤리 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject4ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">윤리와사상 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject5ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">정치와법 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject6ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">경제 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject7ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">한국지리 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject8ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">세계지리 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject9ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">세계사 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject10ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">동아시아사 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject11ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">화학1 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject12ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">화학2 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject13ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">생명과학1 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject14ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">생명과학2 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject15ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">물리1 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject16ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">물리2 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject17ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">지구과학1 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject18ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">지구과학2 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${earlyAdmissionCsat.subject19ReflectedScore}점
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

                    <%-- 등록일시, 수정일시 --%>
                    <table class="table table-bordered table-hover mb-3">
                        <tbody>
                            <tr class="border-top">
                                <th class="align-middle col-2">등록일시</th>
                                <td class="align-middle col-10"><fmt:formatDate value="${earlyAdmissionCsat.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">등록한 사람</th>
                                <td class="align-middle col-10">${earlyAdmissionCsat.createdUserName} (${earlyAdmissionCsat.createdBy}, ${earlyAdmissionCsat.createdUserPhone})</td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정일시</th>
                                <td class="align-middle col-10"><fmt:formatDate value="${earlyAdmissionCsat.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정한 사람</th>
                                <td class="align-middle col-10">${earlyAdmissionCsat.updatedUserName} (${earlyAdmissionCsat.updatedBy}, ${earlyAdmissionCsat.updatedUserPhone})</td>
                            </tr>
                        </tbody>
                    </table>
                    <%--// 등록일시, 수정일시 --%>
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a href="/admin/earlyAdmissionCsat/${earlyAdmissionCsat.earlyAdmissionCsatId}/update" class="btn btn-outline-warning">수시 입시 수능 정보 수정</a>
            </div>
        </div>
    </c:otherwise>
    <%--// 수시 입시 수능 정보 있음 --%>
</c:choose>
