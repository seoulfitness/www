<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%-- jQuery --%>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<%-- jQuery Validation --%>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
<%-- Bootstrap --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<%-- Custom scripts --%>
<script src="/static/js/scripts.js"></script>
<%-- Chart.js --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" crossorigin="anonymous"></script>
<%-- Chart Area Demo --%>
<script src="/static/demo/chart-area-demo.js"></script>
<%-- Chart Bar Demo --%>
<script src="/static/demo/chart-bar-demo.js"></script>
<%-- Simple Datatables --%>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="/static/js/datatables/datatables-simple-demo.js"></script>
<%-- Litepicker --%>
<script src="https://cdn.jsdelivr.net/npm/litepicker/dist/bundle.js" crossorigin="anonymous"></script>
<script src="/static/js/litepicker.js"></script>

<script>
    $(document).ready(function() {
        // 아이디 입력 검증
        $('#userId').on('keypress', function(e) {
            // 영문자, 숫자, 특수문자(-)만 허용
            if (!/[a-zA-Z0-9\-]/.test(String.fromCharCode(e.which)) && e.which != 8 && e.which != 0) {
                e.preventDefault();
            }
        }).on('input', function() {
            // 영문자, 숫자, 특수문자(-)만 남기기
            let userId = $(this).val().replace(/[^a-zA-Z0-9]/g, '');

            // 입력 값이 20자리 이상일 때 20자리로 제한
            if (userId.length > 20) {
                $(this).val(userId.substring(0, 20));
            }
        });

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
    });
</script>
