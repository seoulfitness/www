<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="bg-white pt-4 pb-4" id="explore">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <%-- 검색 폼, 등록 버튼 --%>
                <div class="d-flex justify-content-between mb-3">                
                    <form id="searchForm" method="get" class="d-flex">
                        <div class="input-group">
                            <input type="text" class="form-control" name="keyword" value="" placeholder="검색어를 입력하세요">                        
                            <button class="btn btn-secondary" type="submit" >검색</button>
                        </div>
                    </form>
                </div>
                <%--// 검색 폼, 등록 버튼 --%>

                <%-- 목록 --%>
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr class="border-top text-center">
                                <th>구분</th>
                                <th>학교</th>
                                <th>학과</th>
                                <th>수시</th>
                                <th>정시</th>
                                <th>홈페이지</th>
                                <th>입학안내</th>
                                <th>전화번호</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${admissions}" var="admission" varStatus="status">
                                <tr>
                                    <td class="text-center text-middle">${admission.admissionType}군</td>
                                    <td class="text-middle">
                                        <c:if test="${not empty admission.schoolLogoUrl}">
                                            <c:choose>
                                                <c:when test="${not empty admission.schoolLogoUrl}">
                                                    <img src="${admission.schoolLogoUrl}" alt="${admission.schoolName} 로고" class="me-2" style="width: 24px; height: 24px; object-fit: contain;">
                                                </c:when>
                                                <c:when test="${not empty admission.schoolLogoOriginalFileName}">
                                                    <img src="/static/img/schools/${admission.schoolLogoOriginalFileName}" alt="${admission.schoolName} 로고" class="me-2" style="width: 24px; height: 24px; object-fit: contain;">
                                                </c:when>
                                            </c:choose>
                                        </c:if>
                                        ${admission.schoolName}
                                    </td>
                                    <td class="text-middle">${admission.departmentName}</td>
                                    <td class="text-center text-middle">
                                        <c:choose>
                                            <c:when test="${not empty admission.earlyAdmission && admission.earlyAdmission == 'Y'}">
                                                <span class="btn btn-primary btn-sm">진행</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="btn btn-outline-dark btn-sm">미진행</span>                                                                        
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="text-center text-middle">
                                        <c:choose>
                                            <c:when test="${not empty admission.regularAdmission && admission.regularAdmission == 'Y'}">
                                                <span class="btn btn-primary btn-sm">진행</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="btn btn-outline-dark btn-sm">미진행</span>                                                                        
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="text-center text-middle">
                                        <c:if test="${not empty admission.schoolUrl}">
                                            <a href="${admission.schoolUrl}" target="_blank">
                                                <i data-feather="globe" style="width: 24px; height: 24px;"></i>
                                            </a>
                                        </c:if>
                                    </td>
                                    <td class="text-center text-middle">
                                        <c:if test="${not empty admission.admissionInfoUrl}">
                                            <a href="${admission.admissionInfoUrl}" target="_blank">
                                                <i data-feather="compass" style="width: 24px; height: 24px;"></i>
                                            </a>
                                        </c:if>
                                    </td>
                                    <td class="text-middle">${admission.schoolPhone}<td>                                                                
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
                                    <a class="page-link" href="/?page=${pagination.currentPage - 1}&keyword=${keyword}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <%-- 페이지 번호 --%>
                            <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="page">
                                <li class="page-item ${page == pagination.currentPage ? 'active' : ''}">
                                    <a class="page-link" href="/?page=${page}&keyword=${keyword}">${page}</a>
                                </li>
                            </c:forEach>

                            <%-- 다음 페이지 --%>
                            <c:if test="${pagination.currentPage < pagination.totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="/?page=${pagination.currentPage + 1}&keyword=${keyword}" aria-label="Next">
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
</section>