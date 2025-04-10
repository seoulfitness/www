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
                            <div class="col-lg-12">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        내신 기록 기간 목록
                                    </div>
                                    <div class="card-body">
                                        <%-- 검색 폼, 등록 버튼 --%>
                                        <div class="d-flex justify-content-between mb-3">                
                                            <form id="searchForm" method="get" class="d-flex">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" name="keyword" value="" placeholder="검색어를 입력하세요">                        
                                                    <button class="btn btn-secondary" type="submit" >검색</button>
                                                </div>
                                            </form>
                                            <a class="btn btn-primary" href="/admin/internalRecordPeriods/create">내신 기록 기간 등록</a>
                                        </div>
                                        <%--// 검색 폼, 등록 버튼 --%>

                                        <%-- 지점 목록 --%>
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th class="col-2 text-center">제목</th>
                                                        <th class="col-2 text-center">기록 등록 시작일</th>
                                                        <th class="col-2 text-center">기록 등록 종료일</th>
                                                        <th class="col-2 text-center">지점 회원 권한</th>
                                                        <th class="col-2 text-center">일반 회원 권한</th>
                                                        <th class="col-1 text-center">관리</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${internalRecordPeriods}" var="internalRecordPeriod">
                                                        <tr>
                                                            <td class="align-middle text-center">${internalRecordPeriod.title}</td>
                                                            <td class="align-middle text-center"><fmt:formatDate value="${internalRecordPeriod.startDate}" pattern="yyyy-MM-dd"/></td>
                                                            <td class="align-middle text-center"><fmt:formatDate value="${internalRecordPeriod.endDate}" pattern="yyyy-MM-dd"/></td>
                                                            <td class="align-middle text-center">
                                                                <c:choose>
                                                                    <c:when test="${internalRecordPeriod.grantBranchUser == 'Y'}"><span class="btn btn-outline-success btn-sm">있음</span></c:when>
                                                                    <c:otherwise><span class="btn btn-outline-danger btn-sm">없음</span></c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="align-middle text-center">
                                                                <c:choose>
                                                                    <c:when test="${internalRecordPeriod.grantNormalUser == 'Y'}"><span class="btn btn-outline-success btn-sm"></span>있음</c:when>
                                                                    <c:otherwise><span class="btn btn-outline-danger btn-sm">없음</span></c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="align-middle text-center">
                                                                <a href="/admin/internalRecordPeriods/${internalRecordPeriod.internalRecordPeriodId}" class="btn btn-outline-primary btn-sm">보기</a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <%--// 지점 목록 --%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../../base/footer.jsp" %>
            </div>
        </div>
        <%@ include file="../../base/script.jsp" %>
    </body>
</html>
