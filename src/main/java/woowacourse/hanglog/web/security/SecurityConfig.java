package woowacourse.hanglog.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import woowacourse.hanglog.member.application.MemberService;
import woowacourse.hanglog.web.security.token.TokenProcessor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
class SecurityConfig {

    private final TokenProcessor tokenProcessor;
    private final MemberService memberService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(requests -> requests.requestMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated())
            .addFilterAt(new LoginFilter(tokenProcessor, memberService), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new ReissueTokenFilter(tokenProcessor, memberService), LoginFilter.class)
            .addFilterBefore(new AccessTokenFilter(tokenProcessor), ReissueTokenFilter.class)
            .exceptionHandling(handler -> handler.authenticationEntryPoint(AuthenticationHandler.authenticationEntryPoint())
                .accessDeniedHandler(AuthenticationHandler.accessDeniedHandler()))
            .build();
    }


}
