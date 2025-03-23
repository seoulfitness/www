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
                                <form id="createForm" action="/admin/criterias/create" method="post">
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            입시 요강 등록 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                            
                                            <div class="mb-3">
                                                <label class="small mb-1" for="year">연도<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="year" id="year">
                                                    <option value="">연도를 선택하세요.</option>
                                                    <c:forEach begin="2020" end="2030" var="year">
                                                        <option value="${year}" ${criteria.year == year ? 'selected' : ''}>${year}년</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="criteriaGubun">입시 요강 구분<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="criteriaGubun" id="criteriaGubun">
                                                    <option value="">입시 요강 구분을 선택하세요.</option>
                                                    <option value="수시" ${criteria.criteriaGubun == '수시' ? 'selected' : ''}>수시</option>
                                                    <option value="정시" ${criteria.criteriaGubun == '정시' ? 'selected' : ''}>정시</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="schoolId">학교<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="schoolId" id="schoolId">
                                                    <option value="">학교를 선택하세요.</option>
                                                    <c:forEach items="${schools}" var="school">
                                                        <option value="${school.schoolId}" ${criteria.schoolId == school.schoolId ? 'selected' : ''}>${school.schoolName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="departmentId">학과<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="departmentId" id="departmentId">
                                                    <option value="">학과를 선택하세요.</option>
                                                    <c:forEach items="${departments}" var="department">
                                                        <option value="${department.departmentId}" ${criteria.departmentId == department.departmentId ? 'selected' : ''}>${department.departmentName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="acceptedCount">합격자 수<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="acceptedCount" id="acceptedCount" type="number" min="1" placeholder="합격자 수를 입력하세요." value="${criteria.acceptedCount}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="csatReflectedScore">수능 반영 점수<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="csatReflectedScore" id="csatReflectedScore" type="number" step="0.1" min="0" max="1000" placeholder="수능 반영 점수를 입력하세요." value="${criteria.csatReflectedScore}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="physicalReflectedScore">실기 반영 점수<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="physicalReflectedScore" id="physicalReflectedScore" type="number" step="0.1" min="0" max="1000" placeholder="실기 반영 점수를 입력하세요." value="${criteria.physicalReflectedScore}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="internalReflectedScore">내신 반영 점수<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="internalReflectedScore" id="internalReflectedScore" type="number" step="0.1" min="0" max="1000" placeholder="내신 반영 점수를 입력하세요." value="${criteria.internalReflectedScore}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="interviewReflectedScore">면접 반영 점수<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="interviewReflectedScore" id="interviewReflectedScore" type="number" step="0.1" min="0" max="1000" placeholder="면접 반영 점수를 입력하세요." value="${criteria.interviewReflectedScore}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="totalReflectedScore">총 반영 점수<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="totalReflectedScore" id="totalReflectedScore" type="number" step="0.1" min="0" max="4000" placeholder="총 반영 점수를 입력하세요." value="${criteria.totalReflectedScore}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="criteriaMemo">메모</label>
                                                <textarea class="form-control" name="criteriaMemo" id="criteriaMemo" rows="3" placeholder="메모를 입력하세요.">${criteria.criteriaMemo}</textarea>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">입시 요강 등록</button>
                                            <a href="/admin/criterias" class="btn btn-outline-danger">등록 취소</a>
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
                        year: {
                            required: true
                        },
                        criteriaGubun: {
                            required: true
                        },
                        schoolId: {
                            required: true
                        },
                        departmentId: {
                            required: true
                        },
                        acceptedCount: {
                            required: true,
                            min: 1
                        },
                        csatReflectedScore: {
                            required: true,
                            min: 0,
                            max: 1000
                        },
                        physicalReflectedScore: {
                            required: true,
                            min: 0,
                            max: 1000
                        },
                        internalReflectedScore: {
                            required: true,
                            min: 0,
                            max: 1000
                        },
                        interviewReflectedScore: {
                            required: true,
                            min: 0,
                            max: 1000
                        },
                        totalReflectedScore: {
                            required: true,
                            min: 0,
                            max: 4000
                        },
                        criteriaMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        year: {
                            required: "연도를 선택해주세요."
                        },
                        criteriaGubun: {
                            required: "입시 요강 구분을 선택해주세요."
                        },
                        schoolId: {
                            required: "학교를 선택해주세요."
                        },
                        departmentId: {
                            required: "학과를 선택해주세요."
                        },
                        acceptedCount: {
                            required: "합격자 수를 입력해주세요.",
                            min: "합격자 수는 1명 이상이어야 합니다."
                        },
                        csatReflectedScore: {
                            required: "수능 반영 점수를 입력해주세요.",
                            min: "수능 반영 점수는 0점 이상이어야 합니다.",
                            max: "수능 반영 점수는 1000점 이하여야 합니다."
                        },
                        physicalReflectedScore: {
                            required: "실기 반영 점수를 입력해주세요.",
                            min: "실기 반영 점수는 0점 이상이어야 합니다.",
                            max: "실기 반영 점수는 1000점 이하여야 합니다."
                        },
                        internalReflectedScore: {
                            required: "내신 반영 점수를 입력해주세요.",
                            min: "내신 반영 점수는 0점 이상이어야 합니다.",
                            max: "내신 반영 점수는 1000점 이하여야 합니다."
                        },
                        interviewReflectedScore: {
                            required: "면접 반영 점수를 입력해주세요.",
                            min: "면접 반영 점수는 0점 이상이어야 합니다.",
                            max: "면접 반영 점수는 1000점 이하여야 합니다."
                        },
                        totalReflectedScore: {
                            required: "총 반영 점수를 입력해주세요.",
                            min: "총 반영 점수는 0점 이상이어야 합니다.",
                            max: "총 반영 점수는 4000점 이하여야 합니다."
                        },
                        criteriaMemo: {
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
