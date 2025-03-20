<%@ page language="java" contentType="text/html; charset=UTF-8"%>

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
                                        지점 수정 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                    </div>
                                    <div class="card-body">
                                        <form id="updateForm" action="/admin/branches/${branch.branchId}/update" method="post">
                                            <div class="mb-3">
                                                <label class="small mb-1" for="branchName">지점명<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="branchName" id="branchName" type="text" placeholder="지점명을 입력하세요." value="${branch.branchName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="branchAddress">주소<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="branchAddress" id="branchAddress" type="text" placeholder="주소를 입력하세요." value="${branch.branchAddress}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="branchPhone">전화번호<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="branchPhone" id="branchPhone" type="text" placeholder="전화번호를 입력하세요." value="${branch.branchPhone}" />
                                            </div>
                                            <div>
                                                <button class="btn btn-primary" type="submit">지점 수정</button>
                                                <a href="/admin/branches/${branch.branchId}" class="btn btn-outline-danger">수정 취소</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
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
                $("#updateForm").validate({
                    rules: {
                        branchName: {
                            required: true,
                            minlength: 2,
                            maxlength: 50
                        },
                        branchAddress: {
                            required: true,
                            minlength: 2,
                            maxlength: 100
                        },
                        branchPhone: {
                            required: true,
                            minlength: 2,
                            maxlength: 20
                        }
                    },
                    messages: {
                        branchName: {
                            required: "지점명을 입력해주세요.",
                            minlength: "지점명은 최소 2자 이상이어야 합니다.",
                            maxlength: "지점명은 최대 50자 이하여야 합니다."
                        },
                        branchAddress: {
                            required: "주소를 입력해주세요.",
                            minlength: "주소는 최소 2자 이상이어야 합니다.",
                            maxlength: "주소는 최대 100자 이하여야 합니다."
                        },
                        branchPhone: {
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
