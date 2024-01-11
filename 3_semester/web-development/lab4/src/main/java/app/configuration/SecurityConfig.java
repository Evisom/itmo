package app.configuration;

import app.auth.session.SessionFilter;
import app.configuration.dsl.CustomDsl;
import app.service.CurrentUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

import java.util.Objects;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity // spring security
public class SecurityConfig {
    private final CurrentUserService userService;
    private final SessionFilter sessionFilter;
    private final PasswordEncoder passwordEncoder;
    private final MvcRequestMatcher.Builder mvc;
    private final CustomDsl dsl;

    @Autowired
    public SecurityConfig(final CurrentUserService userService,
                          final SessionFilter sessionFilter,
                          final PasswordEncoder passwordEncoder,
                          final MvcRequestMatcher.Builder mvc,
                          final CustomDsl dsl) {
        this.userService = userService;
        this.sessionFilter = sessionFilter;
        this.passwordEncoder = passwordEncoder;
        this.mvc = mvc;
        this.dsl = dsl;
    } // инъектируем все зависимости для работы безопасности

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable);
        http.exceptionHandling(eh -> eh.authenticationEntryPoint(
                (rq, rs, ex) -> rs.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        ex.getLocalizedMessage())
        ));
        http.authorizeHttpRequests((ar) -> {
            ar.requestMatchers(mvc.pattern("/login")).permitAll();
            ar.requestMatchers(mvc.pattern("/register")).permitAll();
            ar.anyRequest().authenticated();
        }).httpBasic(withDefaults());
        http.apply(dsl);
        return http.build();
    } // определяем цепь фильтров безопасности

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
