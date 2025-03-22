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
                                        대학교 정보
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-hover">
                                                <tbody>
                                                    <tr class="border-top">
                                                        <th class="align-middle col-2">대학교명</th>
                                                        <td class="align-middle col-10">${school.schoolName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">주소</th>
                                                        <td class="align-middle col-10">${school.schoolAddress}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">전화번호</th>
                                                        <td class="align-middle col-10">${school.schoolPhone}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">웹사이트</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${not empty school.schoolUrl}">
                                                                <a href="${school.schoolUrl}" target="_blank">${school.schoolUrl}</a>
                                                            </c:if>
                                                        </td>
                                                    </tr>                        
                                                    <tr>
                                                        <th class="align-middle col-2">입학안내</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${not empty school.admissionInfoUrl}">
                                                                <a href="${school.admissionInfoUrl}" target="_blank">${school.admissionInfoUrl}</a>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">로고 URL</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${not empty school.schoolLogoUrl}">
                                                                <img src="${school.schoolLogoUrl}" alt="대학교 로고" class="img-fluid">
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">로고 파일</th>
                                                        <td class="align-middle col-10">
                                                            <c:if test="${not empty school.schoolLogoOriginalFileName}">
                                                                <img src="/static/img/schools/${school.schoolLogoOriginalFileName}" alt="대학교 로고" class="img-fluid" style="width: 100px; height: 100px; object-fit: contain;">
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">로고 선택</th>
                                                        <td class="align-middle col-10">
                                                            <c:choose>
                                                                <c:when test="${school.schoolLogo == 'url'}">
                                                                    URL
                                                                </c:when>
                                                                <c:when test="${school.schoolLogo == 'file'}">
                                                                    파일
                                                                </c:when>
                                                                <c:otherwise>
                                                                    선택 안함
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                    <tr class="border-top">
                                                        <th class="align-middle col-2">등록일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${school.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">등록한 사람</th>
                                                        <td class="align-middle col-10">${school.createdUserName} (${school.createdBy}, ${school.createdUserPhone})</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${school.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정한 사람</th>
                                                        <td class="align-middle col-10">${school.updatedUserName} (${school.updatedBy}, ${school.updatedUserPhone})</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div>
                                            <div>
                                                <a href="/branchManager/schools" class="btn btn-primary">대학교 목록</a>
                                                <a href="/branchManager/schools/${school.schoolId}/update" class="btn btn-outline-warning">대학교 수정</a>
                                                <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">대학교 삭제</button>
                                            </div>
                                        </div>
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
                    <form id="deleteForm" action="/branchManager/schools/${school.schoolId}/delete" method="POST">
                        <%-- modal-header --%>
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-danger" id="deleteModalModalLabel">
                                <strong>대학교 삭제</strong>
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
