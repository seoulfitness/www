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
                                <form id="createForm" action="/admin/physicalSubjects/create" method="post">
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            실기 교과목 등록 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                        
                                            <div class="mb-3">
                                                <label class="small mb-1" for="physicalSubjectName">실기 교과목명<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="physicalSubjectName" id="physicalSubjectName" type="text" placeholder="실기 교과목명을 입력하세요." value="${physicalSubject.physicalSubjectName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="physicalSubjectMemo">메모</label>
                                                <textarea class="form-control" name="physicalSubjectMemo" id="physicalSubjectMemo" rows="3" placeholder="메모를 입력하세요.">${physicalSubject.physicalSubjectMemo}</textarea>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">실기 교과목 등록</button>
                                            <a href="/admin/physicalSubjects" class="btn btn-outline-danger">등록 취소</a>
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
                        physicalSubjectName: {
                            required: true,
                            minlength: 2,
                            maxlength: 50
                        },
                        physicalSubjectMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        physicalSubjectName: {
                            required: "실기 교과목명을 입력해주세요.",
                            minlength: "실기 교과목명은 최소 2자 이상이어야 합니다.",
                            maxlength: "실기 교과목명은 최대 50자 이하여야 합니다."
                        },
                        physicalSubjectMemo: {
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
