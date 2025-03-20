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
                            <div class="col-lg-6">
                                <div class="card mb-4">
                                    <div class="card-body">                                        
                                        <div class="table-responsive">
                                            <%-- 지점 관리자 정보 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">이름</th>
                                                        <td class="align-middle col-10">${branchManager.userName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">아이디</th>
                                                        <td class="align-middle col-10">${branchManager.userId}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">전화번호</th>
                                                        <td class="align-middle col-10">${branchManager.userPhone}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">이메일</th>
                                                        <td class="align-middle col-10">${branchManager.userEmail}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">비밀번호</th>
                                                        <td class="align-middle col-10">
                                                            <a href="/admin/branchManagers/${branchManager.branchManagerId}/updatePassword" class="btn btn-outline-danger btn-sm">비밀번호 변경</a>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 지점 관리자 정보 --%>

                                            <%-- 지점 정보 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">지점</th>
                                                        <td class="align-middle col-10">${branchManager.branchName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">주소</th>
                                                        <td class="align-middle col-10">${branchManager.branchAddress}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">전화번호</th>
                                                        <td class="align-middle col-10">${branchManager.branchPhone}</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 지점 정보 --%>

                                            <%-- 등록일시, 수정일시 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">등록일시</th>
                                                        <td class="align-middle col-10">${branchManager.createdAt.substring(0, 16)}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">등록한 사람</th>
                                                        <td class="align-middle col-10">${branchManager.createdUserName} (${branchManager.createdBy}, ${branchManager.createdUserPhone})</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">수정일시</th>
                                                        <td class="align-middle col-10">${branchManager.updatedAt.substring(0, 16)}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle text-center col-2">수정한 사람</th>
                                                        <td class="align-middle col-10">${branchManager.updatedUserName} (${branchManager.updatedBy}, ${branchManager.updatedUserPhone})</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 등록일시, 수정일시 --%>
                                        </div>
                                        <div>
                                            <div>
                                                <a href="/admin/branchManagers" class="btn btn-primary">지점 관리자 목록</a>
                                                <a href="/admin/branchManagers/${branchManager.branchManagerId}/update" class="btn btn-outline-warning">지점 관리자 수정</a>
                                                <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">지점 관리자 삭제</button>
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
                    <form id="deleteForm" action="/admin/branchManagers/${branchManager.branchManagerId}/delete" method="POST">
                        <%-- modal-header --%>
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-danger" id="deleteModalModalLabel">
                                <strong>지점 관리자 삭제</strong>
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
