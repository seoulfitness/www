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
                                <form id="createForm" action="/admin/physicalRecordPeriods/create" method="post">
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            실기 기록 등록 기간 등록 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                        
                                            <div class="mb-3">
                                                <label class="small mb-1" for="title">제목<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="title" id="title" type="text" placeholder="제목을 입력하세요." value="${not empty physicalRecordPeriod.title ? physicalRecordPeriod.title : ''}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="startDate">등록 시작일<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="startDate" id="startDate" type="date" placeholder="등록 시작일을 입력하세요." value="${not empty physicalRecordPeriod.startDate ? physicalRecordPeriod.startDate : ''}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="endDate">등록 종료일<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="endDate" id="endDate" type="date" placeholder="등록 종료일을 입력하세요." value="${not empty physicalRecordPeriod.endDate ? physicalRecordPeriod.endDate : ''}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="grantBranchUser">지점 회원의 등록 권한<span class="text-danger small">*</span></label>
                                                <select class="form-select" id="grantBranchUser" name="grantBranchUser"> 
                                                    <option value="Y" <c:if test="${physicalRecordPeriod.grantBranchUser == 'Y'}">selected</c:if>>있음</option>
                                                    <option value="N" <c:if test="${physicalRecordPeriod.grantBranchUser == 'N'}">selected</c:if>>없음</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="grantNormalUser">일반 회원의 등록 권한<span class="text-danger small">*</span></label>
                                                <select class="form-select" id="grantNormalUser" name="grantNormalUser"> 
                                                    <option value="Y" <c:if test="${physicalRecordPeriod.grantNormalUser == 'Y'}">selected</c:if>>있음</option>
                                                    <option value="N" <c:if test="${physicalRecordPeriod.grantNormalUser == 'N'}">selected</c:if>>없음</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="memo">메모</label>
                                                <textarea class="form-control" name="memo" id="memo" placeholder="메모를 입력하세요." value="${not empty physicalRecordPeriod.memo ? physicalRecordPeriod.memo : ''}"></textarea>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">실기 기록 등록 기간 등록</button>
                                            <a href="/admin/physicalRecordPeriods" class="btn btn-outline-danger">등록 취소</a>
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
                        title: {
                            required: true,
                            minlength: 2,
                            maxlength: 100
                        },
                        startDate: {
                            required: true,
                            date: true
                        },
                        endDate: {
                            required: true,
                            date: true,
                            greaterThan: "#startDate"
                        },
                        grantBranchUser: {
                            required: true,
                        },
                        grantNormalUser: {
                            required: true,
                        },
                        memo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        title: {
                            required: "제목을 입력해주세요.",
                            minlength: "제목은 최소 2자 이상이어야 합니다.",
                            maxlength: "제목은 최대 100자 이하여야 합니다."
                        },
                        startDate: {
                            required: "기록 등록 시작일을 입력해주세요.",
                            date: "기록 등록 시작일은 날짜 형식이어야 합니다."
                        },
                        endDate: {
                            required: "기록 등록 종료일을 입력해주세요.",
                            date: "기록 등록 종료일은 날짜 형식이어야 합니다.",
                            greaterThan: "기록 등록 종료일은 기록 등록 시작일 이후여야 합니다."
                        },
                        grantBranchUser: {
                            required: "지점 회원의 기록 등록 권한을 선택해주세요.",
                        },
                        grantNormalUser: {
                            required: "일반 회원의 기록 등록 권한을 선택해주세요.",
                        },
                        memo: {
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
