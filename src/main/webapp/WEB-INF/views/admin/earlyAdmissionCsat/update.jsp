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
                                <form id="updateForm" action="/admin/earlyAdmissionCsat/${earlyAdmissionCsat.earlyAdmissionCsatId}/update?admissionId=${earlyAdmissionCsat.admissionId}" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="earlyAdmissionCsatId" value="${earlyAdmissionCsat.earlyAdmissionCsatId}" />
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            ${admission.admissionYear}년 ${admission.admissionType}군 ${admission.schoolName} ${admission.departmentName} 수시 입시 수능 정보 수정 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                        
                                            <div class="mb-3">
                                                <label for="scoreType" class="form-label">점수 반영 구분 <span class="text-danger small">*</span></label>
                                                <select class="form-select" id="scoreType" name="scoreType" value="${earlyAdmissionCsat.scoreType}">
                                                    <option value="1" <c:if test="${earlyAdmissionCsat.scoreType == '1'}">selected</c:if>>표준점수</option>
                                                    <option value="2" <c:if test="${earlyAdmissionCsat.scoreType == '2'}">selected</c:if>>백분위</option>
                                                    <option value="3" <c:if test="${earlyAdmissionCsat.scoreType == '3'}">selected</c:if>>기타방법</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label for="scoreType2" class="form-label">탐구 교과목 반영 구분 <span class="text-danger small">*</span></label>
                                                <select class="form-select" id="scoreType2" name="scoreType2" value="${earlyAdmissionCsat.scoreType2}">
                                                    <option value="1" <c:if test="${earlyAdmissionCsat.scoreType2 == '1'}">selected</c:if>>동일 과목 허용</option>
                                                    <option value="2" <c:if test="${earlyAdmissionCsat.scoreType2 == '2'}">selected</c:if>>동일 과목 미허용</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject1ReflectedScore" class="form-label">국어 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject1ReflectedScore ? earlyAdmissionCsat.subject1ReflectedScore : '0.00'}" id="subject1ReflectedScore" name="subject1ReflectedScore" placeholder="국어 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject2ReflectedScore" class="form-label">수학 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject2ReflectedScore ? earlyAdmissionCsat.subject2ReflectedScore : '0.00'}" id="subject2ReflectedScore" name="subject2ReflectedScore" placeholder="수학 반영 점수">
                                            </div>                                            
                                            <div class="mb-3">
                                                <label for="subject3ReflectedScore" class="form-label">사회문화 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject3ReflectedScore ? earlyAdmissionCsat.subject3ReflectedScore : '0.00'}" id="subject3ReflectedScore" name="subject3ReflectedScore" placeholder="사회문화 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject4ReflectedScore" class="form-label">생활과윤리 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject4ReflectedScore ? earlyAdmissionCsat.subject4ReflectedScore : '0.00'}" id="subject4ReflectedScore" name="subject4ReflectedScore" placeholder="생활과윤리 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject5ReflectedScore" class="form-label">윤리와사상 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject5ReflectedScore ? earlyAdmissionCsat.subject5ReflectedScore : '0.00'}" id="subject5ReflectedScore" name="subject5ReflectedScore" placeholder="윤리와사상 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject6ReflectedScore" class="form-label">정치와법 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject6ReflectedScore ? earlyAdmissionCsat.subject6ReflectedScore : '0.00'}" id="subject6ReflectedScore" name="subject6ReflectedScore" placeholder="정치와법 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject7ReflectedScore" class="form-label">경제 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject7ReflectedScore ? earlyAdmissionCsat.subject7ReflectedScore : '0.00'}" id="subject7ReflectedScore" name="subject7ReflectedScore" placeholder="경제 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject8ReflectedScore" class="form-label">한국지리 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject8ReflectedScore ? earlyAdmissionCsat.subject8ReflectedScore : '0.00'}" id="subject8ReflectedScore" name="subject8ReflectedScore" placeholder="한국지리 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject9ReflectedScore" class="form-label">세계지리 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject9ReflectedScore ? earlyAdmissionCsat.subject9ReflectedScore : '0.00'}" id="subject9ReflectedScore" name="subject9ReflectedScore" placeholder="세계지리 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject10ReflectedScore" class="form-label">세계사 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject10ReflectedScore ? earlyAdmissionCsat.subject10ReflectedScore : '0.00'}" id="subject10ReflectedScore" name="subject10ReflectedScore" placeholder="세계사 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject11ReflectedScore" class="form-label">동아시아사 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject11ReflectedScore ? earlyAdmissionCsat.subject11ReflectedScore : '0.00'}" id="subject11ReflectedScore" name="subject11ReflectedScore" placeholder="동아시아사 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject12ReflectedScore" class="form-label">화학1 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject12ReflectedScore ? earlyAdmissionCsat.subject12ReflectedScore : '0.00'}" id="subject12ReflectedScore" name="subject12ReflectedScore" placeholder="화학1 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject13ReflectedScore" class="form-label">화학2 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject13ReflectedScore ? earlyAdmissionCsat.subject13ReflectedScore : '0.00'}" id="subject13ReflectedScore" name="subject13ReflectedScore" placeholder="화학2 반영 점수">
                                            </div>  
                                            <div class="mb-3">
                                                <label for="subject14ReflectedScore" class="form-label">생명과학1 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject14ReflectedScore ? earlyAdmissionCsat.subject14ReflectedScore : '0.00'}" id="subject14ReflectedScore" name="subject14ReflectedScore" placeholder="생명과학1 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject15ReflectedScore" class="form-label">생명과학2 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject15ReflectedScore ? earlyAdmissionCsat.subject15ReflectedScore : '0.00'}" id="subject15ReflectedScore" name="subject15ReflectedScore" placeholder="생명과학2 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject16ReflectedScore" class="form-label">물리1 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject16ReflectedScore ? earlyAdmissionCsat.subject16ReflectedScore : '0.00'}" id="subject16ReflectedScore" name="subject16ReflectedScore" placeholder="물리1 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject17ReflectedScore" class="form-label">물리2 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject17ReflectedScore ? earlyAdmissionCsat.subject17ReflectedScore : '0.00'}" id="subject17ReflectedScore" name="subject17ReflectedScore" placeholder="물리2 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject18ReflectedScore" class="form-label">지구과학1 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject18ReflectedScore ? earlyAdmissionCsat.subject18ReflectedScore : '0.00'}" id="subject18ReflectedScore" name="subject18ReflectedScore" placeholder="지구과학1 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject19ReflectedScore" class="form-label">지구과학2 반영 점수 <span class="text-danger small">*</span></label>
                                                <input type="number" class="form-control" min="0.00" max="200.00" step="0.01" value="${not empty earlyAdmissionCsat.subject19ReflectedScore ? earlyAdmissionCsat.subject19ReflectedScore : '0.00'}" id="subject19ReflectedScore" name="subject19ReflectedScore" placeholder="지구과학2 반영 점수">
                                            </div>
                                            <div class="mb-3">
                                                <label for="earlyAdmissionCsatMemo" class="form-label">메모</label>
                                                <textarea class="form-control" id="earlyAdmissionCsatMemo" name="earlyAdmissionCsatMemo" placeholder="메모" value="${earlyAdmissionCsat.earlyAdmissionCsatMemo}"></textarea>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">수시 입시 수능 정보 수정</button>
                                            <a href="/admin/admissions/${earlyAdmissionCsat.admissionId}#earlyAdmissionCsat" class="btn btn-outline-danger">취소</a>
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
                // 수시 입시 수능 정보 수정
                $('#updateForm').validate({
                    rules: {
                        subject1ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject2ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject3ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject4ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },  
                        subject5ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject6ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject7ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject8ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject9ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject10ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject11ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject12ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject13ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject14ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject15ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject16ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject17ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject18ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },
                        subject19ReflectedScore: {
                            required: true,
                            number: true,
                            min: 0,
                            max: 200
                        },                        
                        earlyAdmissionCsatMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        subject1ReflectedScore: {
                            required: '국어 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject2ReflectedScore: {
                            required: '수학 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject3ReflectedScore: {
                            required: '사회문화 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject4ReflectedScore: {
                            required: '생활과윤리 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject5ReflectedScore: {
                            required: '윤리와사상 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject6ReflectedScore: {
                            required: '정치와법 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },  
                        subject7ReflectedScore: {
                            required: '경제 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject8ReflectedScore: {
                            required: '한국지리 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject9ReflectedScore: {
                            required: '세계지리 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject10ReflectedScore: {
                            required: '세계사 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject11ReflectedScore: {
                            required: '동아시아사 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject12ReflectedScore: {
                            required: '화학1 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject13ReflectedScore: {
                            required: '화학2 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject14ReflectedScore: {
                            required: '생명과학1 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject15ReflectedScore: {
                            required: '생명과학2 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject16ReflectedScore: {
                            required: '물리1 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject17ReflectedScore: {
                            required: '물리2 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject18ReflectedScore: {
                            required: '지구과학1 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        subject19ReflectedScore: {
                            required: '지구과학2 반영 점수를 입력하세요.',
                            number: '숫자를 입력하세요.',
                            min: '0.00 이상 입력하세요.',
                            max: '200.00 이하 입력하세요.'
                        },
                        earlyAdmissionCsatMemo: {
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