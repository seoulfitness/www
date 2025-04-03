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
                                            <c:if test="${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 1}">
                                                <a href="#" class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#earlyAdmissionPhysicalManAbsoluteScoreModal${i}">점수 입력 필요</a>
                                            </c:if>
                                            <c:if test="${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 2}">
                                                <a href="#" class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#earlyAdmissionPhysicalManRelativeScoreModal${i}">점수 입력 필요</a>
                                            </c:if>
                                        </td>
                                        <td class="align-middle col-2">
                                            <c:if test="${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 1}">
                                                <a href="#" class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#earlyAdmissionPhysicalWomanAbsoluteScoreModal${i}">점수 입력 필요</a>
                                            </c:if>
                                            <c:if test="${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == 2}">
                                                <a href="#" class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#earlyAdmissionPhysicalWomanRelativeScoreModal${i}">점수 입력 필요</a>
                                            </c:if>
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

<%-- 절대평가 점수 입력 모달 --%>
<c:forEach var="i" begin="1" end="10">
    <c:if test="${earlyAdmissionPhysical['useSubject'.concat(i)] == 'Y'}">
        <div class="modal fade modal-lg" id="earlyAdmissionPhysicalManAbsoluteScoreModal${i}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">${earlyAdmissionPhysical['subject'.concat(i).concat('Name')]} 절대평가 점수 입력</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- 안내 -->
                        <div class="mb-3">
                            <div class="alert alert-info">
                                <div class="mb-1">등급별 점수 및 기록은 등급 사용인 경우에만 입력 가능합니다.</div>
                                <div class="mb-1">범위가 없는 단일 기록일 경우 최소 기록과 최대 기록을 동일하게 입력합니다.</div>
                            </div>
                        </div>
                        <!--// 안내 -->
                        <form id="earlyAdmissionPhysicalManAbsoluteScoreForm${i}">
                            <c:forEach var="j" begin="1" end="40">
                                <div class="mb-3 row">    
                                    <div class="col-md-3">
                                        <label class="small mb-1" for="useGrade${j}">${j}등급 사용 여부 <span class="text-danger">*</span></label>
                                        <select class="form-control" id="useGrade${j}" name="useGrade${j}">
                                            <option value="Y" selected>사용</option>
                                            <option value="N">사용안함</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <label class="small mb-1" for="grade${j}Score">${j}등급 점수 <span class="text-danger">*</span></label>
                                        <input class="form-control" id="grade${j}Score" name="grade${j}Score" type="text" value="" />
                                    </div>  
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="grade${j}RecordMin">${j}등급 기록<span class="text-danger">*</span></label>
                                        <div class="input-group">
                                            <input class="form-control col-auto" id="grade${j}RecordMin" name="grade${j}RecordMin" type="text" value="" />
                                            <span class="input-group-text">~</span>
                                            <input class="form-control col-auto" id="grade${j}RecordMax" name="grade${j}RecordMax" type="text" value="" />
                                        </div>
                                    </div>        
                                </div>  
                        </c:forEach>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</c:forEach>
<%--// 절대평가 점수 입력 모달 --%>
