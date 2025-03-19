<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <%@ include file="../base/head.jsp" %>
    <body class="nav-fixed">
        <%@ include file="../base/nav.jsp" %>
        <div id="layoutSidenav">
            <%@ include file="../base/layoutSidenav_nav.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <%@ include file="../base/simple_header.jsp" %>
                    <%-- Main page content--%>
                    <div class="container-xl px-4 mt-4">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card mb-4">
                                    <div class="card-body">
                                        <%-- 검색 폼, 등록 버튼 --%>
                                        <div class="d-flex justify-content-between mb-3">                
                                            <form id="searchForm" method="get" class="d-flex">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" name="keyword" value="" placeholder="검색어를 입력하세요">                        
                                                    <button class="btn btn-secondary" type="submit" >검색</button>
                                                </div>
                                            </form>
                                            <a class="btn btn-primary" href="/branches/create">지점 등록</a>
                                        </div>
                                        <%--// 검색 폼, 등록 버튼 --%>

                                        <%-- 지점 목록 --%>
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>지점명</th>
                                                        <th>주소</th>
                                                        <th>전화번호</th>
                                                        <th>지점 관리자</th>
                                                        <th>관리</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${branches}" var="branch">
                                                        <tr>
                                                            <td class="align-middle">${branch.branchName}</td>
                                                            <td class="align-middle">${branch.branchAddress}</td>
                                                            <td class="align-middle">${branch.branchPhone}</td>
                                                            <td class="align-middle">
                                                                <c:if test="${not empty branch.branchManager and branch.branchManager != null}">
                                                                    <c:forEach items="${branch.branchManager}" var="manager">
                                                                        <p>${manager.name}(${manager.phone})</p>
                                                                    </c:forEach>
                                                                </c:if>
                                                                <c:if test="${empty branch.branchManager or branch.branchManager == null}">
                                                                    <p>관리자 없음</p>
                                                                </c:if>
                                                            </td>
                                                            <td class="align-middle">
                                                                <a href="/branches/${branch.branchId}/update" class="btn btn-primary">수정</a>
                                                                <button type="button" class="btn btn-danger">삭제</button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <%--// 지점 목록 --%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../base/footer.jsp" %>
            </div>
        </div>
        <%@ include file="../base/script.jsp" %>
        <script>
            $(document).ready(function() {
                $("#createForm").validate({
                    rules: {
                        branchName: {
                            required: true,
                            minlength: 2,
                            maxlength: 50
                        },
                        branchAddress: {
                            required: true,
                            minlength: 2,
                            maxlength: 100
                        },
                        branchPhone: {
                            required: true,
                            minlength: 2,
                            maxlength: 20
                        }
                    },
                    messages: {
                        branchName: {
                            required: "지점명을 입력해주세요.",
                            minlength: "지점명은 최소 2자 이상이어야 합니다.",
                            maxlength: "지점명은 최대 50자 이하여야 합니다."
                        },
                        branchAddress: {
                            required: "주소를 입력해주세요.",
                            minlength: "주소는 최소 2자 이상이어야 합니다.",
                            maxlength: "주소는 최대 100자 이하여야 합니다."
                        },
                        branchPhone: {
                            required: "전화번호를 입력해주세요.",
                            minlength: "전화번호는 최소 2자 이상이어야 합니다.",
                            maxlength: "전화번호는 최대 20자 이하여야 합니다."
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
