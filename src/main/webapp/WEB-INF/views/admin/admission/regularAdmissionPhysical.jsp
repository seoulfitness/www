<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <%-- 정시 입시 실기 정보 없음 --%>
    <c:when test="${empty regualrAdmissionPhysical}">
        <div class="card mb-4" id="regualrAdmissionPhysical">
            <div class="card-header">
                정시 입시 실기 정보
            </div>
            <div class="card-body">
                <div class="alert alert-danger">
                    정시 입시 실기 정보가 없습니다. 정시 입시 실기 정보를 입력해주세요.
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a class="btn btn-outline-danger" href="/admin/regualrAdmissionPhysical/create?admissionId=${admission.admissionId}">실기 정보 입력</a>
            </div>
        </div>
    </c:when>
    <%--// 정시 입시 실기 정보 없음 --%>

    <%-- 정시 입시 실기 정보 있음 --%>
    <c:otherwise>
        <div class="card mb-4" id="regualrAdmissionPhysical">
            <div class="card-header">
                정시 입시 실기 정보
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover mb-3">
                        <tbody>
                            <tr>
                                <th class="align-middle col-2">교과목</th>
                                <th class="align-middle col-4">교과목명</th>
                                <th class="align-middle col-2">평가 방법</th>
                                <th class="align-middle col-2">배점(남)</th>
                                <th class="align-middle col-2">배점(여)</th>
                            </tr>
                            <c:forEach var="i" begin="1" end="10">
                                <c:if test="${regualrAdmissionPhysical['useSubject'.concat(i)] == 'Y'}">
                                    <tr>
                                        <th class="align-middle col-2">교과목 ${i}</th>
                                        <td class="align-middle col-4">${regualrAdmissionPhysical['subject'.concat(i).concat('Name')]}</td>
                                        <td class="align-middle col-2">
                                            <c:if test="${regualrAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 1}">
                                                절대평가
                                            </c:if>
                                            <c:if test="${regualrAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 2}">
                                                상대평가
                                            </c:if>
                                        </td>
                                        <td class="align-middle col-2">
                                            <a href="#" class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#regualrAdmissionPhysicalManScoreModal${i}">점수 입력 필요</a>
                                        </td>
                                        <td class="align-middle col-2">
                                            <a href="#" class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#regualrAdmissionPhysicalWomanScoreModal${i}">점수 입력 필요</a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>      
                            <tr>
                                <th class="align-middle col-2" colspan="4">메모</th>
                                <td class="align-middle col-10">
                                    <c:if test="${not empty regualrAdmissionPhysical.regualrAdmissionPhysicalMemo}">
                                        ${regualrAdmissionPhysical.regualrAdmissionPhysicalMemo}
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
                                <td class="align-middle col-10"><fmt:formatDate value="${regualrAdmissionPhysical.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">등록한 사람</th>
                                <td class="align-middle col-10">${regualrAdmissionPhysical.createdUserName} (${regualrAdmissionPhysical.createdBy}, ${regualrAdmissionPhysical.createdUserPhone})</td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정일시</th>
                                <td class="align-middle col-10"><fmt:formatDate value="${regualrAdmissionPhysical.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정한 사람</th>
                                <td class="align-middle col-10">${regualrAdmissionPhysical.updatedUserName} (${regualrAdmissionPhysical.updatedBy}, ${regualrAdmissionPhysical.updatedUserPhone})</td>
                            </tr>
                        </tbody>
                    </table>
                    <%--// 등록일시, 수정일시 --%>
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a href="/admin/regualrAdmissionPhysical/${regualrAdmissionPhysical.regualrAdmissionPhysicalId}/update" class="btn btn-outline-warning">정시 입시 실기 정보 수정</a>
            </div>
        </div>
    </c:otherwise>
    <%--// 정시 입시 실기 정보 있음 --%>
</c:choose>
