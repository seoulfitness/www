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
                                        구/군 목록 (${pagination.totalCount}개)
                                    </div>
                                    <div class="card-body">
                                        <%-- 검색 폼, 등록 버튼 --%>
                                        <div class="d-flex justify-content-between mb-3">                
                                            <form id="searchForm" method="get" class="d-flex">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" name="keyword" value="${keyword}" placeholder="검색어를 입력하세요">                        
                                                    <button class="btn btn-secondary" type="submit">검색</button>
                                                </div>
                                            </form>
                                            <a class="btn btn-primary" href="/admin/districts/create">구/군 등록</a>
                                        </div>
                                        <%--// 검색 폼, 등록 버튼 --%>

                                        <%-- 목록 --%>
                                        <div class="table-responsive">
                                            <table class="table table-hover">
                                                <thead>
                                                    <tr class="border-top">
                                                        <th>시/도</th>
                                                        <th>구/군</th>
                                                        <th>메모</th>
                                                        <th>관리</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${districts}" var="district" varStatus="status">
                                                        <tr>
                                                            <td>${district.provinceName}</td>
                                                            <td>${district.districtName}</td>
                                                            <td>${district.districtMemo}</td>
                                                            <td>
                                                                <div class="btn-group">
                                                                    <a href="/admin/districts/${district.districtId}" class="btn btn-sm btn-outline-primary">보기</a>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                        <%-- 페이지네이션 --%>
                                        <c:if test="${pagination.totalCount > 0}">
                                            <nav aria-label="Page navigation" class="mt-4">
                                                <ul class="pagination justify-content-center">
                                                    <%-- 이전 페이지 --%>
                                                    <c:if test="${pagination.currentPage > 1}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="/admin/districts?page=${pagination.currentPage - 1}&keyword=${keyword}" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                            </a>
                                                        </li>
                                                    </c:if>

                                                    <%-- 페이지 번호 --%>
                                                    <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="page">
                                                        <li class="page-item ${page == pagination.currentPage ? 'active' : ''}">
                                                            <a class="page-link" href="/admin/districts?page=${page}&keyword=${keyword}">${page}</a>
                                                        </li>
                                                    </c:forEach>

                                                    <%-- 다음 페이지 --%>
                                                    <c:if test="${pagination.currentPage < pagination.totalPages}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="/admin/districts?page=${pagination.currentPage + 1}&keyword=${keyword}" aria-label="Next">
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
