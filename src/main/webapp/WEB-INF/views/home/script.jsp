<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%-- jQuery --%>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<%-- Bootstrap --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<%-- Custom scripts --%>
<script src="/static/js/scripts.js"></script>
<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
<script>
    $(document).ready(function() {
        AOS.init({
            disable: 'mobile',
            duration: 600,
            once: true,
        });

        feather.replace();

        // 검색 취소
        $('#cancelSearchButton').on('click', function() {
            location.href = '?searchScope=${searchScope}&searchAdmission=${searchAdmission}';
        });

        // 가군
        $('#searchGaButton').on('click', function() {
            location.href = '?searchScope=ga&searchAdmission=${searchAdmission}&keyword=${keyword}';
        });

        // 나군
        $('#searchNaButton').on('click', function() {
            location.href = '?searchScope=na&searchAdmission=${searchAdmission}&keyword=${keyword}';
        });

        // 다군
        $('#searchDaButton').on('click', function() {
            location.href = '?searchScope=da&searchAdmission=${searchAdmission}&keyword=${keyword}';
        });

        // 가군/나군/다군 전체
        $('#showAllScopeButton').on('click', function() {
            location.href = '?&searchAdmission=${searchAdmission}&keyword=${keyword}';
        });

        // 정시
        $('#searchEarlyAdmissionBtton').on('click', function() {
            location.href = '?searchScope=${searchScope}&searchEarlyAdmission=true&searchAdmission=${searchAdmission}&keyword=${keyword}';
        });

        // 수시
        $('#searchRegularAdmissionButton').on('click', function() {
            location.href = '?searchScope=${searchScope}&searchRegularAdmission=true&searchAdmission=${searchAdmission}&keyword=${keyword}';
        });

        // 정시/수시 전체
        $('#showAllAdmissionButton').on('click', function() {
            location.href = '?searchScope=${searchScope}&keyword=${keyword}';
        });
    });
</script>