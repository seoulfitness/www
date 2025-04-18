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
                                <form id="updateForm" action="/admin/internalSubSubjects/${internalSubSubjects.internalSubSubjectsId}/update" method="post">
                                    <input type="hidden" name="internalSubSubjectsId" value="${internalSubSubjects.internalSubSubjectsId}" />
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            내신 세부 교과목 수정 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">    
                                            <div class="mb-3">
                                                <label class="small mb-1" for="internalSubjectId">내신 교과목<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="internalSubjectId" id="internalSubjectId">
                                                    <option value="">내신 교과목 선택</option>
                                                    <c:forEach items="${internalSubjects}" var="internalSubject">
                                                        <option value="${internalSubject.internalSubjectId}" ${internalSubject.internalSubjectId == internalSubSubject.internalSubjectId ? "selected" : ""}>${internalSubject.internalSubjectName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="internalSubSubjectsName">내신 세부 교과목명<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="internalSubSubjectsName" id="internalSubSubjectsName" type="text" placeholder="내신 세부 교과목명을 입력하세요." value="${internalSubSubjects.internalSubSubjectsName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="internalSubSubjectsMemo">메모</label>
                                                <textarea class="form-control" name="internalSubSubjectsMemo" id="internalSubSubjectsMemo" rows="3" placeholder="메모를 입력하세요.">${internalSubSubjects.internalSubSubjectsMemo}</textarea>
                                            </div>                                            
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">내신 세부 교과목 수정</button>
                                            <a href="/admin/internalSubSubjects" class="btn btn-outline-danger">수정 취소</a>
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
                $('#internalSubjectId').focus();
                
                $("#updateForm").validate({
                    rules: {
                        internalSubjectId: {
                            required: true
                        },
                        internalSubSubjectsName: {
                            required: true,
                            minlength: 2,
                            maxlength: 50,
                            remote: {
                                url: "/admin/internalSubSubjects/existsInternalSubSubjectName",
                                type: "post",
                                data: {
                                    internalSubSubjectsName: function() {
                                        return $("#internalSubSubjectsName").val();
                                    },
                                    internalSubjectId: function() {
                                        return $("#internalSubjectId").val();
                                    }
                                },
                                dataFilter: function(response) {
                                    const data = JSON.parse(response);
        
                                    // 사용자의 원래 내신 세부 교과목명
                                    let originalInternalSubSubjectName = '${internalSubSubjects.internalSubSubjectsName}';
        
                                    // 사용자가 내신 교과목, 내신 세부 교과목명을 변경했는지 확인
                                    if (originalInternalSubjectId !== $('#internalSubjectId').val() || originalInternalSubSubjectName !== $('#internalSubSubjectsName').val()) {
                                        // 사용자가 내신 교과목, 내신 세부 교과목명을 변경했으면 중복 체크
                                        return !data.exists;
                                    } else {
                                        // 사용자가 내신 교과목, 내신 세부 교과목명을 변경하지 않았으면 중복 체크를 하지 않음
                                        return true;
                                    }
                                }
                            }
                        },
                        internalSubSubjectsMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        internalSubjectId: {
                            required: "내신 교과목을 선택해주세요."
                        },
                        internalSubSubjectsName: {
                            required: "내신 세부 교과목명을 입력해주세요.",
                            minlength: "내신 세부 교과목명은 최소 2자 이상이어야 합니다.",
                            maxlength: "내신 세부 교과목명은 최대 50자 이하여야 합니다.",
                            remote: "이미 존재하는 내신 세부 교과목명입니다."
                        },
                        internalSubSubjectsMemo: {
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
