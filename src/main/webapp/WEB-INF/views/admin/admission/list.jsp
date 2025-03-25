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
                                                        <th>수시 모집 정보</th>
                                                        <th>정시</th>
                                                        <th>정시 모집 정보</th>
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
                                                                <c:if test="${admission.earlyAdmission == 'Y'}">
                                                                    <span class="badge bg-success">있음</span>
                                                                </c:if>
                                                                <c:if test="${admission.earlyAdmission == 'N'}">
                                                                    <span class="badge bg-danger">없음</span>
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:if test="${admission.earlyAdmission == 'Y'}">
                                                                    <c:choose>
                                                                        <c:when test="${not empty admission.earlyAdmissionDto}">
                                                                            <button type="button" class="btn btn-sm btn-outline-primary btn-early-admission" data-admission-id="${admission.admissionId}">정보</button>                                                                        
                                                                        </c:when>
                                                                        <c:when test="${empty admission.earlyAdmissionDto}">
                                                                            <button type="button" class="btn btn-sm btn-outline-danger btn-early-admission" data-admission-id="${admission.admissionId}">정보</button>                                                                        
                                                                        </c:when>
                                                                    </c:choose>                                                                    
                                                                    <a href="#" class="btn btn-sm btn-outline-primary">수능</a>
                                                                    <a href="#" class="btn btn-sm btn-outline-primary">영어</a>
                                                                    <a href="#" class="btn btn-sm btn-outline-primary">한국사</a>
                                                                    <a href="#" class="btn btn-sm btn-outline-primary">내신</a>
                                                                    <a href="#" class="btn btn-sm btn-outline-primary">면접</a>
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:if test="${admission.regularAdmission == 'Y'}">
                                                                    <span class="badge bg-success">있음</span>
                                                                </c:if>
                                                                <c:if test="${admission.regularAdmission == 'N'}">
                                                                    <span class="badge bg-danger">없음</span>
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:if test="${admission.regularAdmission == 'Y'}">
                                                                    <a href="#" class="btn btn-sm btn-outline-primary">정보</a>
                                                                    <a href="#" class="btn btn-sm btn-outline-primary">수능</a>
                                                                    <a href="#" class="btn btn-sm btn-outline-primary">영어</a>
                                                                    <a href="#" class="btn btn-sm btn-outline-primary">한국사</a>
                                                                    <a href="#" class="btn btn-sm btn-outline-primary">내신</a>
                                                                    <a href="#" class="btn btn-sm btn-outline-primary">면접</a>
                                                                </c:if>
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

        <%-- 수시 모집 정보 모달 --%>
        <div class="modal fade modal-dialog-scrollable" id="earlyAdmissionModal" data-bs-backdrop="static" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <form id="earlyAdmissionForm">
                        <div class="modal-header">
                            <h1 class="modal-title">수시 모집 정보</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="useCsatReflectedScore" class="form-label">수능 점수 반영</label>
                                <select class="form-select" id="useCsatReflectedScore" name="useCsatReflectedScore">
                                    <option value="Y">반영</option>
                                    <option value="N">미반영</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="csatReflectedScore" class="form-label">수능 반영 점수</label>
                                <input type="text" class="form-control" id="csatReflectedScore" name="csatReflectedScore" placeholder="수능 반영 점수">
                            </div>
                            <div class="mb-3">
                                <label for="usePhysicalReflectedScore" class="form-label">실기 점수 반영</label>
                                <select class="form-select" id="usePhysicalReflectedScore" name="usePhysicalReflectedScore">
                                    <option value="Y">반영</option>
                                    <option value="N">미반영</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="physicalReflectedScore" class="form-label">실기 반영 점수</label>
                                <input type="text" class="form-control" id="physicalReflectedScore" name="physicalReflectedScore" placeholder="실기 반영 점수">
                            </div>
                            <div class="mb-3">
                                <label for="useInternalReflectedScore" class="form-label">내신 점수 반영</label>
                                <select class="form-select" id="useInternalReflectedScore" name="useInternalReflectedScore">
                                    <option value="Y">반영</option>
                                    <option value="N">미반영</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="internalReflectedScore" class="form-label">내신 반영 점수</label>
                                <input type="text" class="form-control" id="internalReflectedScore" name="internalReflectedScore" placeholder="내신 반영 점수">
                            </div>
                            <div class="mb-3">
                                <label for="useInterviewReflectedScore" class="form-label">면접 점수 반영</label>
                                <select class="form-select" id="useInterviewReflectedScore" name="useInterviewReflectedScore">
                                    <option value="Y">반영</option>
                                    <option value="N">미반영</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="interviewReflectedScore" class="form-label">면접 반영 점수</label>
                                <input type="text" class="form-control" id="interviewReflectedScore" name="interviewReflectedScore" placeholder="면접 반영 점수">
                            </div>
                            <div class="mb-3">
                                <label for="acceptedCount" class="form-label">모집 인원</label>
                                <input type="text" class="form-control" id="acceptedCount" name="acceptedCount" placeholder="모집 인원">
                            </div>
                            <div class="mb-3">
                                <label for="earlyAdmissionMemo" class="form-label">메모</label>
                                <textarea class="form-control" id="earlyAdmissionMemo" name="earlyAdmissionMemo" placeholder="메모"></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
                            <button type="submit" class="btn btn-primary">저장</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <%@ include file="../../base/script.jsp" %>
        <script>
            $(document).ready(function() {
                // 수시 모집 정보 모달 버튼 클릭 이벤트
                $('.btn-early-admission').click(function() {
                    let admissionId = $(this).data('admission-id');
                    $.ajax({
                        url: '/admin/early-admissions/api/' + admissionId,
                        type: 'GET',
                        success: function(response) {
                            console.log(response);
                            $('#earlyAdmissionModal').modal('show');
                        },
                        error: function(xhr, status, error) {
                            console.error(xhr.responseText);
                        }
                    });
                    
                });
            });
        </script>
    </body>
</html>
