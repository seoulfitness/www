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
                            <div class="col-lg-6">
                                <form id="updateForm" action="/admin/internalSubjects/${internalSubject.internalSubjectId}/update" method="post">
                                    <input type="hidden" name="internalSubjectId" value="${internalSubject.internalSubjectId}" />
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            내신 교과목 수정 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                        
                                            <div class="mb-3">
                                                <label class="small mb-1" for="internalSubjectName">내신 교과목명<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="internalSubjectName" id="internalSubjectName" type="text" placeholder="내신 교과목명을 입력하세요." value="${internalSubject.internalSubjectName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="internalSubjectMemo">메모</label>
                                                <textarea class="form-control" name="internalSubjectMemo" id="internalSubjectMemo" rows="3" placeholder="메모를 입력하세요.">${internalSubject.internalSubjectMemo}</textarea>
                                            </div>                                            
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">내신 교과목 수정</button>
                                            <a href="/admin/internalSubjects" class="btn btn-outline-danger">수정 취소</a>
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
                $('#internalSubjectName').focus();
                
                $("#updateForm").validate({
                    rules: {
                        internalSubjectName: {
                            required: true,
                            minlength: 2,
                            maxlength: 50,
                            remote: {
                                url: "/admin/internalSubjects/existsInternalSubjectName",
                                type: "post",
                                data: {
                                    internalSubjectName: function() {
                                        return $("#internalSubjectName").val();
                                    }
                                },
                                dataFilter: function(response) {
                                    const data = JSON.parse(response);
        
                                    // 사용자의 원래 내신 교과목명
                                    let originalInternalSubjectName = '${internalSubject.internalSubjectName}';
        
                                    // 사용자가 내신 교과목명을 변경했는지 확인
                                    if (originalInternalSubjectName !== $('#internalSubjectName').val()) {
                                        // 사용자가 내신 교과목명을 변경했으면 중복 체크
                                        return !data.exists;
                                    } else {
                                        // 사용자가 내신 교과목명을 변경하지 않았으면 중복 체크를 하지 않음
                                        return true;
                                    }
                                }
                            }
                        },
                        internalSubjectMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        internalSubjectName: {
                            required: "내신 교과목명을 입력해주세요.",
                            minlength: "내신 교과목명은 최소 2자 이상이어야 합니다.",
                            maxlength: "내신 교과목명은 최대 50자 이하여야 합니다.",
                            remote: "이미 존재하는 내신 교과목명입니다."
                        },
                        internalSubjectMemo: {
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
