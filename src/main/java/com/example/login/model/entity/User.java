package com.example.login.model.entity;

import com.example.login.enumeration.Role;
import com.example.login.model.converter.CryptoConverter;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Audited
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User extends BaseEntity {

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

    @Column(nullable = false)
    private Integer failedLoginAttempts;

    private LocalDateTime lockTimeDuration;

    @Lob
    private String profilePicture;

    @Lob
    private String bio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts;

    @ManyToMany
    private List<User> following;

    @ManyToMany
    private List<User> followers;
}