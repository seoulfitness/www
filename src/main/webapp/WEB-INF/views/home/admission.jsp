<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="bg-white pt-4 pb-4" id="explore">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <%-- 검색 폼, 등록 버튼 --%>
                <div class="d-flex align-items-center gap-2 mb-3">       
                    <div class="d-flex gap-1">
                        <div class="input-group">
                            <button type="button" class="btn ${not empty searchScope && searchScope == 'ga' ? 'btn-primary' : 'btn-outline-primary'}" id="searchGaButton">가군</button>
                            <button type="button" class="btn ${not empty searchScope && searchScope == 'na' ? 'btn-success' : 'btn-outline-success'}" id="searchNaButton">나군</button>
                            <button type="button" class="btn ${not empty searchScope && searchScope == 'da' ? 'btn-orange' : 'btn-outline-orange'}" id="searchDaButton">다군</button>
                            <button type="button" class="btn ${empty searchScope ? 'btn-danger' : 'btn-outline-danger'}" id="showAllScopeButton">전체</button>
                        </div>
                    </div>
                    <div class="d-flex gap-1">
                        <div class="input-group">
                            <button type="button" class="btn ${not empty searchAdmission && searchAdmission == 'early' ? 'btn-primary' : 'btn-outline-primary'}" id="searchEarlyAdmissionBtton">정시</button>
                            <button type="button" class="btn ${not empty searchAdmission && searchAdmission == 'regular' ? 'btn-success' : 'btn-outline-success'}" id="searchRegularAdmissionButton">수시</button>
                            <button type="button" class="btn ${empty searchAdmission ? 'btn-danger' : 'btn-outline-danger'}" id="showAllAdmissionButton">전체</button>
                        </div>
                    </div>         
                    <form class="d-flex" id="searchForm" method="get">
                        <div class="input-group">
                            <input type="text" class="form-control" name="keyword" value="${keyword}" placeholder="검색어를 입력하세요">                        
                            <button class="btn btn-secondary" type="submit">검색</button>
                            <c:if test="${not empty keyword}">
                                <button class="btn btn-danger" type="button" id="cancelSearchButton">취소</button>
                            </c:if>
                        </div>
                    </form>
                </div>
                <%--// 검색 폼, 등록 버튼 --%>

                <%-- 목록 --%>
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr class="border-top">
                                <th class="text-center">구분</th>
                                <th>학교</th>
                                <th>학과</th>
                                <th class="text-center">수시</th>
                                <th class="text-center">정시</th>
                                <th class="text-center">홈페이지</th>
                                <th class="text-center">입학안내</th>
                                <th class="text-center">전화번호</th>
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
                                    <td class="text-center text-middle">${admission.schoolPhone}<td>                                                                
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <%-- 목록 --%>

            </div>
        </div>
    </div>
</section>