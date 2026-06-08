package com.example.login.repository;

import com.example.login.model.entity.audit.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Integer> {
}
