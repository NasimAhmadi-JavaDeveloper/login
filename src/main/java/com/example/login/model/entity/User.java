package com.example.login.model.entity;

import com.example.login.enumeration.Role;
import com.example.login.model.converter.CryptoConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Setter
@Getter
@Entity
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false, unique = true)
    private String email;
    @Convert(converter = CryptoConverter.class)
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;
    @Column
    @CreatedBy
    private String createdBy;
}