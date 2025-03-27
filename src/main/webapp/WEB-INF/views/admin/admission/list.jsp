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
                                        입시 요강 목록 (${pagination.totalCount}개)
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
                                            <a class="btn btn-primary" href="/admin/admissions/create">입시 요강 등록</a>
                                        </div>
                                        <%--// 검색 폼, 등록 버튼 --%>

                                        <%-- 목록 --%>
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>연도</th>  
                                                        <th>구분</th>
                                                        <th>학교</th>
                                                        <th>학과</th>
                                                        <th>수시</th>
                                                        <th>정시</th>
                                                        <th>메모</th>
                                                        <th>관리</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${admissions}" var="admission" varStatus="status">
                                                        <tr>
                                                            <td>${admission.admissionYear}년</td>
                                                            <td>${admission.admissionType}</td>
                                                            <td>${admission.schoolName}</td>
                                                            <td>${admission.departmentName}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${not empty admission.earlyAdmission && admission.earlyAdmission == 'Y'}">
                                                                        <c:choose>
                                                                            <c:when test="${not empty admission.earlyAdmissionDto}">
                                                                                <span class="btn btn-success btn-sm">수시 입시 진행</span>                                                                        
                                                                            </c:when>
                                                                            <c:when test="${empty admission.earlyAdmissionDto}">
                                                                                <span class="btn btn-outline-danger btn-sm">정보 입력 필요</span>                                                                        
                                                                            </c:when>
                                                                        </c:choose>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="btn btn-outline-dark btn-sm">미진행</span>                                                                        
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${not empty admission.regularAdmission && admission.regularAdmission == 'Y'}">
                                                                        <c:choose>
                                                                        <c:when test="${not empty admission.regularAdmissionDto}">
                                                                            <span class="btn btn-success btn-sm">정시 입시 진행</span> 
                                                                        </c:when>
                                                                        <c:when test="${empty admission.regularAdmissionDto}">
                                                                            <span class="btn btn-outline-danger btn-sm">정보 입력 필요</span> 
                                                                        </c:when>
                                                                        </c:choose>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="btn btn-outline-dark btn-sm">미진행</span>                                                                        
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>${admission.admissionMemo}</td>
                                                            <td>
                                                                <div class="btn-group">
                                                                    <a href="/admin/admissions/${admission.admissionId}" class="btn btn-sm btn-outline-primary">보기</a>
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
                                                            <a class="page-link" href="/admin/admissions?page=${pagination.currentPage - 1}&keyword=${keyword}" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                            </a>
                                                        </li>
                                                    </c:if>

                                                    <%-- 페이지 번호 --%>
                                                    <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="page">
                                                        <li class="page-item ${page == pagination.currentPage ? 'active' : ''}">
                                                            <a class="page-link" href="/admin/admissions?page=${page}&keyword=${keyword}">${page}</a>
                                                        </li>
                                                    </c:forEach>

                                                    <%-- 다음 페이지 --%>
                                                    <c:if test="${pagination.currentPage < pagination.totalPages}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="/admin/admissions?page=${pagination.currentPage + 1}&keyword=${keyword}" aria-label="Next">
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
