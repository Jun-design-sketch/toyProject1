package com.example.demo.Config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // 全てを認証する
        // http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        // 全てを許可する
        // http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        // 全てを拒否する
        // http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        http
                // TODO: Deprecated POSTリクエストにCSRFトークンを含めるか別の書き方にすること
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/setQuote").authenticated()
                .requestMatchers("/getQuote", "/error").permitAll()
                )
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .accessDeniedHandler(
                                        ((request, response, accessDeniedException) ->
                                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "禁止されてる。。")
                                        )
                                )
                );

        // http.formLogin(withDefaults());
        http.formLogin(
                httpSecurityFormLoginConfigurer -> {
                    // when use single line code, {} is optional
                    httpSecurityFormLoginConfigurer.disable();
                });
        http.httpBasic(withDefaults());
        return http.build();
    }
    // intelliJ: Cmd+Oでクラス検索し、SpringBootWebSecurityConfigurationから基本設定を探す
    // Shift+F12 = サイドバー隠し
    // Cmd+F9 = Build Project
    // Cmd+b = browse(Go to declaration or Usage)
    // F7 = Step into
    // Option+F8 = Expression
}
