package kr.seoulfitness.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.seoulfitness.admin.user.UserDto;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("loggedInUser");

        if (user == null) {
            // 로그인하지 않은 경우, 로그인 페이지로 리디렉트
            response.sendRedirect("/auth/login");
            return false;
        }

        // /branches 경로에 대한 접근 제한
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/branches") && !"root".equals(user.getRole())) {
            response.sendRedirect("/");
            return false;
        }

        // 로그인한 경우 계속 진행
        return true;
    }
}
