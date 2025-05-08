<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <%@ include file="head.jsp" %>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container-xl px-4">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <%-- Basic login form--%>
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header justify-content-center"><h3 class="fw-light my-4">서울휘트니스</h3></div>
                                    <div class="card-body">
                                        <%-- Login form--%>
                                        <form id="loginForm" action="/auth/login" method="post">
                                            <%-- Form Group (email address)--%>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="userId">아이디</label>
                                                <input class="form-control" name="userId" id="userId" type="text" placeholder="아이디를 입력해주세요." value="sung2ne" />
                                            </div>
                                            <%-- Form Group (password)--%>
                                            <div class="mb-3">
                                                <label class="small mb-1" for="password">비밀번호</label>
                                                <input class="form-control" name="password" id="password" type="password" placeholder="비밀번호를 입력해주세요." value="1234" />
                                            </div>
                                            <%-- Form Group (login box)--%>
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <div class="col d-flex align-items-center">
                                                    <a class="small" href="#">아이디 찾기</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a class="small" href="#">비밀번호 초기화</a>
                                                </div>
                                                <div class="col d-flex justify-content-end align-items-center">
                                                    <button type="submit" class="btn btn-primary" data-dashlane-label="true" data-dashlane-rid="473715b4bd396608" data-dashlane-classification="action,login" data-kwimpalastatus="dead">로그인</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center">
                                        <div class="small row">
                                            <div class="col">
                                                <a href="/auth/branch-register/">학원(지점) 회원가입</a>
                                            </div>
                                            <div class="col">
                                                <a href="/auth/normal-register/">일반(개인) 회원가입</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="footer-admin mt-auto footer-dark">
                    <div class="container-xl px-4">
                        <div class="row">
                            <div class="col-md-6 small">Copyright &copy; Your Website 2021</div>
                            <div class="col-md-6 text-md-end small">
                                <a href="#!">Privacy Policy</a>
                                &middot;
                                <a href="#!">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <%@ include file="script.jsp" %>
        <script>
            $(document).ready(function() {
                $("#loginForm").validate({
                    rules: {
                        userId: "required",
                        password: "required"
                    },
                    messages: {
                        userId: "아이디를 입력해주세요.",
                        password: "비밀번호를 입력해주세요."
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