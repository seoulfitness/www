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
                                        입시 요강 정보
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <%-- 입시 요강 정보 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle col-2">연도</th>
                                                        <td class="align-middle col-10">${criteria.year}년</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">입시 요강 구분</th>
                                                        <td class="align-middle col-10">${criteria.criteriaGubun}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">학교</th>
                                                        <td class="align-middle col-10">${criteria.schoolName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">학과</th>
                                                        <td class="align-middle col-10">${criteria.departmentName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">합격자 수</th>
                                                        <td class="align-middle col-10">${criteria.acceptedCount}명</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수능 반영 점수</th>
                                                        <td class="align-middle col-10">${criteria.csatReflectedScore}점</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">실기 반영 점수</th>
                                                        <td class="align-middle col-10">${criteria.physicalReflectedScore}점</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">내신 반영 점수</th>
                                                        <td class="align-middle col-10">${criteria.internalReflectedScore}점</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">면접 반영 점수</th>
                                                        <td class="align-middle col-10">${criteria.interviewReflectedScore}점</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">총 반영 점수</th>
                                                        <td class="align-middle col-10">${criteria.totalReflectedScore}점</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">메모</th>
                                                        <td class="align-middle col-10">${criteria.criteriaMemo}</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 입시 요강 정보 --%>

                                            <%-- 등록일시, 수정일시 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr class="border-top">
                                                        <th class="align-middle col-2">등록일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${criteria.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">등록한 사람</th>
                                                        <td class="align-middle col-10">${criteria.createdUserName} (${criteria.createdBy}, ${criteria.createdUserPhone})</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${criteria.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정한 사람</th>
                                                        <td class="align-middle col-10">${criteria.updatedUserName} (${criteria.updatedBy}, ${criteria.updatedUserPhone})</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 등록일시, 수정일시 --%>
                                        </div>                                        
                                    </div>
                                    <div class="card-footer">
                                        <a href="/admin/criterias" class="btn btn-primary">입시 요강 목록</a>
                                        <a href="/admin/criterias/${criteria.criteriaId}/update" class="btn btn-outline-warning">입시 요강 수정</a>
                                        <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">입시 요강 삭제</button>
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
                    <form id="deleteForm" action="/admin/criterias/${criteria.criteriaId}/delete" method="POST">
                        <%-- modal-header --%>
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-danger" id="deleteModalModalLabel">
                                <strong>입시 요강 삭제</strong>
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
