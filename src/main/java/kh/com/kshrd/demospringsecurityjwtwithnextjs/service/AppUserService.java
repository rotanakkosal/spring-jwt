package kh.com.kshrd.demospringsecurityjwtwithnextjs.service;

import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.entity.AppUser;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.request.AppUserRequest;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.response.AppUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {

    AppUserResponse register(AppUserRequest request);

    AppUser getCurrentUser();

    Long getCurrentUserId();

}
