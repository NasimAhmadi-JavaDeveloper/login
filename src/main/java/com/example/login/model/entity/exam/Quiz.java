package com.example.login.model.entity.exam;

import com.example.login.model.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = "id", callSuper = false)
@Accessors(chain = true)
public class Quiz extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String title;

    //DONE = QUERY USER-QUIZ -> WHEN ALL QUESTIONS OF A QUIZ ID ANSWERED
//    private boolean done;

}