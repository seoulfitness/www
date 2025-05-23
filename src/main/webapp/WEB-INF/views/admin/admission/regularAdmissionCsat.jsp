<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <%-- 정시 입시 수능 정보 없음 --%>
    <c:when test="${empty regularAdmissionCsat}">
        <div class="card mb-4" id="regularAdmissionCsat">
            <div class="card-header">
                정시 입시 수능 정보
            </div>
            <div class="card-body">
                <div class="alert alert-danger">
                    정시 입시 수능 정보가 없습니다. 정시 입시 수능 정보를 입력해주세요.
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a class="btn btn-outline-danger" href="/admin/regularAdmissionCsat/create?admissionId=${admission.admissionId}">수능 정보 입력</a>
            </div>
        </div>
    </c:when>
    <%--// 정시 입시 수능 정보 없음 --%>

    <%-- 정시 입시 수능 정보 있음 --%>
    <c:otherwise>
        <div class="card mb-4" id="regularAdmissionCsat">
            <div class="card-header">
                정시 입시 수능 정보
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover mb-3">
                        <tbody>
                            <tr>
                                <th>점수 반영 구분</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${regularAdmissionCsat.scoreType == '1'}">표준점수</c:when>
                                        <c:when test="${regularAdmissionCsat.scoreType == '2'}">백분위</c:when>
                                        <c:when test="${regularAdmissionCsat.scoreType == '3'}">기타방법</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th>탐구 교과목 반영 구분</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${regularAdmissionCsat.scoreType2 == '1'}">동일 과목 허용</c:when>
                                        <c:when test="${regularAdmissionCsat.scoreType2 == '2'}">동일 과목 허용 안함</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">국어 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject1ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수학 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject2ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">사회문화 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject3ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">생활과윤리 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject4ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">윤리와사상 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject5ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">정치와법 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject6ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">경제 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject7ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">한국지리 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject8ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">세계지리 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject9ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">세계사 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject10ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">동아시아사 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject11ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">화학1 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject12ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">화학2 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject13ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">생명과학1 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject14ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">생명과학2 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject15ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">물리1 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject16ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">물리2 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject17ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">지구과학1 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject18ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">지구과학2 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionCsat.subject19ReflectedScore}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">메모</th>
                                <td class="align-middle col-10">
                                    <c:if test="${not empty regularAdmissionCsat.regularAdmissionCsatMemo}">
                                        ${regularAdmissionCsat.regularAdmissionCsatMemo}
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
                                <td class="align-middle col-10"><fmt:formatDate value="${regularAdmissionCsat.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">등록한 사람</th>
                                <td class="align-middle col-10">${regularAdmissionCsat.createdUserName} (${regularAdmissionCsat.createdBy}, ${regularAdmissionCsat.createdUserPhone})</td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정일시</th>
                                <td class="align-middle col-10"><fmt:formatDate value="${regularAdmissionCsat.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정한 사람</th>
                                <td class="align-middle col-10">${regularAdmissionCsat.updatedUserName} (${regularAdmissionCsat.updatedBy}, ${regularAdmissionCsat.updatedUserPhone})</td>
                            </tr>
                        </tbody>
                    </table>
                    <%--// 등록일시, 수정일시 --%>
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a href="/admin/regularAdmissionCsat/${regularAdmissionCsat.regularAdmissionCsatId}/update" class="btn btn-outline-warning">정시 입시 수능 정보 수정</a>
            </div>
        </div>
    </c:otherwise>
    <%--// 정시 입시 수능 정보 있음 --%>
</c:choose>
