package com.example.login.security.filter;

import com.example.login.model.enums.ExceptionSpec;
import com.example.login.exception.LogicalException;
import com.example.login.model.response.ErrorResponse;
import com.example.login.repository.UserDetailRepository;
import com.example.login.security.CustomUserDetails;
import com.example.login.security.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
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

    private final ObjectMapper objectMapper;

    @Lazy
    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTService jwtService,
                                  UserDetailRepository userBlockRepository, ObjectMapper objectMapper) {
        super(authManager);
        this.jwtService = jwtService;
        this.userDetailRepository = userBlockRepository;
        this.objectMapper = objectMapper;
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
            try {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (LogicalException e) {
                sendErrorResponse(res, e);
                return; // Stop the filter chain
            } catch (Exception e) {
                sendErrorResponse(res, e);
                return;
            }
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

    private void sendErrorResponse(HttpServletResponse res, Exception e) throws IOException {
        ErrorResponse errorResponse;
        HttpStatus status;

        if (e instanceof LogicalException) {
            LogicalException logicalException = (LogicalException) e;
            errorResponse = new ErrorResponse(logicalException.getSpecs().getMessage());
            status = logicalException.getSpecs().getHttpStatus();
        } else {
            errorResponse = new ErrorResponse("Authentication failed: " + e.getMessage());
            status = HttpStatus.UNAUTHORIZED;
        }

        res.setStatus(status.value());
        res.setContentType("application/json");
        res.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
