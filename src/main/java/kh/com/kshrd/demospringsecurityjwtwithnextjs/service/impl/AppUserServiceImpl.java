package kh.com.kshrd.demospringsecurityjwtwithnextjs.service.impl;


import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.entity.AppUser;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.request.AppUserRequest;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.response.AppUserResponse;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.repository.AppUserRepository;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.getUserByEmail(email);
    }

    @Override
    public AppUserResponse register(AppUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        AppUser appUser = appUserRepository.register(request);
        return modelMapper.map(appUser, AppUserResponse.class);
    }

    @Override
    public AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof AppUser) {
            return (AppUser) authentication.getPrincipal();
        }
        return null;
    }

    @Override
    public Long getCurrentUserId() {
        AppUser appUser = getCurrentUser();
        return (appUser != null) ? appUser.getUserId() : null;
    }

}
