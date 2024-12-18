package woowacourse.hanglog.web;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import woowacourse.hanglog.core.exception.ApplicationException;

@ControllerAdvice
class ApiResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public ApiResponse<?> beforeBodyWrite(
        Object body,
        MethodParameter returnType,
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType,
        ServerHttpRequest request,
        ServerHttpResponse response
    ) {
        if (body instanceof ApplicationException e) {
            response.setStatusCode(e.getStatus());
            return ApiResponse.fail(e);
        }

        if (body instanceof ApiResponse<?> apiResponse) {
            response.setStatusCode(apiResponse.status());
            return apiResponse;
        }

        if (body instanceof ResponseEntity<?> responseEntity) {
            HttpStatus status = (HttpStatus) responseEntity.getStatusCode();
            response.setStatusCode(status);
            return ApiResponse.of(status, responseEntity.getBody());
        }

        HttpStatus status = obtainResponseStatus(returnType);
        response.setStatusCode(status);
        return ApiResponse.of(status, body);
    }

    private HttpStatus obtainResponseStatus(MethodParameter returnType) {
        ResponseStatus responseStatus = returnType.getMethodAnnotation(ResponseStatus.class);
        return responseStatus == null ? HttpStatus.OK : responseStatus.value();
    }

}
