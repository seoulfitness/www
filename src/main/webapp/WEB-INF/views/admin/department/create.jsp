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
                                <form id="createForm" action="/admin/departments/create" method="post">
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            학과 등록 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                        
                                            <div class="mb-3">
                                                <label class="small mb-1" for="departmentName">학과명<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="departmentName" id="departmentName" type="text" placeholder="학과명을 입력하세요." value="${department.departmentName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="departmentAddress">학과 주소</label>
                                                <input class="form-control" name="departmentAddress" id="departmentAddress" type="text" placeholder="학과 주소를 입력하세요." value="${department.departmentAddress}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="departmentPhone">학과 전화번호</label>
                                                <input class="form-control" name="departmentPhone" id="departmentPhone" type="text" placeholder="학과 전화번호를 입력하세요." value="${department.departmentPhone}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="departmentUrl">학과 웹사이트</label>
                                                <input class="form-control" name="departmentUrl" id="departmentUrl" type="text" placeholder="학과 웹사이트를 입력하세요." value="${department.departmentUrl}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="departmentMemo">메모</label>
                                                <textarea class="form-control" name="departmentMemo" id="departmentMemo" rows="3" placeholder="메모를 입력하세요.">${department.departmentMemo}</textarea>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">학과 등록</button>
                                            <a href="/admin/departments" class="btn btn-outline-danger">등록 취소</a>
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
                        departmentName: {
                            required: true,
                            minlength: 2,
                            maxlength: 50
                        },
                        departmentUrl: {
                            url: true
                        },
                        departmentMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        departmentName: {
                            required: "학과명을 입력해주세요.",
                            minlength: "학과명은 최소 2자 이상이어야 합니다.",
                            maxlength: "학과명은 최대 50자 이하여야 합니다."
                        },
                        departmentUrl: {
                            required: "학과 웹사이트를 입력해주세요.",
                            url: "유효한 URL을 입력해주세요."
                        },
                        departmentMemo: {
                            maxlength: "메모는 최대 500자 이하여야 합니다."
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
