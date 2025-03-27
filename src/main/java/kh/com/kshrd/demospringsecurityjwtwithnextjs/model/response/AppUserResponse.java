package kh.com.kshrd.demospringsecurityjwtwithnextjs.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponse {
    private Long userId;
    private String fullName;
    private String email;
}
