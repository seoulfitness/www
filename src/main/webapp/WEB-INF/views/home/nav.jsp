<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!-- Navbar-->
<nav class="navbar navbar-marketing navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand text-light" href="/">서울휘트니스</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i data-feather="menu"></i></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto me-lg-5">
                <li class="nav-item"><a class="nav-link" href="/" ${activePage == 'home' ? 'active' : ''}>입시정보</a></li>
                <li class="nav-item"><a class="nav-link" href="/auth/login">로그인</a></li>
                <li class="nav-item"><a class="nav-link" href="/auth/normal-register">일반회원가입</a></li>
                <li class="nav-item"><a class="nav-link" href="/auth/branch-register">지점회원가입</a></li>
            </ul>
        </div>
    </div>
</nav>