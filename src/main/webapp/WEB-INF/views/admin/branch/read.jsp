<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                                        지점 정보                                        
                                    </div>
                                    <div class="card-body">                                        
                                        <div class="table-responsive">
                                            <%-- 지점 정보 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle col-2">지점명</th>
                                                        <td class="align-middle col-10">${branch.branchName}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">주소</th>
                                                        <td class="align-middle col-10">${branch.branchAddress}</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">전화번호</th>
                                                        <td class="align-middle col-10">${branch.branchPhone}</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 지점 정보 --%>

                                            <%-- 등록일시, 수정일시 --%>
                                            <table class="table table-bordered table-hover mb-3">
                                                <tbody>
                                                    <tr>
                                                        <th class="align-middle col-2">등록일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${branch.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">등록한 사람</th>
                                                        <td class="align-middle col-10">${branch.createdUserName} (${branch.createdBy}, ${branch.createdUserPhone})</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정일시</th>
                                                        <td class="align-middle col-10"><fmt:formatDate value="${branch.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="align-middle col-2">수정한 사람</th>
                                                        <td class="align-middle col-10">${branch.updatedUserName} (${branch.updatedBy}, ${branch.updatedUserPhone})</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <%--// 등록일시, 수정일시 --%>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <a href="/admin/branches" class="btn btn-primary">지점 목록</a>
                                        <button type="button" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#addManagerModal">관리자 추가</button>
                                        <a href="/admin/branches/${branch.branchId}/update" class="btn btn-outline-warning">지점 수정</a>
                                        <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">지점 삭제</button>
                                    </div>
                                </div>

                                <%-- 지점 관리자 --%>
                                <c:if test="${not empty branchManagersInBranch}">
                                    <c:forEach var="branchManagerInBranch" items="${branchManagersInBranch}">
                                        <div class="col-lg-12">
                                            <div class="card mb-4">
                                                <div class="card-header">
                                                    지점 관리자                                                    
                                                </div>
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <%-- 지점 관리자 목록 --%>                                                    
                                                        <table class="table table-bordered table-hover">
                                                            <tbody>
                                                                <tr>
                                                                    <th class="align-middle col-2">이름</th>
                                                                    <td class="align-middle col-10">${branchManagerInBranch.userName}</td>
                                                                </tr>
                                                                <tr>
                                                                    <th class="align-middle col-2">아이디</th>
                                                                    <td class="align-middle col-10">${branchManagerInBranch.userId}</td>
                                                                </tr>  
                                                                <tr>
                                                                    <th class="align-middle col-2">전화번호</th>
                                                                    <td class="align-middle col-10">${branchManagerInBranch.userPhone}</td>
                                                                </tr> 
                                                                <tr>
                                                                    <th class="align-middle col-2">이메일</th>
                                                                    <td class="align-middle col-10">${not empty branchManagerInBranch.userEmail ? branchManagerInBranch.userEmail : ''}</td>
                                                                </tr>                                                  
                                                            </tbody>
                                                        </table>
                                                        <%--// 지점 관리자 목록 --%>
                                                    </div>
                                                </div>
                                                <div class="card-footer">
                                                    <a href="/admin/branches" class="btn btn-primary">지점 목록</a>
                                                    <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteManagerModal" data-user-id="${branchManagerInBranch.userId}">관리자 삭제</button>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>                                    
                                </c:if>
                                <%--// 지점 관리자 --%>
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../../base/footer.jsp" %>
            </div>
        </div>

        <%-- 관리자 추가 모달 --%>
        <div class="modal fade" id="addManagerModal" tabindex="-1" aria-labelledby="addManagerModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="addManagerForm" action="/admin/branches/${branch.branchId}/addManager" method="POST">
                        <%-- modal-header --%>
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-primary" id="addManagerModalLabel">
                                <strong>관리자 추가</strong>
                            </h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <%--// modal-header --%>

                        <%-- modal-body --%>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="userId" class="form-label">지점 관리자</label>
                                <select class="form-select" id="userId" name="userId">
                                    <c:forEach var="branchManager" items="${branchManagersNotInBranch}">
                                        <option value="${branchManager.userId}">${branchManager.userName} (${branchManager.userId} / ${branchManager.userPhone})</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%--// modal-body --%>

                        <%-- modal-footer --%>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">추가</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                        </div>
                        <%--// modal-footer --%>
                    </form>
                </div>
            </div>
        </div>

        <%-- 관리자 삭제 모달 --%>
        <div class="modal fade" id="deleteManagerModal" tabindex="-1" aria-labelledby="deleteManagerModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="deleteManagerForm" action="/admin/branches/${branch.branchId}/deleteManager" method="POST">
                        <input type="hidden" name="userId" value="">
                        <%-- modal-header --%>
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-danger" id="deleteManagerModalLabel">
                                <strong>관리자 삭제</strong>
                            </h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <%--// modal-header --%>

                        <%-- modal-body --%>
                        <div class="modal-body">
                            <p class="text-danger">삭제된 데이터는 복구할 수 없습니다.</p>
                            <p>정말 삭제하시겠습니까?</p>
                        </div>
                        <%--// modal-body --%>

                        <%-- modal-footer --%>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-outline-danger">삭제</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                        </div>
                        <%--// modal-footer --%>
                    </form>
                </div>
            </div>
        </div>
        <%--// 관리자 삭제 모달 --%>

        <%-- 삭제 모달 --%>
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="deleteForm" action="/admin/branches/${branch.branchId}/delete" method="POST">
                        <%-- modal-header --%>
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-danger" id="deleteModalModalLabel">
                                <strong>지점 삭제</strong>
                            </h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <%--// modal-header --%>

                        <%-- modal-body --%>
                        <div class="modal-body">
                            <div class="mb-3">
                                <p class="text-danger">삭제된 데이터는 복구할 수 없습니다.</p>
                                <p>정말 삭제하시겠습니까?</p>
                            </div>
                        </div>
                        <%--// modal-body --%>

                        <%-- modal-footer --%>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-outline-danger">삭제</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                        </div>
                        <%--// modal-footer --%>
                    </form>
                </div>
            </div>
        </div>
        <%-- 삭제 모달 --%>

        <%@ include file="../../base/script.jsp" %>

        <script>
            $(document).ready(function() {
                $('#deleteManagerModal').on('show.bs.modal', function (e) {
                    var userId = $(e.relatedTarget).data('user-id');
                    $(this).find('input[name="userId"]').val(userId);
                });
            });
        </script>
    </body>
</html>
