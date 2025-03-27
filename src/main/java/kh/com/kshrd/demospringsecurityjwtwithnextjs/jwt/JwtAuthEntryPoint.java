package kh.com.kshrd.demospringsecurityjwtwithnextjs.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .message("Authentication required - Please Login to access this resource")
                .status(HttpStatus.UNAUTHORIZED)
                .code(HttpStatus.UNAUTHORIZED.value())
                .payload("Access to this resource requires authentication")
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}