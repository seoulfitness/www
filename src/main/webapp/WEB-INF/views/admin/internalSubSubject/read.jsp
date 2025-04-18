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
                                        내신 세부 교과목 정보
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <%-- 내신 세부 교과목 정보 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr class="border-top">
                                                        <th class="align-middle col-2">내신 교과목명</th>
                                                        <td class="align-middle col-10">${internalSubSubjects.internalSubjectsName}</td>
                                                    </tr>
                                                    <tr class="border-top">
                                                        <th class="align-middle col-2">내신 세부 교과목명</th>
                                                        <td class="align-middle col-10">${internalSubSubjects.internalSubSubjectsName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">메모</th>
                                                        <td class="align-middle col-10">${internalSubSubjects.internalSubSubjectsMemo}</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 내신 세부 교과목 정보 --%>

                                            <%-- 등록일시, 수정일시 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr class="border-top">
                                                        <th class="align-middle col-2">등록일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${internalSubSubjects.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">등록한 사람</th>
                                                        <td class="align-middle col-10">${internalSubSubjects.createdUserName} (${internalSubSubjects.createdBy}, ${internalSubSubjects.createdUserPhone})</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${internalSubSubjects.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정한 사람</th>
                                                        <td class="align-middle col-10">${internalSubSubjects.updatedUserName} (${internalSubSubjects.updatedBy}, ${internalSubSubjects.updatedUserPhone})</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 등록일시, 수정일시 --%>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <a href="/admin/internalSubSubjects" class="btn btn-primary">내신 세부 교과목 목록</a>
                                        <a href="/admin/internalSubSubjects/${internalSubSubjects.internalSubSubjectsId}/update" class="btn btn-outline-warning">내신 세부 교과목 수정</a>
                                        <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">내신 세부 교과목 삭제</button>
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
                    <form id="deleteForm" action="/admin/internalSubSubjects/${internalSubSubjects.internalSubSubjectsId}/delete" method="POST">
                        <%-- modal-header --%>
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-danger" id="deleteModalModalLabel">
                                <strong>내신 세부 교과목 삭제</strong>
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
