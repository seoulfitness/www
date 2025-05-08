<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="bg-light pt-10 pb-4" id="explore">
    <div class="container">
        <div class="row">
            <div class="col-3 text-center">
                <a href="/" style="text-decoration: none;">
                    <div><i data-feather="file-text" class="text-danger" style="width:60px;height:60px;"></i></div>
                    <div><span class="text-muted">입시 정보</span></div>
                </a>
            </div>
            <div class="col-3 text-center">
                <c:if test="${empty sessionScope.user}">
                    <a href="/auth/login" style="text-decoration: none;">
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <a href="/user/analysis" style="text-decoration: none;">
                </c:if>
                    <div><i data-feather="file-text" class="text-success" style="width:60px;height:60px;"></i></div>
                    <div><span class="text-muted">입시 분석</span></div>
                </a>
            </div>
            <div class="col-3 text-center">
                <c:if test="${empty sessionScope.user}">
                    <a href="/auth/login" style="text-decoration: none;">
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <a href="/user/records" style="text-decoration: none;">
                </c:if>
                    <div><i data-feather="file-text" class="text-primary" style="width:60px;height:60px;"></i></div>
                    <div><span class="text-muted">기록 관리</span></div>
                </a>
            </div>
            <div class="col-3 text-center">
                <c:if test="${empty sessionScope.user}">
                    <a href="/auth/login" style="text-decoration: none;">
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <a href="/user/consults" style="text-decoration: none;">
                </c:if>
                    <div><i data-feather="file-text" class="text-orange" style="width:60px;height:60px;"></i></div>
                    <div><span class="text-muted">상담 관리</span></div>
                </a>
            </div>
        </div>
    </div>
</section>