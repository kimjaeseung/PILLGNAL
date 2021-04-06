package com.pillgnal.backend.config.oauth2;

import com.pillgnal.backend.config.oauth2.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.support.MultipartFilter;

/**
 * 보안 관련 처리 class
 *
 * @author Eomjaewoong
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailService customUserDetailService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    public JwtAuthenticationFilter tokenAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**",
                "/swagger/**");
    }

    /**
     * 웹 수준 보안처리
     *
     * 요청 처리 허용 전 보안 조건 구성
     * 커스텀 로그인 페이지 구성
     * 로그아웃 구성
     * CSRF 공격으로부터 보호
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .headers().frameOptions().disable()
                .and()
                    .cors().configurationSource(corsConfigurationSource())
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .csrf()
                    .disable()
                    .formLogin()
                    .disable()
                    .httpBasic()
                    .disable()
                    .exceptionHandling()
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                    .authorizeRequests()
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                    .antMatchers("/",
                            "/error",
                            "/favicon.ico",
                            "/**/*.png",
                            "/**/*.gif",
                            "/**/*.svg",
                            "/**/*.jpg",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js",
                            "/h2-console/**",
                            "/v2/api-docs",
                            "/swagger*/**")
                        .permitAll()
                    .antMatchers("/signup", "/login")
                        .permitAll()
                    .anyRequest()
                        .permitAll()
                        //.authenticated()
                .and()
                    .oauth2Login()
                        .authorizationEndpoint()
                            .baseUri("/login/oauth2/code/kakao")
                            .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                        .and()
                        .redirectionEndpoint()
                            .baseUri("/login/oauth2/code/kakao")
                        .and()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService)
                        .and()
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                        .failureHandler(oAuth2AuthenticationFailureHandler);

        // jwt token filter
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // CORS 허용 적용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedOriginPattern("*");
//        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
