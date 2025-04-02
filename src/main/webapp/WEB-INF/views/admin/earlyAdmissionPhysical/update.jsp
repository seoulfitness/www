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
                                <form id="updateForm" action="/admin/earlyAdmissionPhysical/update?admissionId=${admissionId}" method="post" enctype="multipart/form-data">
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            ${admission.admissionYear}년 ${admission.admissionType}군 ${admission.schoolName} ${admission.departmentName} 수시 입시 실기 정보 수정 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">
                                            <div id="subjectBlock">
                                                <div class="mb-3 row">
                                                    <div class="col-md-3">
                                                        <label class="small mb-1" for="useSubject1">교과목 1 사용 여부 <span class="text-danger">*</span></label>
                                                        <select class="form-control" id="useSubject1" name="useSubject1">
                                                            <option value="Y" selected>사용</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <label class="small mb-1" for="subject1Id">교과목 1 선택 <span class="text-danger">*</span></label>
                                                        <select class="form-control" id="subject1Id" name="subject1Id">
                                                            <c:forEach var="physicalSubject" items="${physicalSubjects}">
                                                                <option value="${physicalSubject.physicalSubjectId}" ${earlyAdmissionPhysical.subject1Id == physicalSubject.physicalSubjectId ? 'selected' : ''}>${physicalSubject.physicalSubjectName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label class="small mb-1" for="subject1EvaluationMethod">평가 방법 <span class="text-danger">*</span></label>
                                                        <select class="form-control" id="subject1EvaluationMethod" name="subject1EvaluationMethod">
                                                            <option value="1" ${earlyAdmissionPhysical.subject1EvaluationMethod == '1' ? 'selected' : ''}>절대평가</option>
                                                            <option value="2" ${earlyAdmissionPhysical.subject1EvaluationMethod == '2' ? 'selected' : ''}>상대평가</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <c:forEach var="i" begin="2" end="10">
                                                    <div class="mb-3 row">
                                                        <div class="col-md-3">
                                                            <label class="small mb-1" for="useSubject${i}">교과목 ${i} 사용 여부 <span class="text-danger">*</span></label>
                                                            <select class="form-control" id="useSubject${i}" name="useSubject${i}">
                                                                <option value="Y" ${earlyAdmissionPhysical['useSubject'.concat(i)] == 'Y' ? 'selected' : ''}>사용</option>
                                                                <option value="N" ${earlyAdmissionPhysical['useSubject'.concat(i)] == 'N' ? 'selected' : ''}>미사용</option>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="small mb-1" for="subject${i}Id">교과목 ${i} 선택 <span class="text-danger">*</span></label>
                                                            <select class="form-control" id="subject${i}Id" name="subject${i}Id">
                                                                <c:forEach var="physicalSubject" items="${physicalSubjects}">
                                                                    <option value="${physicalSubject.physicalSubjectId}" ${earlyAdmissionPhysical['subject'.concat(i).concat('Id')] == physicalSubject.physicalSubjectId ? 'selected' : ''}>${physicalSubject.physicalSubjectName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <label class="small mb-1" for="subject${i}EvaluationMethod">평가 방법 <span class="text-danger">*</span></label>
                                                            <select class="form-control" id="subject${i}EvaluationMethod" name="subject${i}EvaluationMethod">
                                                                <option value="1" ${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == '1' ? 'selected' : ''}>절대평가</option>
                                                                <option value="2" ${earlyAdmissionPhysical['subject'.concat(i).concat('EvaluationMethod')] == '2' ? 'selected' : ''}>상대평가</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div class="mb-3">
                                                <label for="earlyAdmissionPhysicalMemo" class="form-label">메모</label>
                                                <textarea class="form-control" id="earlyAdmissionPhysicalMemo" name="earlyAdmissionPhysicalMemo" placeholder="메모" value="${earlyAdmissionPhysical.earlyAdmissionPhysicalMemo}"></textarea>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">수시 입시 실기 정보 수정</button>
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
                /*
                function init() {   
                    // 교과목 2부터 10까지 사용 여부를 모두 사용안함으로 설정
                    for (let i=2; i<11; i++) {
                        $('#useSubject'+i).val('N');
                        $('#useSubject'+i).prop('disabled', true);
                        $('#subject'+i+'Id').prop('disabled', true);                        
                        $('#subject'+i+'EvaluationMethod').prop('disabled', true);
                    }

                    // 교과목 2 사용여부 disabled 해제
                    $('#useSubject2').val('N');
                    $('#useSubject2').prop('disabled', false);
                    $('#subject2Id').prop('disabled', true);
                    $('#subject2EvaluationMethod').prop('disabled', true);
                }
        
                init();
                */

                // 교과목 i 사용 여부 변경 시 교과목 (i+1) 선택 여부 변경
                for (let i=2; i<11; i++) {
                    $('#useSubject'+i).change(function() {
                        let useSubject = $(this).val();
                        if (useSubject == 'Y') {
                            // 교과목 i 선택, 평가방법 disabled 해제
                            $('#subject' + i + 'Id').prop('disabled', false);
                            $('#subject' + i + 'EvaluationMethod').prop('disabled', false);
                            // 교과목 (i+1) 사용 여부 disabled 해제
                            $('#useSubject' + (i+1)).val('N');
                            $('#useSubject' + (i+1)).prop('disabled', false);
                            $('#subject' + (i+1) + 'Id').prop('disabled', false);
                            $('#subject' + (i+1) + 'EvaluationMethod').prop('disabled', false);
                        } else {
                            // 교과목 i 선택, 평가방법 disabled
                            $('#subject' + i + 'Id').prop('disabled', true);
                            $('#subject' + i + 'EvaluationMethod').prop('disabled', true);
                            // 교과목 (i+1)부터 사용 여부 disabled
                            for (let j=i+1; j<11; j++) {
                                $('#useSubject' + j).val('N');
                                $('#useSubject' + j).prop('disabled', true);
                                $('#subject' + j + 'Id').prop('disabled', true);
                                $('#subject' + j + 'EvaluationMethod').prop('disabled', true);
                            }
                        }
                    });
                }

                // notEqual 메소드(교과목 중복 검사)
                jQuery.validator.addMethod("notEqual", function(value, element, param) {
                    // 사용하지 않는 교과목이라면 검증하지 않음
                    const subjectNum = element.id.match(/\d+/)[0]; // 교과목 번호 추출 (예: 'subject3Id'에서 '3' 추출)
                    if ($('#useSubject' + subjectNum).val() !== 'Y') {
                        return true;
                    }

                    // param이 배열인 경우
                    if (Array.isArray(param)) {
                        for (let i = 0; i < param.length; i++) {
                            const compareVal = $(param[i]).val();
                            // 비교 대상 교과목이 사용중이고, 현재 교과목 값과 같다면 유효하지 않음
                            const compareNum = param[i].match(/\d+/)[0];
                            if ($('#useSubject' + compareNum).val() === 'Y' && value === compareVal) {
                                return false;
                            }
                        }
                        return true;
                    } 
                    // param이 단일 선택자인 경우
                    else {
                        const compareVal = $(param).val();
                        const compareNum = param.match(/\d+/)[0];
                        return $('#useSubject' + compareNum).val() !== 'Y' || value !== compareVal;
                    }
                }, "중복되는 교과목이 있습니다.");


                // 수시 입시 실기 정보 수정
                $('#updateForm').validate({
                    rules: {
                        subject2Id: {
                            notEqual: '#subject1Id'
                        },
                        subject3Id: {
                            notEqual: ['#subject1Id', '#subject2Id']
                        },
                        subject4Id: {
                            notEqual: ['#subject1Id', '#subject2Id', '#subject3Id']
                        },
                        subject5Id: {
                            notEqual: ['#subject1Id', '#subject2Id', '#subject3Id', '#subject4Id']
                        },
                        subject6Id: {
                            notEqual: ['#subject1Id', '#subject2Id', '#subject3Id', '#subject4Id', '#subject5Id']
                        },
                        subject7Id: {
                            notEqual: ['#subject1Id', '#subject2Id', '#subject3Id', '#subject4Id', '#subject5Id', '#subject6Id']
                        },
                        subject8Id: {
                            notEqual: ['#subject1Id', '#subject2Id', '#subject3Id', '#subject4Id', '#subject5Id', '#subject6Id', '#subject7Id']
                        },
                        subject9Id: {
                            notEqual: ['#subject1Id', '#subject2Id', '#subject3Id', '#subject4Id', '#subject5Id', '#subject6Id', '#subject7Id', '#subject8Id']
                        },
                        subject10Id: {
                            notEqual: ['#subject1Id', '#subject2Id', '#subject3Id', '#subject4Id', '#subject5Id', '#subject6Id', '#subject7Id', '#subject8Id', '#subject9Id']
                        },
                        earlyAdmissionMemo: {
                            maxlength: 500
                        },
                    },
                    messages: {
                        earlyAdmissionMemo: {
                            maxlength: '500자 이하로 입력하세요.'
                        },
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

                // 교과목 선택 변경 시 validation 강제 실행
                $('[id^=subject][id$=Id]').change(function() {
                    $('#updateForm').valid();
                });
            });
        </script>
    </body>
</html> 
