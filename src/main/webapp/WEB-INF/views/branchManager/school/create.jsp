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
                                <div class="card mb-4">
                                    <div class="card-header">
                                        학교 등록 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                    </div>
                                    <div class="card-body">
                                        <form id="createForm" action="/branchManager/schools/${school.schoolId}/create" method="post" enctype="multipart/form-data">
                                            <div class="mb-3">
                                                <label class="small mb-1" for="schoolName">학교명<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="schoolName" id="schoolName" type="text" placeholder="학교명을 입력하세요." value="${school.schoolName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="schoolAddress">주소</label>
                                                <input class="form-control" name="schoolAddress" id="schoolAddress" type="text" placeholder="주소를 입력하세요." value="${school.schoolAddress}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="schoolPhone">전화번호</label>
                                                <input class="form-control" name="schoolPhone" id="schoolPhone" type="text" placeholder="전화번호를 입력하세요." value="${school.schoolPhone}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="schoolWebsite">웹사이트</label>
                                                <input class="form-control" name="schoolWebsite" id="schoolWebsite" type="url" placeholder="웹사이트 주소를 입력하세요." value="${school.schoolWebsite}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="schoolLogoUrl">로고 URL</label>
                                                <input class="form-control" name="schoolLogoUrl" id="schoolLogoUrl" type="url" placeholder="학교 로고 URL을 입력하세요." value="${school.schoolLogoUrl}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="schoolLogoFile">로고 파일</label>
                                                <input class="form-control" name="schoolLogoFile" id="schoolLogoFile" type="file" accept="image/*" />
                                                <div class="form-text">이미지 파일만 업로드 가능합니다.</div>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="schoolLogo">로고 선택</label>
                                                <select class="form-control" name="schoolLogo" id="schoolLogo">
                                                    <option value="none" ${school.schoolLogo == 'none' ? 'selected' : ''}>선택 안함</option>
                                                    <option value="url" ${school.schoolLogo == 'url' ? 'selected' : ''}>URL</option>
                                                    <option value="file" ${school.schoolLogo == 'file' ? 'selected' : ''}>파일</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="schoolMemo">메모</label>
                                                <textarea class="form-control" name="schoolMemo" id="schoolMemo" rows="5" placeholder="메모를 입력하세요.">${school.schoolMemo}</textarea>
                                            </div>
                                            <div>
                                                <button class="btn btn-primary" type="submit">학교 등록</button>
                                                <a href="/branchManager/schools" class="btn btn-outline-danger">등록 취소</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
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
                        schoolName: {
                            required: true,
                            minlength: 2,
                            maxlength: 50
                        },
                        schoolWebsite: {
                            url: true,
                            maxlength: 200
                        },
                        schoolLogoUrl: {
                            url: true,
                            maxlength: 200
                        },
                        schoolMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        schoolName: {
                            required: "학교명을 입력해주세요.",
                            minlength: "학교명은 최소 2자 이상이어야 합니다.",
                            maxlength: "학교명은 최대 50자 이하여야 합니다."
                        },
                        schoolWebsite: {
                            url: "올바른 URL 형식이 아닙니다.",
                            maxlength: "웹사이트 주소는 최대 200자 이하여야 합니다."
                        },
                        schoolLogoUrl: {
                            url: "올바른 URL 형식이 아닙니다.",
                            maxlength: "학교 로고 URL은 최대 200자 이하여야 합니다."
                        },
                        schoolLogo: {
                            accept: "이미지 파일만 업로드 가능합니다."
                        },
                        schoolMemo: {
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