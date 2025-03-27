package kh.com.kshrd.demospringsecurityjwtwithnextjs.model.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse<T> {
    private String message;      // Human-readable message
    private HttpStatus status;   // HTTP status (e.g., HttpStatus.OK)
    private int code;            // HTTP status code (e.g., 200)
    private T payload;           // The actual data
}