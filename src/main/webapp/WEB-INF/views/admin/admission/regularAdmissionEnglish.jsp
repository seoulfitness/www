<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <%-- 정시 입시 영어 정보 없음 --%>
    <c:when test="${empty regularAdmissionEnglish}">
        <div class="card mb-4" id="regularAdmissionEnglish">
            <div class="card-header">
                정시 입시 영어 정보
            </div>
            <div class="card-body">
                <div class="alert alert-warning">
                    정시 입시 영어 정보가 없습니다. 정시 입시 영어 정보를 입력해주세요.
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a class="btn btn-outline-danger" href="/admin/regularAdmissionEnglish/create?admissionId=${admission.admissionId}">영어 정보 입력</a>
            </div>
        </div>
    </c:when>
    <%--// 정시 입시 영어 정보 없음 --%>

    <%-- 정시 입시 영어 정보 있음 --%>
    <c:otherwise>
        <div class="card mb-4" id="regularAdmissionEnglish">
            <div class="card-header">
                정시 입시 영어 정보
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover mb-3">
                        <tbody>
                            <tr>
                                <th class="align-middle col-2">영어 등급 1 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionEnglish.subject20ReflectedGrade1}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">영어 등급 2 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionEnglish.subject20ReflectedGrade2}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">영어 등급 3 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionEnglish.subject20ReflectedGrade3}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">영어 등급 4 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionEnglish.subject20ReflectedGrade4}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">영어 등급 5 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionEnglish.subject20ReflectedGrade5}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">영어 등급 6 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionEnglish.subject20ReflectedGrade6}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">영어 등급 7 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionEnglish.subject20ReflectedGrade7}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">영어 등급 8 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionEnglish.subject20ReflectedGrade8}점
                                </td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">영어 등급 9 반영 점수</th>
                                <td class="align-middle col-10">
                                    ${regularAdmissionEnglish.subject20ReflectedGrade9}점
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
                                <td class="align-middle col-10"><fmt:formatDate value="${regularAdmissionEnglish.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">등록한 사람</th>
                                <td class="align-middle col-10">${regularAdmissionEnglish.createdUserName} (${regularAdmissionEnglish.createdBy}, ${regularAdmissionEnglish.createdUserPhone})</td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정일시</th>
                                <td class="align-middle col-10"><fmt:formatDate value="${regularAdmissionEnglish.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정한 사람</th>
                                <td class="align-middle col-10">${regularAdmissionEnglish.updatedUserName} (${regularAdmissionEnglish.updatedBy}, ${regularAdmissionEnglish.updatedUserPhone})</td>
                            </tr>
                        </tbody>
                    </table>
                    <%--// 등록일시, 수정일시 --%>
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a href="/admin/regularAdmissionEnglish/${regularAdmissionEnglish.regularAdmissionEnglishId}/update" class="btn btn-outline-warning">정시 입시 영어 정보 수정</a>
            </div>
        </div>
    </c:otherwise>
    <%--// 정시 입시 영어 정보 있음 --%>
</c:choose>
