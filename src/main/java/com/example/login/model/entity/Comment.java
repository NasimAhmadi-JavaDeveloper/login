package com.example.login.model.entity;

import com.example.login.model.enums.Emoji;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@Audited
@DynamicInsert
@DynamicUpdate
@ToString(of = "id")
@Accessors(chain = true)
@EqualsAndHashCode(of = "id", callSuper = true)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String commentText;

    @Enumerated
    private Emoji emoji;
}