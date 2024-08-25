package com.example.login.filters;


import com.example.login.enumeration.LogType;
import com.example.login.model.entity.Log;
import com.example.login.repository.LogRepository;
import com.example.login.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Enumeration;

import static java.util.stream.Collectors.joining;

@Slf4j
@Component
@RequiredArgsConstructor
@WebFilter(urlPatterns = "/api/*")
public class LogFilter extends OncePerRequestFilter {

    private final LogRepository logRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        final long startTime = System.currentTimeMillis();
        final Log appLog = new Log();

        appLog.setLogType(LogType.APP_LOG)
                .setRequestRemoteIp(request.getRemoteAddr())
                .setRequestPath(request.getRequestURI())
                .setRequestMethod(request.getMethod())
                .setRequestQueryString(request.getQueryString())
                .setRequestHeader(getRequestHeader(requestWrapper));

        try {

            appLog.setHasError(true);

            filterChain.doFilter(requestWrapper, responseWrapper);

            HttpStatus status = HttpStatus.resolve(response.getStatus());

            if (status != null && status.is2xxSuccessful()) {
                appLog.setHasError(false);
            }

            appLog.setRequestBody(getBodyContent(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding()))
                    .setResponseBody(getBodyContent(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding()))
                    .setResponseHeader(getResponseHeaders(responseWrapper));

            responseWrapper.copyBodyToResponse();

        } catch (Throwable ex) {

            appLog.setHasError(true)
                    .setErrorDesc(ex.toString());

            throw ex;

        } finally {
            appLog.setDurationTimeInMs(System.currentTimeMillis() - startTime)
                    .setCreatedOn(LocalDateTime.now())
                    .setCreatedBy(SecurityUtil.getUserName());
            logRepository.save(appLog);
        }
    }

    private String getRequestHeader(ContentCachingRequestWrapper req) {
        Enumeration<String> requestHeaderNames = req.getHeaderNames();
        StringBuilder builder = new StringBuilder();

        while (requestHeaderNames.hasMoreElements()) {
            String headerName = requestHeaderNames.nextElement();
            String headerValue = req.getHeader(headerName);
            builder.append(headerName)
                    .append(":")
                    .append(headerValue)
                    .append(",");
        }

        return builder.toString();
    }

    private String getBodyContent(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            log.error("Error On Log Filter:{}", e.getMessage(), e);
        }
        return "Error On Read Body";
    }

    private String getResponseHeaders(ContentCachingResponseWrapper req) {
        return req.getHeaderNames()
                .stream()
                .map(headerName -> headerName + " : " + req.getHeader(headerName))
                .collect(joining(","));
    }


}