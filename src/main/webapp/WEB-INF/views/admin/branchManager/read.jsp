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
                                        지점 관리자 정보
                                    </div>
                                    <div class="card-body">                                        
                                        <div class="table-responsive">
                                            <%-- 지점 관리자 정보 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle col-2">이름</th>
                                                        <td class="align-middle col-10">${user.userName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">아이디</th>
                                                        <td class="align-middle col-10">${user.userId}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">전화번호</th>
                                                        <td class="align-middle col-10">${user.userPhone}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">이메일</th>
                                                        <td class="align-middle col-10">${user.userEmail}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">비밀번호</th>
                                                        <td class="align-middle col-10">
                                                            <button type="button" class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#updatePasswordModal">비밀번호 변경</button>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 지점 관리자 정보 --%>

                                            <%-- 등록일시, 수정일시 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle col-2">등록일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${user.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">등록한 사람</th>
                                                        <td class="align-middle col-10">${user.createdUserName} (${user.createdBy}, ${user.createdUserPhone})</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${user.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정한 사람</th>
                                                        <td class="align-middle col-10">${user.updatedUserName} (${user.updatedBy}, ${user.updatedUserPhone})</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 등록일시, 수정일시 --%>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <a href="/admin/branchManagers" class="btn btn-primary">지점 관리자 목록</a>
                                        <a href="/admin/branchManagers/${user.userId}/update" class="btn btn-outline-warning">지점 관리자 수정</a>
                                        <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">지점 관리자 삭제</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../../base/footer.jsp" %>
            </div>
        </div>

        <%-- 비밀번호 변경 모달 --%>
        <div class="modal fade" id="updatePasswordModal" tabindex="-1" aria-labelledby="updatePasswordModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="updatePasswordForm" action="/admin/branchManagers/${user.userId}/update" method="POST">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-danger" id="updatePasswordModalLabel">
                                <strong>비밀번호 변경</strong>
                            </h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <%--// modal-header --%>

                        <%-- modal-body --%>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="password" class="form-label">새 비밀번호</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <div class="mb-3">
                                <label for="passwordCheck" class="form-label">새 비밀번호 확인</label>
                                <input type="password" class="form-control" id="passwordCheck" name="passwordCheck" required>
                            </div>
                        </div>
                        <%--// modal-body --%>

                        <%-- modal-footer --%>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-outline-danger">변경</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                        </div>
                        <%--// modal-footer --%>
                    </form>
                </div>
            </div>
        </div>
        <%--// 비밀번호 변경 모달 --%>

        <%-- 삭제 모달 --%>
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="deleteForm" action="/admin/branchManagers/${user.userId}/delete" method="POST">
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

        <script>
            $(document).ready(function() {
                $('#updatePasswordForm').validate({
                    rules: {
                        password: {
                            required: true,
                            minlength: 8,
                            maxlength: 20
                        },
                        passwordCheck: {
                            required: true,
                            minlength: 8,
                            maxlength: 20,
                            equalTo: "#password"
                        }
                    },
                    messages: {
                        password: {
                            required: "비밀번호를 입력해주세요.",
                            minlength: "비밀번호는 8자 이상이어야 합니다.",
                            maxlength: "비밀번호는 20자 이하여야 합니다."
                        },
                        passwordCheck: {
                            required: "비밀번호 확인을 입력해주세요.",
                            minlength: "비밀번호는 8자 이상이어야 합니다.",
                            maxlength: "비밀번호는 20자 이하여야 합니다.",
                            equalTo: "비밀번호가 일치하지 않습니다."
                        }
                    },
                    errorClass: 'is-invalid',
                    validClass: 'is-valid',
                    errorPlacement: function(error, element) {
                        error.addClass('invalid-feedback');
                        element.closest('.mb-3').append(error);
                    },
                    submitHandler: function(form) {
                        form.submit();
                    }
                });
            });
        </script>
    </body>
</html>
