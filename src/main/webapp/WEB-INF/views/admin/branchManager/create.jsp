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
                            <div class="col-lg-12">
                                <form id="createForm" action="/admin/branchManagers/create" method="post">
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            지점 관리자 등록 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">
                                            <div class="mb-3">
                                                <label class="small mb-1" for="userName">이름<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="userName" id="userName" type="text" placeholder="이름을 입력하세요." value="${branchManager.userName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="userPhone">전화번호<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="userPhone" id="userPhone" type="text" placeholder="전화번호를 입력하세요." value="${branchManager.userPhone}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="userEmail">이메일</label>
                                                <input class="form-control" name="userEmail" id="userEmail" type="text" placeholder="이메일을 입력하세요." value="${branchManager.userEmail}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="userId">아이디<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="userId" id="userId" type="text" placeholder="아이디를 입력하세요." value="${branchManager.userId}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="password">비밀번호<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="password" id="password" type="password" placeholder="비밀번호를 입력하세요." />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="passwordCheck">비밀번호 확인<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="passwordCheck" id="passwordCheck" type="password" placeholder="비밀번호를 입력하세요." />
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">지점 관리자 등록</button>
                                            <a href="/admin/branchManagers" class="btn btn-outline-danger">등록 취소</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../../base/footer.jsp" %>
            </div>
        </div>
        <%@ include file="../../base/script.jsp" %>
        <script>
            $(document).ready(function() {
                $("#createForm").validate({
                    rules: {
                        userName: {
                            required: true,
                            minlength: 2,
                            maxlength: 10
                        },
                        userEmail: {
                            email: true,
                            maxlength: 100,
                            remote: {
                                url: '/auth/check-email',
                                type: 'post',
                                data: {
                                    email: function() {
                                        return $('#userEmail').val();
                                    },
                                },
                                dataFilter: function(response) {
                                    const data = JSON.parse(response);
                                    return !data.exists;
                                },
                                depends: function() {
                                    return $('#userEmail').val().length > 0;
                                }
                            }
                        },
                        userPhone: {
                            required: true,
                            minlength: 11,
                            maxlength: 20,
                            remote: {
                                url: '/auth/check-phone',
                                type: 'post',
                                data: {
                                    phone: function() {
                                        return $('#userPhone').val();
                                    },
                                },
                                dataFilter: function(response) {
                                    const data = JSON.parse(response);
                                    return !data.exists;
                                }
                            }
                        },
                        userId: {
                            required: true,
                            minlength: 6,
                            maxlength: 20,
                            remote: {
                                url: '/auth/check-user-id',
                                type: 'post',
                                data: {
                                    userId: function() {
                                        return $('#userId').val();
                                    },
                                },
                                dataFilter: function(response) {
                                    const data = JSON.parse(response);
                                    return !data.exists;
                                }
                            }
                        },
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
                        userName: {
                            required: "이름을 입력해주세요.",
                            minlength: "이름은 최소 2자 이상이어야 합니다.",
                            maxlength: "이름은 최대 10자 이하여야 합니다."
                        },
                        userEmail: {
                            email: "이메일 형식이 올바르지 않습니다.",
                            maxlength: "이메일은 최대 100자 이하여야 합니다.",
                            remote: "이미 사용 중인 이메일입니다."
                        },
                        userPhone: {
                            required: "전화번호를 입력해주세요.",
                            minlength: "전화번호는 최소 11자 이상이어야 합니다.",
                            maxlength: "전화번호는 최대 20자 이하여야 합니다.",
                            remote: "이미 사용 중인 전화번호입니다."
                        },
                        userId: {
                            required: "아이디를 입력해주세요.",
                            minlength: "아이디는 최소 6자 이상이어야 합니다.",
                            maxlength: "아이디는 최대 20자 이하여야 합니다.",
                            remote: "이미 사용 중인 아이디입니다."
                        },
                        password: {
                            required: "비밀번호를 입력해주세요.",
                            minlength: "비밀번호는 최소 8자 이상이어야 합니다.",
                            maxlength: "비밀번호는 최대 20자 이하여야 합니다."
                        },
                        passwordCheck: {
                            required: "비밀번호 확인을 입력해주세요.",
                            minlength: "비밀번호 확인은 최소 8자 이상이어야 합니다.",
                            maxlength: "비밀번호 확인은 최대 20자 이하여야 합니다.",
                            equalTo: "비밀번호와 일치하지 않습니다."
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
