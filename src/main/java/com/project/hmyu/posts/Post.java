package com.project.hmyu.posts;

import com.project.hmyu.type.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Setter
@Getter
@Table(name = "POST")
public class Post {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(nullable = false)
    private String subject;

    @Column(length = 2500)
    private String contents;

    @Column(columnDefinition = "enum('O', 'P')")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String regId;
}
