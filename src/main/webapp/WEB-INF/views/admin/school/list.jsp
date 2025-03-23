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
                                        대학교 목록 (${pagination.totalCount}개)
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
                                            <a class="btn btn-primary" href="/admin/schools/create">대학교 등록</a>
                                        </div>
                                        <%--// 검색 폼, 등록 버튼 --%>

                                        <%-- 목록 --%>
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                    <tr class="border-top">
                                                        <th>로고</th>
                                                        <th>학교명</th>
                                                        <th>주소</th>
                                                        <th>전화번호</th>
                                                        <th>웹사이트</th>
                                                        <th>입학안내</th>
                                                        <th>관리</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${schools}" var="school" varStatus="status">
                                                        <tr>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${not empty school.schoolLogoUrl}">
                                                                        <img src="${school.schoolLogoUrl}" alt="${school.schoolName} 로고" class="me-2" style="width: 24px; height: 24px; object-fit: contain;">
                                                                    </c:when>
                                                                    <c:when test="${not empty school.schoolLogoOriginalFileName}">
                                                                        <img src="/static/img/schools/${school.schoolLogoOriginalFileName}" alt="${school.schoolName} 로고" class="me-2" style="width: 24px; height: 24px; object-fit: contain;">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="text-muted">없음</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>${school.schoolName}</td>
                                                            <td>${school.schoolAddress}</td>
                                                            <td>${school.schoolPhone}</td>
                                                            <td>
                                                                <c:if test="${not empty school.schoolUrl}">
                                                                    <a href="${school.schoolUrl}" target="_blank"><i data-feather="external-link"></i></a>
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:if test="${not empty school.admissionInfoUrl}">
                                                                    <a href="${school.admissionInfoUrl}" target="_blank"><i data-feather="external-link"></i></a>
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <div class="btn-group">
                                                                    <a href="/admin/schools/${school.schoolId}" class="btn btn-sm btn-outline-primary">보기</a>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                        <%-- 페이지네이션 --%>
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
