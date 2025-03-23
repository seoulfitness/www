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
                                <form id="createForm" action="/admin/highSchools/create" method="post">
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            고등학교 등록 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                        
                                            <div class="mb-3">
                                                <label class="small mb-1" for="highSchoolName">고등학교명<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="highSchoolName" id="highSchoolName" type="text" placeholder="고등학교명을 입력하세요." value="${highSchool.highSchoolName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="provinceId">시/도<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="provinceId" id="provinceId">
                                                    <option value="">시/도를 선택하세요.</option>
                                                    <c:forEach items="${provinces}" var="province">
                                                        <option value="${province.provinceId}" ${highSchool.provinceId == province.provinceId ? 'selected' : ''}>${province.provinceName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="districtId">구/군<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="districtId" id="districtId">
                                                    <option value="">구/군을 선택하세요.</option>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="highSchoolAddress">주소</label>
                                                <input class="form-control" name="highSchoolAddress" id="highSchoolAddress" type="text" placeholder="주소를 입력하세요." value="${highSchool.highSchoolAddress}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="highSchoolPhone">전화번호</label>
                                                <input class="form-control" name="highSchoolPhone" id="highSchoolPhone" type="text" placeholder="전화번호를 입력하세요." value="${highSchool.highSchoolPhone}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="highSchoolWebsite">웹사이트</label>
                                                <input class="form-control" name="highSchoolWebsite" id="highSchoolWebsite" type="url" placeholder="웹사이트 주소를 입력하세요." value="${highSchool.highSchoolWebsite}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="highSchoolMemo">메모</label>
                                                <textarea class="form-control" name="highSchoolMemo" id="highSchoolMemo" rows="5" placeholder="메모를 입력하세요.">${highSchool.highSchoolMemo}</textarea>
                                            </div>                                            
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">고등학교 등록</button>
                                            <a href="/admin/highSchools" class="btn btn-outline-danger">등록 취소</a>
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
                    if ($('select[name="province"]').val() != "") {
                        var province_id = $('select[name="province"]').val();
                        $.ajax({
                            url: '/admin/districts',
                            type: 'get',
                            dataType: 'json',
                            data: {
                                'provinceId': province_id
                            },                            
                            success: function(data) {
                                $('select[name="district"]').empty();
                                $('select[name="district"]').append('<option value="" selected disabled>구/군을 선택하세요.</option>');
                                $.each(data, function(index, district) {
                                    $('select[name="district"]').append('<option value="' + district.districtId + '">' + district.districtName + '</option>');
                                });
                            },
                            error: function(xhr, status, error) {
                                console.log(xhr.responseText);
                            }
                        });
                    }
                }
                init();

                $("#createForm").validate({
                    rules: {
                        highSchoolName: {
                            required: true,
                            minlength: 2,
                            maxlength: 50
                        },
                        provinceId: {
                            required: true
                        },
                        districtId: {
                            required: true
                        },
                        highSchoolWebsite: {
                            url: true,
                            maxlength: 200
                        },
                        highSchoolMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        highSchoolName: {
                            required: "고등학교명을 입력해주세요.",
                            minlength: "고등학교명은 최소 2자 이상이어야 합니다.",
                            maxlength: "고등학교명은 최대 50자 이하여야 합니다."
                        },
                        provinceId: {
                            required: "시/도를 선택해주세요."
                        },
                        districtId: {
                            required: "구/군을 선택해주세요."
                        },
                        highSchoolWebsite: {
                            url: "올바른 URL 형식이 아닙니다.",
                            maxlength: "웹사이트 주소는 최대 200자 이하여야 합니다."
                        },
                        highSchoolMemo: {
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

                // 시/도 선택 시 구/군 목록 로드
                $("#provinceId").change(function() {
                    var provinceId = $(this).val();
                    if (provinceId) {
                        $.get("/admin/districts/list/" + provinceId, function(districts) {
                            var options = '<option value="">구/군을 선택하세요.</option>';
                            districts.forEach(function(district) {
                                options += '<option value="' + district.districtId + '">' + district.districtName + '</option>';
                            });
                            $("#districtId").html(options);
                        });
                    } else {
                        $("#districtId").html('<option value="">구/군을 선택하세요.</option>');
                    }
                });
            });
        </script>
    </body>
</html> 