package com.example.login.model.entity;

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
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(optional = false)
    @JoinColumn(name = "followee_id")
    private User followee;
}