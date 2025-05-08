<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-body p-5 text-center">
                                        <div class="icons-org-join align-items-center mx-auto">
                                            <i class="icon-users" data-feather="users"></i>
                                            <i class="icon-plus fas fa-plus"></i>
                                        </div>
                                        <div class="h3 text-secondary mb-0">학원(지점) 회원가입</div>
                                    </div>
                                    <hr class="m-0" />
                                    <div class="card-body p-5">
                                        <%-- 학원(지점) 회원 가입 폼 --%>
                                        <form id="registerForm" action="/auth/branch-register" method="post">
                                            <input type="hidden" name="role" value="BRANCH_USER" />
                                            <input type="hidden" name="studentParent" value="1" />
                                            <!-- 학원(지점) 이름 -->
                                            <div class="mb-3">
                                                <select class="form-select form-select-solid" name="branchId" id="branchId" aria-label="학원(지점) 이름 선택">
                                                    <option value="">학원(지점) 이름 선택</option>
                                                    <c:forEach var="branch" items="${branches}">
                                                        <option value="${branch.branchId}">${branch.branchName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <!-- 학원(지점) 위치 -->

                                            <!-- 학교가 위치한 시/도 -->
                                            <div class="mb-3">    
                                                <select class="form-select form-select-solid" name="provinceId" id="provinceId" aria-label="학교가 위치한 시/도 선택">
                                                    <option value="">학교가 위치한 시/도 선택</option>
                                                    <c:forEach var="province" items="${provinces}">
                                                        <option value="${province.provinceId}">${province.provinceName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <!-- 학교가 위치한 구/군 -->
                                            <div class="mb-3">  
                                                <select class="form-select form-select-solid" name="districtId" id="districtId" aria-label="학교가 위치한 구/군 선택">
                                                    <option value="">학교가 위치한 구/군 선택</option>
                                                </select>
                                            </div>
                                            <!-- 고등학교 -->
                                            <div class="mb-3">
                                                <select class="form-select form-select-solid" name="highSchoolId" id="highSchoolId" aria-label="고등학교 선택">
                                                    <option value="">고등학교 선택</option>
                                                </select>
                                            </div>
                                            <!-- 학년 -->
                                            <div class="mb-3">
                                                <select class="form-select form-select-solid" name="grade" id="grade" aria-label="학년 선택">
                                                    <option value="">학년 선택</option>
                                                    <option value="1">1학년</option>
                                                    <option value="2">2학년</option>
                                                    <option value="3">3학년</option>
                                                    <option value="4">N수생</option>
                                                </select>
                                            </div>
                                            <!-- 이름 -->
                                            <div class="mb-3">
                                                <input class="form-control form-control-solid" type="text" name="userName" id="userName" placeholder="이름 (최대 4글자)" aria-label="이름" maxlength="4"/>
                                            </div>
                                            <!-- 연락처 -->
                                            <div class="mb-3">
                                                <input class="form-control form-control-solid" type="userPhone" name="userPhone" id="userPhone" placeholder="연락처 (예: 010-1234-5678)" aria-label="연락처" maxlength="13" />
                                            </div>
                                            <!-- 아이디 -->
                                            <div class="mb-3"> 
                                                <input class="form-control form-control-solid" type="text" name="userId" id="userId" placeholder="아이디(영소문자, 숫자, 6자리 이상, 20자리 이하)" aria-label="아이디" maxlength="20" />
                                            </div>
                                            <!-- 비밀번호 -->
                                            <div class="mb-3">
                                                <input class="form-control form-control-solid" type="password" name="password" id="password" placeholder="비밀번호(8자리 이상, 20자리 이하)" aria-label="비밀번호" maxlength="20" />
                                            </div>
                                            <!-- 비밀번호 확인 -->
                                            <div class="mb-3">
                                                <input class="form-control form-control-solid" type="password" name="passwordConfirm" id="passwordConfirm" placeholder="비밀번호 확인" aria-label="비밀번호 확인" maxlength="20" />
                                            </div>
                                            <div class="d-grid d-flex justify-content-between">
                                                <button type="submit" class="btn btn-primary">회원가입</button>
                                                <a href="/" class="btn btn-outline-dark">취소</a>
                                            </div>
                                        </form>
                                        <%--// 일반 회원 가입 폼 --%>
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
                // 전화번호 입력 제한 및 형식화
                $('#userPhone').on('keypress', function(e) {
                    // 숫자 키(0-9)만 허용, 그 외 입력은 차단
                    if (!/[0-9]/.test(String.fromCharCode(e.which)) && e.which != 8 && e.which != 0) {
                        e.preventDefault();
                    }
                }).on('input', function() {
                    // 숫자만 남기기
                    let phone = $(this).val().replace(/[^0-9]/g, '');

                    // 입력 값이 3자리 이상일 때 010으로 시작하는지 확인
                    if (phone.length >= 3 && !phone.startsWith('010')) {
                        phone = '010' + phone.substring(Math.min(3, phone.length));
                    }

                    // 형식화
                    if (phone.length <= 3) {
                        $(this).val(phone);
                    } else if (phone.length <= 7) {
                        $(this).val(phone.replace(/(\d{3})(\d{1,4})/, '$1-$2'));
                    } else {
                        $(this).val(phone.replace(/(\d{3})(\d{4})(\d{0,4})/, '$1-$2-$3'));
                    }

                    // 최대 11자리로 제한
                    if (phone.length > 11) {
                        phone = phone.substring(0, 11);
                        $(this).val(phone.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3'));
                    }
                });
                
                // 시/도 선택 시 구/군 선택 옵션 변경
                $("#provinceId").change(function() {
                    let provinceId = $(this).val();
                    let districtSelect = $("#districtId");
                    let highSchoolSelect = $("#highSchoolId");

                    // 구/군 선택 옵션 초기화
                    districtSelect.empty();
                    districtSelect.append("<option value=''>학교가 위치한 구/군 선택</option>");

                    // 고등학교 선택 옵션 초기화
                    highSchoolSelect.empty();
                    highSchoolSelect.append("<option value=''>고등학교 선택</option>");

                    if (provinceId) {
                        $.ajax({
                            url: "/auth/get-districts",
                            type: "post",
                            data: { provinceId: provinceId },
                            success: function(response) {
                                $.each(response.districts, function(index, district) {
                                    districtSelect.append("<option value='" + district.districtId + "'>" + district.districtName + "</option>");
                                });
                            },
                            error: function(xhr, status, error) {
                                console.error("구/군 목록 조회 오류:", error);
                            }
                        });
                    }
                });

                // 구/군 선택 시 고등학교 선택 옵션 변경
                $("#districtId").change(function() {
                    let districtId = $(this).val();
                    let provinceId = $("#provinceId").val();
                    let highSchoolSelect = $("#highSchoolId");

                    // 고등학교 선택 옵션 초기화
                    highSchoolSelect.empty();
                    highSchoolSelect.append("<option value=''>고등학교 선택</option>");
                    if (districtId) {
                        $.ajax({
                            url: "/auth/get-highschools",
                            type: "post",
                            data: { 
                                provinceId: provinceId,
                                districtId: districtId 
                            },
                            success: function(response) {
                                $.each(response.highSchools, function(index, highSchool) {
                                    highSchoolSelect.append("<option value='" + highSchool.highSchoolId + "'>" + highSchool.highSchoolName + "</option>");
                                });
                            },
                            error: function(xhr, status, error) {
                                console.error("고등학교 목록 조회 오류:", error);
                            }
                        });
                    }
                });

                // 일반 회원 가입 폼 유효성 검사
                $("#registerForm").validate({
                    rules: {
                        branchId: {
                            required: true
                        },
                        provinceId: {
                            required: true
                        },
                        districtId: {
                            required: true
                        },
                        highSchoolId: {
                            required: true
                        },
                        grade: {
                            required: true
                        },
                        userName: {
                            required: true
                        },
                        userPhone: {
                            required: true,
                            pattern: "010-\\d{4}-\\d{4}",
                            remote: {
                                url: '/auth/check-user-phone',
                                type: 'post',
                                data: {
                                    userPhone: function() {
                                        return $('#userPhone').val();
                                    },
                                },
                                dataFilter: function(response) {
                                    const data = JSON.parse(response);
                                    return !data.exists;
                                }
                            }
                        },
                        userId: {
                            required: true,
                            minlength: 6,
                            maxlength: 20,
                            remote: {
                                url: '/auth/check-user-id',
                                type: 'post',
                                data: {
                                    userId: function() {
                                        return $('#userId').val();
                                    },
                                },
                                dataFilter: function(response) {
                                    const data = JSON.parse(response);
                                    return !data.exists;
                                }
                            }
                        },
                        password: {
                            required: true
                        },
                        passwordConfirm: {
                            required: true,
                            equalTo: "#password"
                        }
                    },
                    messages: {
                        branchId: {
                            required: "학원(지점)을 선택해주세요."
                        },
                        provinceId: {
                            required: "학교가 위치한 시/도를 선택해주세요."
                        },
                        districtId: {
                            required: "학교가 위치한 구/군을 선택해주세요."
                        },
                        highSchoolId: {
                            required: "학교를 선택해주세요."
                        },
                        grade: {
                            required: "학년을 선택해주세요."
                        },
                        userName: {
                            required: "이름을 입력해주세요."
                        },
                        userPhone: {
                            required: "연락처를 입력해주세요.",
                            pattern: "010-XXXX-XXXX 형식으로 입력해주세요.",
                            remote: "이미 사용중인 연락처입니다."
                        },
                        userId: {
                            required: "아이디를 입력해주세요.",
                            minlength: "아이디는 6자리 이상, 20자리 이하로 입력해주세요.",
                            maxlength: "아이디는 6자리 이상, 20자리 이하로 입력해주세요.",
                            remote: "이미 사용중인 아이디입니다."
                        },
                        password: {
                            required: "비밀번호를 입력해주세요.",
                            minlength: "비밀번호는 8자리 이상, 20자리 이하로 입력해주세요.",
                            maxlength: "비밀번호는 8자리 이상, 20자리 이하로 입력해주세요."
                        },
                        passwordConfirm: {
                            required: "비밀번호 확인을 입력해주세요.",
                            equalTo: "비밀번호가 일치하지 않습니다."
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