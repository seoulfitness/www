<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                                        지점 목록
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
                                            <a class="btn btn-primary" href="/admin/branches/create">지점 등록</a>
                                        </div>
                                        <%--// 검색 폼, 등록 버튼 --%>

                                        <%-- 지점 목록 --%>
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th class="col-2 text-center">지점명</th>
                                                        <th class="col-4 text-center">주소</th>
                                                        <th class="col-2 text-center">전화번호</th>
                                                        <th class="col-3 text-center">지점 관리자</th>
                                                        <th class="col-1 text-center">관리</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${branches}" var="branch">
                                                        <tr>
                                                            <td class="align-middle text-center">${branch.branchName}</td>
                                                            <td class="align-middle">${branch.branchAddress}</td>
                                                            <td class="align-middle text-center">${branch.branchPhone}</td>
                                                            <td class="align-middle">
                                                                <c:forEach items="${branch.branchManagers}" var="branchManager">
                                                                    <div class="mb-2">${branchManager.userName} (${branchManager.userId}, ${branchManager.userPhone})</div>
                                                                </c:forEach>
                                                            </td>
                                                            <td class="align-middle text-center">
                                                                <a href="/admin/branches/${branch.branchId}" class="btn btn-outline-primary btn-sm">보기</a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <%--// 지점 목록 --%>

                                        <%-- 페이지네이션 --%>
                                        <c:if test="${pagination.totalCount > 0}">
                                            <nav aria-label="Page navigation" class="mt-4">
                                                <ul class="pagination justify-content-center">
                                                    <%-- 이전 페이지 --%>
                                                    <c:if test="${pagination.currentPage > 1}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="/admin/branches?page=${pagination.currentPage - 1}&keyword=${keyword}" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                            </a>
                                                        </li>
                                                    </c:if>

                                                    <%-- 페이지 번호 --%>
                                                    <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="page">
                                                        <li class="page-item ${page == pagination.currentPage ? 'active' : ''}">
                                                            <a class="page-link" href="/admin/branches?page=${page}&keyword=${keyword}">${page}</a>
                                                        </li>
                                                    </c:forEach>

                                                    <%-- 다음 페이지 --%>
                                                    <c:if test="${pagination.currentPage < pagination.totalPages}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="/admin/branches?page=${pagination.currentPage + 1}&keyword=${keyword}" aria-label="Next">
                                                                <span aria-hidden="true">&raquo;</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </nav>
                                        </c:if>
                                        <%--// 페이지네이션 --%>
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
