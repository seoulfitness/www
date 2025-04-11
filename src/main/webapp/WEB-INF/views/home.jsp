<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <%-- head --%>
    <%@ include file="./home/head.jsp" %>
    <%--// head --%>
    <body>
        <div id="layoutDefault">
            <div id="layoutDefault_content">
                <main>
                    <%-- navigation --%>
                    <%@ include file="./home/nav.jsp" %>
                    <%--// navigation --%>

                    <%-- 바로가기 아이콘 --%>
                    <jsp:include page="./home/shortcut.jsp" />
                    <%--// 바로가기 아이콘 --%>

                    <%-- 입시 정보 --%>
                    <jsp:include page="./home/admission.jsp" />
                    <%--// 입시 정보 --%>
                </main>
            </div>
            <%-- footer --%>
            <%@ include file="./home/footer.jsp" %>
            <%--// footer --%>
        </div>
        <%-- script --%>
        <%@ include file="./home/script.jsp" %>
        <%--// script --%>
    </body>
</html>
