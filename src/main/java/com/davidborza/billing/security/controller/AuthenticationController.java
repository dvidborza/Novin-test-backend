package com.davidborza.billing.security.controller;

import com.davidborza.billing.entity.User;
import com.davidborza.billing.security.exception.TokenRefreshException;
import com.davidborza.billing.security.model.SecurityUser;
import com.davidborza.billing.security.model.dto.request.LoginRequestDto;
import com.davidborza.billing.security.model.dto.request.SignupRequestDto;
import com.davidborza.billing.security.model.dto.request.TokenRefreshRequestDto;
import com.davidborza.billing.security.model.dto.response.JwtResponseDto;
import com.davidborza.billing.security.model.dto.response.SignupResponseDto;
import com.davidborza.billing.security.model.dto.response.TokenRefreshResponseDto;
import com.davidborza.billing.security.service.UserDetailsServiceImpl;
import com.davidborza.billing.security.utils.JwtUtils;
import com.davidborza.billing.service.UserService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;
    private final ConversionService conversionService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    /**
     * Login the User.
     *
     * @param loginRequest The given LoginRequestDto object.
     * @return The JwtResponseDto object.
     */
    @PostMapping("/login")
    public JwtResponseDto authenticateUser(@Valid @RequestBody final LoginRequestDto loginRequest) {

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

        final String jwt = jwtUtils.generateJwtToken(securityUser);

        final List<String> roles = securityUser.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        final String refreshToken = jwtUtils.generateRefreshTokenFromUsername(securityUser.getUsername());

        final LocalDateTime now = LocalDateTime.now();
        userService.updateLastLogInDate(securityUser.getUsername(), now);

        return new JwtResponseDto(jwt, refreshToken, securityUser.getId(),
                securityUser.getUsername(), roles, now);
    }

    /**
     * Register User.
     *
     * @param signupRequestDto The given SignupRequestDto object.
     * @return The saved User's SignupResponseDto object.
     */
    @PostMapping("/register")
    public SignupResponseDto registerUser(@Valid @RequestBody final SignupRequestDto signupRequestDto) {
        return conversionService.convert(userService.save(conversionService.convert(signupRequestDto, User.class)), SignupResponseDto.class);
    }

    /**
     * Refresh the Tokens.
     *
     * @param request The given refresh token.
     * @return New Tokens.
     */
    @PostMapping("/refresh-token")
    public TokenRefreshResponseDto refreshToken(@Valid @RequestBody final TokenRefreshRequestDto request) {
        final String requestRefreshToken = request.getRefreshToken();

        if (!jwtUtils.validateJwtToken(request.getRefreshToken())) {
            throw new TokenRefreshException(request.getRefreshToken(), "Invalid refresh token!");
        }

        final String username = jwtUtils.getUserNameFromJwtToken(requestRefreshToken);

        final SecurityUser securityUser = (SecurityUser) userDetailsService.loadUserByUsername(username);

        final String jwt = jwtUtils.generateJwtToken(securityUser);

        return new TokenRefreshResponseDto(jwt, requestRefreshToken);
    }

}
