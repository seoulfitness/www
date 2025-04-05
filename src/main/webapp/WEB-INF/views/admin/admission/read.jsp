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
                                <%-- 입시 요강 정보 --%>
                                <%@ include file="admission.jsp" %>
                                <%--// 입시 요강 정보 --%>

                                <c:if test="${admission.earlyAdmission == 'Y'}">
                                    <%-- 수시 입시 정보 --%>
                                    <%@ include file="earlyAdmission.jsp" %>
                                    <%--// 수시 입시 정보 --%>

                                    <%-- 수시 입시 수능 정보 --%>
                                    <%@ include file="earlyAdmissionCsat.jsp" %>
                                    <%--// 수시 입시 수능 정보 --%>

                                    <%-- 수시 입시 영어 정보 --%>
                                    <%@ include file="earlyAdmissionEnglish.jsp" %>
                                    <%--// 수시 입시 영어 정보 --%>

                                    <%-- 수시 입시 한국사 정보 --%>
                                    <%@ include file="earlyAdmissionHistory.jsp" %>
                                    <%--// 수시 입시 한국사 정보 --%>

                                    <%-- 수시 입시 실기 정보 --%>
                                    <%@ include file="earlyAdmissionPhysical.jsp" %>
                                    <%--// 수시 입시 실기 정보 --%>
                                </c:if>

                                <c:if test="${admission.regularAdmission == 'Y'}">
                                    <%-- 정시 입시 정보 --%>
                                    <%@ include file="regularAdmission.jsp" %>
                                    <%--// 정시 입시 정보 --%>

                                    <%-- 정시 입시 수능 정보 --%>
                                    <%@ include file="regularAdmissionCsat.jsp" %>
                                    <%--// 정시 입시 수능 정보 --%>

                                    <%-- 정시 입시 영어 정보 --%>
                                    <%@ include file="regularAdmissionEnglish.jsp" %>
                                    <%--// 정시 입시 영어 정보 --%>

                                    <%-- 정시 입시 한국사 정보 --%>
                                    <%@ include file="regularAdmissionHistory.jsp" %>
                                    <%--// 정시 입시 한국사 정보 --%>

                                    <%-- 정시 입시 실기 정보 --%>
                                    <%@ include file="regularAdmissionPhysical.jsp" %>
                                    <%--// 정시 입시 실기 정보 --%>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../../base/footer.jsp" %>
            </div>
        </div>

        <%-- 삭제 모달 --%>
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="deleteForm" action="/admin/admissions/${admission.admissionId}/delete" method="POST">
                        <%-- modal-header --%>
                        <div class="modal-header">
                            <h1 class="modal-title fs-5 text-danger" id="deleteModalModalLabel">
                                <strong>입시 요강 삭제</strong>
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

        <%-- 절대평가 점수 입력 모달 --%>
        <%@ include file="earlyAdmissionPhysicalModal.jsp" %>
        <%--// 절대평가 점수 입력 모달 --%>

        <%@ include file="../../base/script.jsp" %>
        <%@ include file="earlyAdmissionPhysicalScript.jsp" %>
    </body>
</html>
