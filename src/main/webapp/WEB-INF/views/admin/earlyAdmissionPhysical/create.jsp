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
                                <form id="createForm" action="/admin/earlyAdmissionsPhysical/create?admissionId=${admissionId}" method="post" enctype="multipart/form-data">
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            ${admission.admissionYear}년 ${admission.admissionType}군 ${admission.schoolName} ${admission.departmentName} 수시 입시 실기 정보 등록 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">     
                                            <!-- 안내 -->
                                            <div class="mb-3" id="errorMessage" style="display: none;">
                                                <div class="alert alert-danger">
                                                    <div class="mb-1">하나 이상의 교과목을 등록해 주세요.</div>
                                                </div>
                                            </div>
                                            <div class="mb-3" id="subjectError" style="display: none;">
                                                <div class="alert alert-danger">
                                                    <div class="mb-1">중복되는 교과목이 있습니다.</div>
                                                </div>
                                            </div>
                                            <!--// 안내 -->
                                            <div id="subjectBlock">
                                                <c:forEach var="i" begin="1" end="10">
                                                    <div class="mb-3 row">
                                                        <div class="col-md-3">
                                                            <label class="small mb-1" for="useSubject${i}">교과목 ${i} 사용 여부 <span class="text-danger">*</span></label>
                                                            <select class="form-control" id="useSubject${i}" name="useSubject${i}">
                                                                <option value="Y" ${earlyAdmissionPhysical['useSubject' + i] == 'Y' ? 'selected' : ''}>사용</option>
                                                                <option value="N" ${earlyAdmissionPhysical['useSubject' + i] == 'N' ? 'selected' : ''}>미사용</option>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="small mb-1" for="subject${i}Id">교과목 ${i} 선택 <span class="text-danger">*</span></label>
                                                            <select class="form-control" id="subject${i}Id" name="subject${i}Id">
                                                                <c:forEach var="physicalSubject" items="${physicalSubjects}">
                                                                    <option value="${physicalSubject.physicalSubjectId}" ${earlyAdmissionPhysical['subject' + i] == physicalSubject.physicalSubjectId ? 'selected' : ''}>${physicalSubject.physicalSubjectName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="small mb-1" for="subject${i}EvaluationMethod">평가 방법 <span class="text-danger">*</span></label>
                                                            <select class="form-control" id="subject${i}EvaluationMethod" name="subject${i}EvaluationMethod">
                                                                <option value="1" ${earlyAdmissionPhysical['subject' + i + 'EvaluationMethod'] == '1' ? 'selected' : ''}>절대평가</option>
                                                                <option value="2" ${earlyAdmissionPhysical['subject' + i + 'EvaluationMethod'] == '2' ? 'selected' : ''}>상대평가</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div class="mb-3">
                                                <label for="earlyAdmissionMemo" class="form-label">메모</label>
                                                <textarea class="form-control" id="earlyAdmissionMemo" name="earlyAdmissionMemo" placeholder="메모" value="${earlyAdmission.earlyAdmissionMemo}"></textarea>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">수시 입시 실기 정보 등록</button>
                                            <a href="/admin/admissions/${admissionId}#earlyAdmissionPhysical" class="btn btn-outline-danger">취소</a>
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
                function init() {   
                    $('#useSubject1').val('1');
                    $('#useSubject1').prop('disabled', false);
                    $('#subject1Id').prop('disabled', false);
                    $('#subject1EvaluationMethod').prop('disabled', false);
                    for (let i=1; i<11; i++) {
                        $('#useSubject'+i).val('2');
                        $('#useSubject'+i).prop('disabled', true);
                        $('#subject'+i+'Id').prop('disabled', true);                        
                        $('#subject'+i+'EvaluationMethod').prop('disabled', true);
                    }
                    $('#useSubject2').prop('disabled', false);
                    $('#subject2Id').prop('disabled', false);
                    $('#subject2EvaluationMethod').prop('disabled', false);
                }
        
                init();

                // 교과목 사용 여부 변경 시 교과목 선택 여부 변경
                <c:forEach var="i" begin="1" end="10">
                    $('#useSubject${i}').change(function() {
                        let useSubject = $(this).val();
                        if (useSubject == 'Y') {
                            $('#subject${i}Id').prop('disabled', false);
                        } else {
                            $('#subject${i}Id').prop('disabled', true);
                        }
                    });
                </c:forEach>

                /*
                // 수시(입시) 정보 등록
                $('#createForm').validate({
                    rules: {
                        acceptedCount: {
                            required: true
                        },
                        earlyAdmissionMemo: {
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
                        earlyAdmissionMemo: {
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
                */
            });
        </script>
    </body>
</html> 