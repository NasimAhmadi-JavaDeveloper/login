package com.example.login.security.filter;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.repository.UserDetailRepository;
import com.example.login.security.CustomUserDetails;
import com.example.login.security.JWTService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final JWTService jwtService;
    private final UserDetailRepository userDetailRepository;

    @Lazy
    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTService jwtService,
                                  UserDetailRepository userBlockRepository) {
        super(authManager);
        this.jwtService = jwtService;
        this.userDetailRepository = userBlockRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

//        if ("TRACE".equalsIgnoreCase(req.getMethod())
//                || "TRACK".equalsIgnoreCase(req.getMethod())) {
//            res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//            return;
//        }

        String header = req.getHeader(HEADER_STRING);
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            CustomUserDetails userDetails = jwtService.decode(token.replace(TOKEN_PREFIX, ""));

            Boolean isUserBlock = userDetailRepository.isUserBlocked(userDetails.getId()).orElse(false);

            if (isUserBlock) {
                throw new LogicalException(ExceptionSpec.USER_ALREADY_BLOCKED);
            }

            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
        return null;
    }
}
