package app.auth.session;

import app.auth.CurrentUser;
import app.service.CurrentUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


// аутентификация на основе сессий
@Component
public class SessionFilter extends OncePerRequestFilter {

    private final SessionHandler handler;
    private final CurrentUserService userService;

    @Autowired // инъекции SessionHandler, CurrentUserService
    public SessionFilter(final SessionHandler handler,
                         final CurrentUserService userService) {
        this.handler = handler;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) // основная логика
            throws ServletException, IOException {
        final String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (sessionId == null || sessionId.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        } // смотрим есть ли sessionid

        final String username = handler.getUsernameForSession(sessionId);
        if (username == null) {
            filterChain.doFilter(request, response);
            return;
        } // смотрим username по sessionid

        final CurrentUser user = userService.loadUserByUsername(username); // подгружаем информацию о пользователе
        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );  // создаем токен
        auth.setDetails(new WebAuthenticationDetailsSource() .buildDetails (request));
        SecurityContextHolder.getContext().setAuthentication(auth); // добавляем в контекст безопасности
        filterChain.doFilter(request, response); // продолжаем выполнение запроса
    }
}
