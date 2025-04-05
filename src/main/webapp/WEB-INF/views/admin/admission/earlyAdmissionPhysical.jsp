<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <%-- 수시 입시 실기 정보 없음 --%>
    <c:when test="${empty earlyAdmissionPhysical}">
        <div class="card mb-4" id="earlyAdmissionPhysical">
            <div class="card-header">
                수시 입시 실기 정보
            </div>
            <div class="card-body">
                <div class="alert alert-danger">
                    수시 입시 실기 정보가 없습니다. 수시 입시 실기 정보를 입력해주세요.
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a class="btn btn-outline-danger" href="/admin/earlyAdmissionPhysical/create?admissionId=${admission.admissionId}">실기 정보 입력</a>
            </div>
        </div>
    </c:when>
    <%--// 수시 입시 실기 정보 없음 --%>

    <%-- 수시 입시 실기 정보 있음 --%>
    <c:otherwise>
        <div class="card mb-4" id="earlyAdmissionPhysical">
            <div class="card-header">
                수시 입시 실기 정보
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
                                <c:if test="${earlyAdmissionPhysical['useSubject'.concat(i)] == 'Y'}">
                                    <tr>
                                        <th class="align-middle col-2">교과목 ${i}</th>
                                        <td class="align-middle col-4">${earlyAdmissionPhysical['subject'.concat(i).concat('Name')]}</td>
                                        <td class="align-middle col-2">
                                            <c:if test="${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 1}">
                                                절대평가
                                            </c:if>
                                            <c:if test="${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 2}">
                                                상대평가
                                            </c:if>
                                        </td>
                                        <td class="align-middle col-2">
                                            <%-- 남자 절대평가 --%>
                                            <c:if test="${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 1}">
                                                <%-- 공통 버튼 속성 설정 --%>
                                                <c:set var="buttonData">
                                                    data-id="${i}"
                                                    data-admission-id="${admission.admissionId}"
                                                    data-early-admission-physical-id="${earlyAdmissionPhysical.earlyAdmissionPhysicalId}"
                                                    data-subject-id="${earlyAdmissionPhysical['subject'.concat(i).concat('Id')]}"
                                                    data-subject-name="${earlyAdmissionPhysical['subject'.concat(i).concat('Name')]}"
                                                    data-gender="man"
                                                </c:set>
                                                
                                                <%-- 절대평가 데이터 존재 여부 확인 --%>
                                                <c:set var="earlyAdmissionPhysicalAbsoluteExists" value="false"/>
                                                <c:set var="earlyAdmissionPhysicalAbsoluteId" value=""/>
                                                
                                                <c:forEach var="earlyAdmissionPhysicalManAbsolute" items="${earlyAdmissionPhysicalManAbsoluteList}">
                                                    <c:if test="${not earlyAdmissionPhysicalAbsoluteExists}">
                                                        <c:if test="${earlyAdmissionPhysicalManAbsolute.earlyAdmissionPhysicalSubjectId == earlyAdmissionPhysical['subject'.concat(i).concat('Id')]}">
                                                            <c:set var="earlyAdmissionPhysicalAbsoluteExists" value="true"/>
                                                            <c:set var="earlyAdmissionPhysicalAbsoluteId" value="${earlyAdmissionPhysicalManAbsolute.earlyAdmissionPhysicalAbsoluteId}"/>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                                
                                                <%-- 버튼 렌더링 --%>
                                                <button class="btn btn-sm btn-absolute-score btn-man-absolute-score ${earlyAdmissionPhysicalAbsoluteExists ? 'btn-primary' : 'btn-outline-danger'}"
                                                    ${buttonData}
                                                    data-early-admission-physical-absolute-id="${earlyAdmissionPhysicalAbsoluteId}"
                                                    data-action="${earlyAdmissionPhysicalAbsoluteExists ? 'update' : 'create'}">
                                                    ${earlyAdmissionPhysicalAbsoluteExists ? '배점 보기' : '점수 입력 필요'}
                                                </button>
                                            </c:if>
                                            <%--// 남자 절대평가 --%>
                                            <%-- 남자 상대평가 --%>
                                            <c:if test="${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 2}">
                                                <button class="btn btn-outline-danger btn-sm btn-relative-score btn-man-relative-score" 
                                                    data-id="${i}" 
                                                    data-subject-name="${earlyAdmissionPhysical['subject'.concat(i).concat('Name')]}" 
                                                    data-subject-id="${earlyAdmissionPhysical['subject'.concat(i).concat('Id')]}" 
                                                    data-gender="man" 
                                                    data-action="create">점수 입력 필요</button>
                                                </c:if>
                                            <%--// 남자 상대평가 --%>
                                        </td>
                                        <td class="align-middle col-2">
                                            <%-- 여자 절대평가 --%>
                                            <c:if test="${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 1}">
                                                <button class="btn btn-outline-danger btn-sm btn-absolute-score btn-woman-absolute-score" 
                                                    data-id="${i}" 
                                                    data-subject-name="${earlyAdmissionPhysical['subject'.concat(i).concat('Name')]}" 
                                                    data-subject-id="${earlyAdmissionPhysical['subject'.concat(i).concat('Id')]}" 
                                                    data-gender="woman" 
                                                    data-action="create">점수 입력 필요</button>
                                            </c:if>
                                            <%--// 여자 절대평가 --%>
                                            <%-- 여자 상대평가 --%>
                                            <c:if test="${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 2}">
                                                <button class="btn btn-outline-danger btn-sm btn-relative-score btn-woman-relative-score" 
                                                    data-id="${i}" 
                                                    data-subject-name="${earlyAdmissionPhysical['subject'.concat(i).concat('Name')]}" 
                                                    data-subject-id="${earlyAdmissionPhysical['subject'.concat(i).concat('Id')]}" 
                                                    data-gender="woman" 
                                                    data-action="create">점수 입력 필요</button>
                                            </c:if> 
                                            <%--// 여자 상대평가 --%>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>  
                            <tr>
                                <th class="align-middle col-2" colspan="4">메모</th>
                                <td class="align-middle col-10">
                                    <c:if test="${not empty earlyAdmissionPhysical.earlyAdmissionPhysicalMemo}">
                                        ${earlyAdmissionPhysical.earlyAdmissionPhysicalMemo}
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
                                <td class="align-middle col-10"><fmt:formatDate value="${earlyAdmissionPhysical.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">등록한 사람</th>
                                <td class="align-middle col-10">${earlyAdmissionPhysical.createdUserName} (${earlyAdmissionPhysical.createdBy}, ${earlyAdmissionPhysical.createdUserPhone})</td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정일시</th>
                                <td class="align-middle col-10"><fmt:formatDate value="${earlyAdmissionPhysical.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <th class="align-middle col-2">수정한 사람</th>
                                <td class="align-middle col-10">${earlyAdmissionPhysical.updatedUserName} (${earlyAdmissionPhysical.updatedBy}, ${earlyAdmissionPhysical.updatedUserPhone})</td>
                            </tr>
                        </tbody>
                    </table>
                    <%--// 등록일시, 수정일시 --%>
                </div>
            </div>
            <div class="card-footer">
                <a href="/admin/admissions" class="btn btn-primary">입시 요강 목록</a>
                <a href="/admin/earlyAdmissionPhysical/${earlyAdmissionPhysical.earlyAdmissionPhysicalId}/update" class="btn btn-outline-warning">수시 입시 실기 정보 수정</a>
            </div>
        </div>
    </c:otherwise>
    <%--// 수시 입시 실기 정보 있음 --%>
</c:choose>

