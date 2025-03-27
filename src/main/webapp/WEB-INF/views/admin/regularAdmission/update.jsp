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
                                <form id="updateForm" action="/admin/regularAdmissions/update?admissionId=${admissionId}" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="regularAdmissionId" value="${regularAdmission.regularAdmissionId}" />
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            ${admission.admissionYear}년 ${admission.admissionType}군 ${admission.schoolName} ${admission.departmentName} 정시 입시 정보 수정 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                        
                                            <div class="mb-3">
                                                <label for="useCsatReflectedScore" class="form-label">수능 점수 반영</label>
                                                <select class="form-select" id="useCsatReflectedScore" name="useCsatReflectedScore" value="${regularAdmission.useCsatReflectedScore}">
                                                    <option value="Y" <c:if test="${regularAdmission.useCsatReflectedScore == 'Y'}">selected</c:if>>반영</option>
                                                    <option value="N" <c:if test="${regularAdmission.useCsatReflectedScore == 'N'}">selected</c:if>>미반영</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label for="csatReflectedScore" class="form-label">수능 반영 점수</label>
                                                <input type="number" class="form-control" min="0.00" max="1000.00" step="0.01" value="${not empty regularAdmission.csatReflectedScore ? regularAdmission.csatReflectedScore : '0.00'}" id="csatReflectedScore" name="csatReflectedScore" placeholder="수능 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="usePhysicalReflectedScore" class="form-label">실기 점수 반영</label>
                                                <select class="form-select" id="usePhysicalReflectedScore" name="usePhysicalReflectedScore" value="${regularAdmission.usePhysicalReflectedScore}">
                                                    <option value="Y" <c:if test="${regularAdmission.usePhysicalReflectedScore == 'Y'}">selected</c:if>>반영</option>
                                                    <option value="N" <c:if test="${regularAdmission.usePhysicalReflectedScore == 'N'}">selected</c:if>>미반영</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label for="physicalReflectedScore" class="form-label">실기 반영 점수</label>
                                                <input type="number" class="form-control" min="0.00" max="1000.00" step="0.01" value="${not empty regularAdmission.physicalReflectedScore ? regularAdmission.physicalReflectedScore : '0.00'}" id="physicalReflectedScore" name="physicalReflectedScore" placeholder="실기 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="useInternalReflectedScore" class="form-label">내신 점수 반영</label>
                                                <select class="form-select" id="useInternalReflectedScore" name="useInternalReflectedScore" value="${regularAdmission.useInternalReflectedScore}">
                                                    <option value="Y" <c:if test="${regularAdmission.useInternalReflectedScore == 'Y'}">selected</c:if>>반영</option>
                                                    <option value="N" <c:if test="${regularAdmission.useInternalReflectedScore == 'N'}">selected</c:if>>미반영</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label for="internalReflectedScore" class="form-label">내신 반영 점수</label>
                                                <input type="number" class="form-control" min="0.00" max="1000.00" step="0.01" value="${not empty regularAdmission.internalReflectedScore ? regularAdmission.internalReflectedScore : '0.00'}" id="internalReflectedScore" name="internalReflectedScore" placeholder="내신 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="useInterviewReflectedScore" class="form-label">면접 점수 반영</label>
                                                <select class="form-select" id="useInterviewReflectedScore" name="useInterviewReflectedScore" value="${regularAdmission.useInterviewReflectedScore}">
                                                    <option value="Y" <c:if test="${regularAdmission.useInterviewReflectedScore == 'Y'}">selected</c:if>>반영</option>
                                                    <option value="N" <c:if test="${regularAdmission.useInterviewReflectedScore == 'N'}">selected</c:if>>미반영</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label for="interviewReflectedScore" class="form-label">면접 반영 점수</label>
                                                <input type="number" class="form-control" min="0.00" max="1000.00" step="0.01" value="${not empty regularAdmission.interviewReflectedScore ? regularAdmission.interviewReflectedScore : '0.00'}" id="interviewReflectedScore" name="interviewReflectedScore" placeholder="면접 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="acceptedCount" class="form-label">모집 인원</label>
                                                <input type="text" class="form-control" id="acceptedCount" name="acceptedCount" placeholder="모집 인원" value="$regularAdmission.acceptedCount">
                                            </div>
                                            <div class="mb-3">
                                                <label for="regularAdmissionMemo" class="form-label">메모</label>
                                                <textarea class="form-control" id="regularAdmissionMemo" name="regularAdmissionMemo" placeholder="메모" value="$regularAdmission.regularAdmissionMemo"></textarea>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">정시 입시 정보 수정</button>
                                            <a href="/admin/admissions/${admissionId}" class="btn btn-outline-danger">취소</a>
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
                // 정시(입시) 수능 점수 반영 여부
                $('#useCsatReflectedScore').change(function() {
                    if ($(this).val() == 'Y') {
                        $('#csatReflectedScore').prop('disabled', false);
                    } else {
                        $('#csatReflectedScore').prop('disabled', true);
                    }
                });

                // 정시(입시) 실기 점수 반영 여부
                $('#usePhysicalReflectedScore').change(function() {
                    if ($(this).val() == 'Y') {
                        $('#physicalReflectedScore').prop('disabled', false);
                    } else {
                        $('#physicalReflectedScore').prop('disabled', true);
                    }
                });

                // 정시(입시) 내신 점수 반영 여부
                $('#useInternalReflectedScore').change(function() {
                    if ($(this).val() == 'Y') {
                        $('#internalReflectedScore').prop('disabled', false);
                    } else {
                        $('#internalReflectedScore').prop('disabled', true);
                    }
                });

                // 정시(입시) 면접 점수 반영 여부
                $('#useInterviewReflectedScore').change(function() {
                    if ($(this).val() == 'Y') {
                        $('#interviewReflectedScore').prop('disabled', false);
                    } else {
                        $('#interviewReflectedScore').prop('disabled', true);
                    }
                });

                // 정시(입시) 정보 저장
                $('#updateForm').validate({
                    rules: {
                        acceptedCount: {
                            required: true
                        },
                        regularAdmissionMemo: {
                            maxlength: 500
                        },
                        // useCsatReflectedScore가 Y인 경우 csatReflectedScore가 필수
                        csatReflectedScore: {
                            required: function() {
                                return $('#useCsatReflectedScore').val() == 'Y';
                            }
                        },
                        // usePhysicalReflectedScore가 Y인 경우 physicalReflectedScore가 필수
                        physicalReflectedScore: {
                            required: function() {
                                return $('#usePhysicalReflectedScore').val() == 'Y';
                            }
                        },
                        // useInternalReflectedScore가 Y인 경우 internalReflectedScore가 필수
                        internalReflectedScore: {
                            required: function() {
                                return $('#useInternalReflectedScore').val() == 'Y';
                            }
                        },
                        // useInterviewReflectedScore가 Y인 경우 interviewReflectedScore가 필수
                        interviewReflectedScore: {
                            required: function() {
                                return $('#useInterviewReflectedScore').val() == 'Y';
                            }
                        }
                    },
                    messages: {
                        acceptedCount: {
                            required: '모집 인원을 입력하세요.'
                        },
                        regularAdmissionMemo: {
                            maxlength: '500자 이하로 입력하세요.'
                        },
                        csatReflectedScore: {
                            required: '수능 반영 점수를 입력하세요.'
                        },
                        physicalReflectedScore: {
                            required: '실기 반영 점수를 입력하세요.'
                        },
                        internalReflectedScore: {
                            required: '내신 반영 점수를 입력하세요.'
                        },
                        interviewReflectedScore: {
                            required: '면접 반영 점수를 입력하세요.'
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