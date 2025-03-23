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
                                <form id="updateForm" action="/admin/provinces/${province.provinceId}/update" method="post">
                                    <input type="hidden" name="provinceId" value="${province.provinceId}" />
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            시/도 수정 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                        
                                            <div class="mb-3">
                                                <label class="small mb-1" for="provinceName">시/도명<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="provinceName" id="provinceName" type="text" placeholder="시/도명을 입력하세요." value="${province.provinceName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="provinceMemo">메모</label>
                                                <textarea class="form-control" name="provinceMemo" id="provinceMemo" rows="5" placeholder="메모를 입력하세요.">${province.provinceMemo}</textarea>
                                            </div>                                            
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">시/도 수정</button>
                                            <a href="/admin/provinces" class="btn btn-outline-danger">수정 취소</a>
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
                $("#updateForm").validate({
                    rules: {
                        provinceName: {
                            required: true,
                            minlength: 2,
                            maxlength: 50
                        },
                        provinceMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        provinceName: {
                            required: "시/도명을 입력해주세요.",
                            minlength: "시/도명은 최소 2자 이상이어야 합니다.",
                            maxlength: "시/도명은 최대 50자 이하여야 합니다."
                        },
                        provinceMemo: {
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
