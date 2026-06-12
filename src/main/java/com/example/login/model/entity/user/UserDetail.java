package com.example.login.model.entity.user;

import com.example.login.model.entity.BaseEntity;
import com.example.login.model.entity.exam.Quiz;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
public class UserDetail extends BaseEntity {

    @Id
    private Integer id;

    @ColumnDefault("0")
    private Integer banWordCount;

    @ColumnDefault("0")
    private Boolean blocked;

    @ColumnDefault("0")
    private Integer failedLoginAttempts;

    private LocalDateTime lockTimeDuration;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId  // Uses User's ID as primary key
    @JoinColumn(name = "id")
    private User user;
    @ManyToOne
    private Quiz currentQuiz;
}