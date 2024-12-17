package woowacourse.hanglog.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import woowacourse.hanglog.core.exception.ErrorCode;
import woowacourse.hanglog.web.ApiResponse;

import java.io.IOException;

abstract class AuthenticationHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, auth) ->
            setResponse(response, ApiResponse.fail(ErrorCode.UNAUTHORIZED.toException()));
    }

    static AccessDeniedHandler accessDeniedHandler() {
        return (request, response, auth) ->
            setResponse(response, ApiResponse.fail(ErrorCode.FORBIDDEN.toException()));
    }

    static void setResponse(HttpServletResponse response, ApiResponse<?> apiResponse) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(apiResponse.status().value());
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }

}
