package kh.com.kshrd.demospringsecurityjwtwithnextjs.controller;

import kh.com.kshrd.demospringsecurityjwtwithnextjs.jwt.JwtService;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.request.AppUserRequest;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.request.AuthRequest;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.response.ApiResponse;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.model.response.AuthResponse;
import kh.com.kshrd.demospringsecurityjwtwithnextjs.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class AuthController {
    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("Account is disabled", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid email or password", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest request) {
        try {
            authenticate(request.getEmail(), request.getPassword());
            final UserDetails userDetails = appUserService.loadUserByUsername(request.getEmail());
            final String token = jwtService.generateToken(userDetails);

            AuthResponse authResponse = new AuthResponse(token);

            return ResponseEntity.ok(
                    ApiResponse.<AuthResponse>builder()
                            .message("Login successful")
                            .status(HttpStatus.OK)
                            .code(HttpStatus.OK.value())
                            .payload(authResponse)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(
                            ApiResponse.<AuthResponse>builder()
                                    .message(e.getMessage())
                                    .status(HttpStatus.UNAUTHORIZED)
                                    .code(HttpStatus.UNAUTHORIZED.value())
                                    .payload(null)
                                    .build()
                    );
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody AppUserRequest request) {
        try {
            Object registeredUser = appUserService.register(request);

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Registration successful")
                            .status(HttpStatus.OK)
                            .code(HttpStatus.OK.value())
                            .payload(registeredUser)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .status(HttpStatus.BAD_REQUEST)
                                    .code(HttpStatus.BAD_REQUEST.value())
                                    .payload(null)
                                    .build()
                    );
        }
    }
}