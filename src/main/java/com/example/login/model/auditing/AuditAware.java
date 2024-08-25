package com.example.login.model.auditing;

import com.example.login.security.SecurityUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return SecurityUtil.getCurrentAuditorUsername();
    }
}
