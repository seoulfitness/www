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
                                <form id="updateForm" action="/admin/csatSubjects/${csatSubject.csatSubjectId}/update" method="post">
                                    <input type="hidden" name="csatSubjectId" value="${csatSubject.csatSubjectId}" />
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            수능 교과목 수정 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                        
                                            <div class="mb-3">
                                                <label class="small mb-1" for="csatSubjectName">수능 교과목명<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="csatSubjectName" id="csatSubjectName" type="text" placeholder="수능 교과목명을 입력하세요." value="${csatSubject.csatSubjectName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="csatSubjectMemo">메모</label>
                                                <textarea class="form-control" name="csatSubjectMemo" id="csatSubjectMemo" rows="3" placeholder="메모를 입력하세요.">${csatSubject.csatSubjectMemo}</textarea>
                                            </div>                                        
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">수능 교과목 수정</button>
                                            <a href="/admin/csatSubjects" class="btn btn-outline-danger">수정 취소</a>
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
                        csatSubjectName: {
                            required: true,
                            minlength: 2,
                            maxlength: 50
                        },
                        csatSubjectType: {
                            required: true
                        },
                        csatSubjectMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        csatSubjectName: {
                            required: "수능 교과목명을 입력해주세요.",
                            minlength: "수능 교과목명은 최소 2자 이상이어야 합니다.",
                            maxlength: "수능 교과목명은 최대 50자 이하여야 합니다."
                        },
                        csatSubjectType: {
                            required: "수능 교과목 타입을 선택해주세요."
                        },
                        csatSubjectMemo: {
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
