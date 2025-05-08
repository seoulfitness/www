package kr.seoulfitness.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @SuppressWarnings("null")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {       
        // 인증이 필요한 URL 패턴들
        if (request.getRequestURI().startsWith("/user/") || request.getRequestURI().startsWith("/admin/")) {                        
            // 로그인 체크
            if (request.getSession().getAttribute("userId") == null) {
                response.sendRedirect("/auth/logout");
                return false;
            }

            // /admin 경로에 대한 접근 제한
            if (request.getRequestURI().startsWith("/admin/") && !"ADMIN".equals(request.getSession().getAttribute("role"))) {
                response.sendRedirect("/auth/logout");
                return false;
            }
        }

        return true;
    }
}
