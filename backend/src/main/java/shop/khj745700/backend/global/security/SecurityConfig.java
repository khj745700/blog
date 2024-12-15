package shop.khj745700.backend.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import shop.khj745700.backend.global.security.filter.JsonLoginAuthenticationFilter;
import shop.khj745700.backend.global.security.handler.CustomAccessDeniedHandler;
import shop.khj745700.backend.global.security.handler.CustomAuthEntryPoint;
import shop.khj745700.backend.global.security.handler.JsonAuthFailureHandler;
import shop.khj745700.backend.global.security.handler.JsonAuthSuccessHandler;
import shop.khj745700.backend.global.security.provider.JsonLoginAuthenticationProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new JsonAuthFailureHandler();
    }

    @Bean
    public AuthenticationEntryPoint accessAuthEntryPoint() {
        return new CustomAuthEntryPoint();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    public AuthenticationProvider jsonAuthenticationProvider() {
        return new JsonLoginAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    @Bean
    public AccessDeniedHandler jsonAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    public JsonAuthSuccessHandler jsonAuthSuccessHandler() {
        return new JsonAuthSuccessHandler(delegatingSecurityContextRepository());
    }

    public JsonLoginAuthenticationFilter jsonLoginAuthenticationFilter(
            AuthenticationManager authenticationManager) {
        JsonLoginAuthenticationFilter filter = new JsonLoginAuthenticationFilter(objectMapper,
                authenticationManager);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
        filter.setAuthenticationSuccessHandler(jsonAuthSuccessHandler());

        return filter;
    }

    @Bean
    public DelegatingSecurityContextRepository delegatingSecurityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.anonymous(AbstractHttpConfigurer::disable);
//        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        AuthenticationManager authenticationManager = authenticationManager(
                http.getSharedObject(AuthenticationConfiguration.class));

        ProviderManager p = (ProviderManager) authenticationManager;
        p.getProviders().add(jsonAuthenticationProvider());

        http.securityContext((securityContext) -> {
            securityContext.securityContextRepository(delegatingSecurityContextRepository());
            securityContext.requireExplicitSave(
                    true); // true로 주게 되면 SecurityContextHolder의 값이 변경되어도 자동 저장되지 않게 함.
        });

        http.authorizeHttpRequests(
                (auth) -> auth.requestMatchers("/", "/auth/**", "/health-check", "/comment/**" , "/resources/**", "/user/profile/**", "/hashtags/counts", "/hashtags/search", "/boards/paging", "/boards/search", "/boards/{boardId:^[0-9]*$}").permitAll()
                        .anyRequest().authenticated());
        http.addFilterAt(jsonLoginAuthenticationFilter(authenticationManager),
                UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                    SessionCreationPolicy.NEVER);
        });
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer
                        .authenticationEntryPoint(accessAuthEntryPoint()));

        return http.build();
    }

}
