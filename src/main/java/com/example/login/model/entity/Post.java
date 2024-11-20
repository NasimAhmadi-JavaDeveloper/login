package com.example.login.model.entity;

import com.example.login.model.converter.StringListConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.*;
import org.hibernate.envers.Audited;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Audited
@ToString(of = "id")
@Accessors(chain = true)
@EqualsAndHashCode(of = "id", callSuper = true)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String caption;

    @ElementCollection
    @CollectionTable(name = "post_images", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image_url", nullable = false)
    private List<String> imageUrls;

    @ColumnDefault("0")
    private Integer visitCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<User> likes = new HashSet<>();

    @Convert(converter = StringListConverter.class)
    private List<String> tag;
}