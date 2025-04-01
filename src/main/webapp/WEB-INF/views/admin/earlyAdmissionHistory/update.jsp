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
                                <form id="updateForm" action="/admin/earlyAdmissionHistory/update?admissionId=${admissionId}" method="post" enctype="multipart/form-data">
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            ${admission.admissionYear}년 ${admission.admissionType}군 ${admission.schoolName} ${admission.departmentName} 수시 입시 수정 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">
                                            <div class="mb-3">
                                                <label class="small mb-1" for="subject20ReflectedGrade1">한국사 등급 1 점수 <span class="text-danger">(감점일 경우 -를 붙여주세요)</span></label>
                                                <input class="form-control" id="subject20ReflectedGrade1" name="subject20ReflectedGrade1" type="number" value="${empty earlyAdmissionHistory.subject20ReflectedGrade1 ? '0.000' : earlyAdmissionHistory.subject20ReflectedGrade1}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="subject20ReflectedGrade2">한국사 등급 2 점수 <span class="text-danger">(감점일 경우 -를 붙여주세요)</span></label>
                                                <input class="form-control" id="subject20ReflectedGrade2" name="subject20ReflectedGrade2" type="number" value="${empty earlyAdmissionHistory.subject20ReflectedGrade2 ? '0.000' : earlyAdmissionHistory.subject20ReflectedGrade2}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="subject20ReflectedGrade3">한국사 등급 3 점수 <span class="text-danger">(감점일 경우 -를 붙여주세요)</span></label>
                                                <input class="form-control" id="subject20ReflectedGrade3" name="subject20ReflectedGrade3" type="number" value="${empty earlyAdmissionHistory.subject20ReflectedGrade3 ? '0.000' : earlyAdmissionHistory.subject20ReflectedGrade3}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="subject20ReflectedGrade4">한국사 등급 4 점수 <span class="text-danger">(감점일 경우 -를 붙여주세요)</span></label>
                                                <input class="form-control" id="subject20ReflectedGrade4" name="subject20ReflectedGrade4" type="number" value="${empty earlyAdmissionHistory.subject20ReflectedGrade4 ? '0.000' : earlyAdmissionHistory.subject20ReflectedGrade4}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="subject20ReflectedGrade5">한국사 등급 5 점수 <span class="text-danger">(감점일 경우 -를 붙여주세요)</span></label>
                                                <input class="form-control" id="subject20ReflectedGrade5" name="subject20ReflectedGrade5" type="number" value="${empty earlyAdmissionHistory.subject20ReflectedGrade5 ? '0.000' : earlyAdmissionHistory.subject20ReflectedGrade5}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="subject20ReflectedGrade6">한국사 등급 6 점수 <span class="text-danger">(감점일 경우 -를 붙여주세요)</span></label>
                                                <input class="form-control" id="subject20ReflectedGrade6" name="subject20ReflectedGrade6" type="number" value="${empty earlyAdmissionHistory.subject20ReflectedGrade6 ? '0.000' : earlyAdmissionHistory.subject20ReflectedGrade6}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="subject20ReflectedGrade7">한국사 등급 7 점수 <span class="text-danger">(감점일 경우 -를 붙여주세요)</span></label>
                                                <input class="form-control" id="subject20ReflectedGrade7" name="subject20ReflectedGrade7" type="number" value="${empty earlyAdmissionHistory.subject20ReflectedGrade7 ? '0.000' : earlyAdmissionHistory.subject20ReflectedGrade7}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="subject20ReflectedGrade8">한국사 등급 8 점수 <span class="text-danger">(감점일 경우 -를 붙여주세요)</span></label>
                                                <input class="form-control" id="subject20ReflectedGrade8" name="subject20ReflectedGrade8" type="number" value="${empty earlyAdmissionHistory.subject20ReflectedGrade8 ? '0.000' : earlyAdmissionHistory.subject20ReflectedGrade8}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="subject20ReflectedGrade9">한국사 등급 9 점수 <span class="text-danger">(감점일 경우 -를 붙여주세요)</span></label>
                                                <input class="form-control" id="subject20ReflectedGrade9" name="subject20ReflectedGrade9" type="number" value="${empty earlyAdmissionHistory.subject20ReflectedGrade9 ? '0.000' : earlyAdmissionHistory.subject20ReflectedGrade9}" />
                                            </div>
                                            <div class="mb-3">
                                                <label for="earlyAdmissionHistoryMemo" class="form-label">메모</label>
                                                <textarea class="form-control" id="earlyAdmissionHistoryMemo" name="earlyAdmissionHistoryMemo" placeholder="메모" value="${empty earlyAdmissionHistory.earlyAdmissionHistoryMemo ? '' : earlyAdmissionHistory.earlyAdmissionHistoryMemo}"></textarea>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">수시 입시 정보 수정</button>
                                            <a href="/admin/earlyAdmissionHistory/${earlyAdmissionHistory.earlyAdmissionHistoryId}" class="btn btn-outline-danger">취소</a>
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
                // 수시 입시 한국사 정보 수정
                $('#updateForm').validate({
                    rules: {
                        subject20ReflectedGrade1: {
                            required: true,
                            number: true,
                            min: -2000,
                            max: 2000,
                            threeDecimalPlaces: true
                        },
                        subject20ReflectedGrade2: {
                            required: true,
                            number: true,
                            min: -2000,
                            max: 2000,
                            threeDecimalPlaces: true
                        },
                        subject20ReflectedGrade3: {
                            required: true,
                            number: true,
                            min: -2000,
                            max: 2000,
                            threeDecimalPlaces: true
                        },
                        subject20ReflectedGrade4: {
                            required: true,
                            number: true,
                            min: -2000,
                            max: 2000,
                            threeDecimalPlaces: true
                        },
                        subject20ReflectedGrade5: {
                            required: true,
                            number: true,
                            min: -2000,
                            max: 2000,
                            threeDecimalPlaces: true
                        },
                        subject20ReflectedGrade6: {
                            required: true,
                            number: true,
                            min: -2000,
                            max: 2000,
                            threeDecimalPlaces: true
                        },
                        subject20ReflectedGrade7: {
                            required: true,
                            number: true,
                            min: -2000,
                            max: 2000,
                            threeDecimalPlaces: true
                        },
                        subject20ReflectedGrade8: {
                            required: true,
                            number: true,
                            min: -2000,
                            max: 2000,
                            threeDecimalPlaces: true
                        },
                        subject20ReflectedGrade9: {
                            required: true,
                            number: true,
                            min: -2000,
                            max: 2000,
                            threeDecimalPlaces: true
                        },
                        earlyAdmissionHistoryMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        subject20ReflectedGrade1: {
                            required: "한국사 1등급 점수를 입력하세요.",
                            number: "숫자만 입력 가능합니다.",
                            min: "-2000 이상의 값을 입력하세요.",
                            max: "2000 이하의 값을 입력하세요.",
                            threeDecimalPlaces: "소수점 셋째 자리까지만 입력 가능합니다."
                        },
                        subject20ReflectedGrade2: {
                            required: "한국사 2등급 점수를 입력하세요.",
                            number: "숫자만 입력 가능합니다.",
                            min: "-2000 이상의 값을 입력하세요.",
                            max: "2000 이하의 값을 입력하세요.",
                            threeDecimalPlaces: "소수점 셋째 자리까지만 입력 가능합니다."
                        },
                        subject20ReflectedGrade3: {
                            required: "한국사 3등급 점수를 입력하세요.",
                            number: "숫자만 입력 가능합니다.",
                            min: "-2000 이상의 값을 입력하세요.",
                            max: "2000 이하의 값을 입력하세요.",
                            threeDecimalPlaces: "소수점 셋째 자리까지만 입력 가능합니다."
                        },
                        subject20ReflectedGrade4: {
                            required: "한국사 4등급 점수를 입력하세요.",
                            number: "숫자만 입력 가능합니다.",
                            min: "-2000 이상의 값을 입력하세요.",
                            max: "2000 이하의 값을 입력하세요.",
                            threeDecimalPlaces: "소수점 셋째 자리까지만 입력 가능합니다."
                        },
                        subject20ReflectedGrade5: {
                            required: "한국사 5등급 점수를 입력하세요.",
                            number: "숫자만 입력 가능합니다.",
                            min: "-2000 이상의 값을 입력하세요.",
                            max: "2000 이하의 값을 입력하세요.",
                            threeDecimalPlaces: "소수점 셋째 자리까지만 입력 가능합니다."
                        },
                        subject20ReflectedGrade6: {
                            required: "한국사 6등급 점수를 입력하세요.",
                            number: "숫자만 입력 가능합니다.",
                            min: "-2000 이상의 값을 입력하세요.",
                            max: "2000 이하의 값을 입력하세요.",
                            threeDecimalPlaces: "소수점 셋째 자리까지만 입력 가능합니다."
                        },
                        subject20ReflectedGrade7: {
                            required: "한국사 7등급 점수를 입력하세요.",
                            number: "숫자만 입력 가능합니다.",
                            min: "-2000 이상의 값을 입력하세요.",
                            max: "2000 이하의 값을 입력하세요.",
                            threeDecimalPlaces: "소수점 셋째 자리까지만 입력 가능합니다."
                        },
                        subject20ReflectedGrade8: {
                            required: "한국사 8등급 점수를 입력하세요.",
                            number: "숫자만 입력 가능합니다.",
                            min: "-2000 이상의 값을 입력하세요.",
                            max: "2000 이하의 값을 입력하세요.",
                            threeDecimalPlaces: "소수점 셋째 자리까지만 입력 가능합니다."
                        },
                        subject20ReflectedGrade9: {
                            required: "한국사 9등급 점수를 입력하세요.",
                            number: "숫자만 입력 가능합니다.",
                            min: "-2000 이상의 값을 입력하세요.",
                            max: "2000 이하의 값을 입력하세요.",
                            threeDecimalPlaces: "소수점 셋째 자리까지만 입력 가능합니다."
                        },
                        earlyAdmissionHistoryMemo: {
                            maxlength: '500자 이하로 입력하세요.'
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