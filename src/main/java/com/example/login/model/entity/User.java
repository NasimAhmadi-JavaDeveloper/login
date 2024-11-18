package com.example.login.model.entity;

import com.example.login.enumeration.Role;
import com.example.login.model.converter.CryptoConverter;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(unique = true)
    private String email;

    @Convert(converter = CryptoConverter.class)
    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @Column
    @CreatedBy
    private String createdBy;

    @Column(nullable = false)
    private Integer failedLoginAttempts;

    private LocalDateTime lockTimeDuration;

    @Version
    @ColumnDefault("0")
    private Integer version;

    private String profilePicture;

    private String bio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    @OneToMany(mappedBy = "follower")
    private List<Follow> follows;

    @OneToMany(mappedBy = "followee")
    private List<Follow> followings;
}