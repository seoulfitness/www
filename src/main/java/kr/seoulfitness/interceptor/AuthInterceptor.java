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
        String requestURI = request.getRequestURI();
        
        // 인증이 필요한 URL 패턴들
        if (requestURI.startsWith("/branchManager") || 
            requestURI.startsWith("/branches") || 
            requestURI.startsWith("/admin")) {
            
            UserDto user = (UserDto) session.getAttribute("loggedInUser");
            
            // 로그인 체크
            if (user == null) {
                response.sendRedirect("/auth/login");
                return false;
            }

            // /admin 경로에 대한 접근 제한
            if (requestURI.startsWith("/admin") && !"관리자".equals(user.getRole())) {
                response.sendRedirect("/auth/logout");
                return false;
            }

            // /branchManager 경로에 대한 접근 제한
            if (requestURI.startsWith("/branchManager")) {
                String role = user.getRole();
                if (!"관리자".equals(role) && !"지점 관리자".equals(role)) {
                    response.sendRedirect("/auth/logout");
                    return false;
                }
            }
        }

        return true;
    }
}
