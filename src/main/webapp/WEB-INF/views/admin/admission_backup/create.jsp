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
                                <form id="createForm" action="/admin/admissions/create" method="post">
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            입시 요강 등록 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                            
                                            <div class="mb-3">
                                                <label class="small mb-1" for="year">연도<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="admissionYear" id="admissionYear">
                                                    <option value="">연도를 선택하세요.</option>
                                                    <c:forEach begin="2025" end="2026" var="year">
                                                        <option value="${year}" ${admission.admissionYear == year ? 'selected' : ''}>${year}년</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="admissionType">구분<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="admissionType" id="admissionType">
                                                    <option value="">구분을 선택하세요.</option>
                                                    <option value="가" ${admission.admissionType == '가' ? 'selected' : ''}>가</option>
                                                    <option value="나" ${admission.admissionType == '나' ? 'selected' : ''}>나</option>
                                                    <option value="다" ${admission.admissionType == '다' ? 'selected' : ''}>다</option>
                                                    <option value="라" ${admission.admissionType == '라' ? 'selected' : ''}>라</option>                                                    
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="schoolId">학교<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="schoolId" id="schoolId">
                                                    <option value="">학교를 선택하세요.</option>
                                                    <c:forEach items="${schools}" var="school">
                                                        <option value="${school.schoolId}" ${admission.schoolId == school.schoolId ? 'selected' : ''}>${school.schoolName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="departmentId">학과<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="departmentId" id="departmentId">
                                                    <option value="">학과를 선택하세요.</option>
                                                    <c:forEach items="${departments}" var="department">
                                                        <option value="${department.departmentId}" ${admission.departmentId == department.departmentId ? 'selected' : ''}>${department.departmentName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="earlyAdmission">수시 입시 여부<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="earlyAdmission" id="earlyAdmission">
                                                    <option value="">수시 입시 여부를 선택하세요.</option>
                                                    <option value="Y" ${admission.earlyAdmission == 'Y' ? 'selected' : ''}>진행</option>
                                                    <option value="N" ${admission.earlyAdmission == 'N' ? 'selected' : ''}>미진행</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="regularAdmission">정시 입시 여부<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="regularAdmission" id="regularAdmission">
                                                    <option value="">정시 입시 여부를 선택하세요.</option>
                                                    <option value="Y" ${admission.regularAdmission == 'Y' ? 'selected' : ''}>진행</option>
                                                    <option value="N" ${admission.regularAdmission == 'N' ? 'selected' : ''}>미진행</option>
                                                </select>
                                            </div>                                            
                                            <div class="mb-3">
                                                <label class="small mb-1" for="admissionMemo">메모</label>
                                                <textarea class="form-control" name="admissionMemo" id="admissionMemo" rows="3" placeholder="메모를 입력하세요.">${admission.admissionMemo}</textarea>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">입시 요강 등록</button>
                                            <a href="/admin/admissions" class="btn btn-outline-danger">등록 취소</a>
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
                        admissionYear: {
                            required: true
                        },
                        admissionType: {
                            required: true
                        },
                        earlyAdmission: {
                            required: true
                        },
                        regularAdmission: {
                            required: true
                        },
                        schoolId: {
                            required: true
                        },
                        departmentId: {
                            required: true
                        },
                        admissionMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        admissionYear: {
                            required: "연도를 선택해주세요."
                        },
                        admissionType: {
                            required: "구분을 선택해주세요."
                        },
                        earlyAdmission: {
                            required: "수시 입시 여부를 선택해주세요."
                        },
                        regularAdmission: {
                            required: "정시 입시 여부를 선택해주세요."
                        },
                        schoolId: {
                            required: "학교를 선택해주세요."
                        },
                        departmentId: {
                            required: "학과를 선택해주세요."
                        },
                        admissionMemo: {
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
