<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <%@ include file="../base/head.jsp" %>
    <body class="nav-fixed">
        <%@ include file="../base/nav.jsp" %>
        <div id="layoutSidenav">
            <%@ include file="../base/layoutSidenav_nav.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <%@ include file="../base/simple_header.jsp" %>
                    <%-- Main page content--%>
                    <div class="container-fluid px-4 mt-4">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card mb-4">
                                    <div class="card-header">지점 관리자 수정</div>
                                    <div class="card-body">
                                        <form id="updateForm" action="/branches/${branch.branchId}/update" method="post">
                                            <div class="mb-3">
                                                <label class="small mb-1" for="branchId">지점</label>
                                                <select class="form-control" name="branchId" id="branchId">
                                                    <option value="">지점을 선택하세요.</option>
                                                    <c:forEach var="branch" items="${branches}">
                                                        <option value="${branch.branchId}" ${branchManager.branchId == branch.branchId ? 'selected' : ''}>${branch.branchName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="userName">이름</label>
                                                <input class="form-control" name="userName" id="userName" type="text" placeholder="이름을 입력하세요." value="${branchManager.userName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="userEmail">이메일</label>
                                                <input class="form-control" name="userEmail" id="userEmail" type="text" placeholder="이메일을 입력하세요." value="${branchManager.userEmail}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="userPhone">전화번호</label>
                                                <input class="form-control" name="userPhone" id="userPhone" type="text" placeholder="전화번호를 입력하세요." value="${branchManager.userPhone}" />
                                            </div>
                                            <div>
                                                <button class="btn btn-primary" type="submit">지점 관리자 수정</button>
                                                <a href="/branches/${branch.branchId}" class="btn btn-outline-danger">수정 취소</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../base/footer.jsp" %>
            </div>
        </div>
        <%@ include file="../base/script.jsp" %>
        <script>
            $(document).ready(function() {
                $("#updateForm").validate({
                    rules: {
                        branchId: {
                            required: true,
                        },
                        userName: {
                            required: true,
                            minlength: 2,
                            maxlength: 10
                        },
                        userEmail: {
                            required: true,
                            minlength: 2,
                            maxlength: 100
                        },
                        userPhone: {
                            required: true,
                            minlength: 2,
                            maxlength: 20
                        }
                    },
                    messages: {
                        branchId: {
                            required: "지점을 선택해주세요.",
                        },
                        userName: {
                            required: "이름을 입력해주세요.",
                            minlength: "이름은 최소 2자 이상이어야 합니다.",
                            maxlength: "이름은 최대 10자 이하여야 합니다."
                        },
                        userEmail: {
                            required: "이메일을 입력해주세요.",
                            minlength: "이메일은 최소 2자 이상이어야 합니다.",
                            maxlength: "이메일은 최대 100자 이하여야 합니다."
                        },
                        userPhone: {
                            required: "전화번호를 입력해주세요.",
                            minlength: "전화번호는 최소 2자 이상이어야 합니다.",
                            maxlength: "전화번호는 최대 20자 이하여야 합니다."
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
