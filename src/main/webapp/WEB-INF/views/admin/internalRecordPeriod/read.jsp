<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <%@ include file="../../base/head.jsp" %>
    <body class="nav-fixed">
        <%@ include file="../../base/nav.jsp" %>
        <div id="layoutSidenav">
            <%@ include file="../../base/layoutSidenav_nav.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <%@ include file="../../base/simple_header.jsp" %>
                    <%-- Main page content--%>
                    <div class="container-fluid px-4 mt-4">
                        <%-- 메시지 --%>
                        <%@ include file="../../base/message.jsp" %>
                        <%--// 메시지 --%>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        내신 기록 기간 정보
                                    </div>
                                    <div class="card-body">                                        
                                        <div class="table-responsive">
                                            <%-- 내신 기록 기간 정보 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle col-2">제목</th>
                                                        <td class="align-middle col-10">${internalRecordPeriod.title}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">등록 시작일</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${internalRecordPeriod.startDate}" pattern="yyyy-MM-dd"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">등록 종료일</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${internalRecordPeriod.endDate}" pattern="yyyy-MM-dd"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">지점 회원 권한</th>
                                                        <td class="align-middle col-10">
                                                            <c:choose>
                                                                <c:when test="${internalRecordPeriod.grantBranchUser == 'Y'}"><span class="btn btn-success btn-sm">있음</span></c:when>
                                                                <c:otherwise><span class="btn btn-outline-danger btn-sm">없음</span></c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">일반 회원 권한</th>
                                                        <td class="align-middle col-10">
                                                            <c:choose>
                                                                <c:when test="${internalRecordPeriod.grantNormalUser == 'Y'}"><span class="btn btn-success btn-sm">있음</span></c:when>
                                                                <c:otherwise><span class="btn btn-outline-danger btn-sm">없음</span></c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">메모</th>
                                                        <td class="align-middle col-10">${internalRecordPeriod.memo}</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 내신 기록 기간 정보 --%>

                                            <%-- 등록일시, 수정일시 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle col-2">등록일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${internalRecordPeriod.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">등록한 사람</th>
                                                        <td class="align-middle col-10">${internalRecordPeriod.createdUserName} (${internalRecordPeriod.createdBy}, ${internalRecordPeriod.createdUserPhone})</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${internalRecordPeriod.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정한 사람</th>
                                                        <td class="align-middle col-10">${internalRecordPeriod.updatedUserName} (${internalRecordPeriod.updatedBy}, ${internalRecordPeriod.updatedUserPhone})</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 등록일시, 수정일시 --%>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <a href="/admin/internalRecordPeriods" class="btn btn-primary">내신 기록 기간 목록</a>
                                        <a href="/admin/internalRecordPeriods/${internalRecordPeriod.internalRecordPeriodId}/update" class="btn btn-outline-warning">내신 기록 기간 수정</a>
                                        <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">내신 기록 기간 삭제</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../../base/footer.jsp" %>
            </div>
        </div>

        <%-- 삭제 모달 --%>
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="deleteForm" action="/admin/internalRecordPeriods/${internalRecordPeriod.internalRecordPeriodId}/delete" method="POST">
                        <%-- modal-header --%>
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-danger" id="deleteModalModalLabel">
                                <strong>내신 기록 기간 삭제</strong>
                            </h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <%--// modal-header --%>

                        <%-- modal-body --%>
                        <div class="modal-body">
                            <div class="mb-3">
                                <p class="text-danger">삭제된 데이터는 복구할 수 없습니다.</p>
                                <p>정말 삭제하시겠습니까?</p>
                            </div>
                        </div>
                        <%--// modal-body --%>

                        <%-- modal-footer --%>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-outline-danger">삭제</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                        </div>
                        <%--// modal-footer --%>
                    </form>
                </div>
            </div>
        </div>
        <%-- 삭제 모달 --%>

        <%@ include file="../../base/script.jsp" %>
    </body>
</html>
