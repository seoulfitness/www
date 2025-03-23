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
                                <%-- 구/군 수정 --%>
                                <form id="updateForm" action="/admin/districts/${district.districtId}/update" method="post">
                                    <input type="hidden" name="districtId" value="${district.districtId}" />
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            구/군 수정 (<span class="text-danger small">*</span> 표시는 필수 입력 항목입니다.)
                                        </div>
                                        <div class="card-body">                                        
                                            <div class="mb-3">
                                                <label class="small mb-1" for="provinceId">시/도<span class="text-danger small">*</span></label>
                                                <select class="form-control" name="provinceId" id="provinceId">
                                                    <option value="">시/도를 선택하세요.</option>
                                                    <c:forEach items="${provinces}" var="province">
                                                        <option value="${province.provinceId}" ${district.provinceId == province.provinceId ? 'selected' : ''}>${province.provinceName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="districtName">구/군명<span class="text-danger small">*</span></label>
                                                <input class="form-control" name="districtName" id="districtName" type="text" placeholder="구/군명을 입력하세요." value="${district.districtName}" />
                                            </div>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="districtMemo">메모</label>
                                                <textarea class="form-control" name="districtMemo" id="districtMemo" rows="3" placeholder="메모를 입력하세요.">${district.districtMemo}</textarea>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-primary" type="submit">구/군 수정</button>
                                            <a href="/admin/districts" class="btn btn-outline-danger">수정 취소</a>
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
                $("#updateForm").validate({
                    rules: {
                        provinceId: {
                            required: true
                        },
                        districtName: {
                            required: true,
                            minlength: 2,
                            maxlength: 50
                        },
                        districtMemo: {
                            maxlength: 500
                        }
                    },
                    messages: {
                        provinceId: {
                            required: "시/도를 선택해주세요."
                        },
                        districtName: {
                            required: "구/군명을 입력해주세요.",
                            minlength: "구/군명은 최소 2자 이상이어야 합니다.",
                            maxlength: "구/군명은 최대 50자 이하여야 합니다."
                        },
                        districtMemo: {
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
