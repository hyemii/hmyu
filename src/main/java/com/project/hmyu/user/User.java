package com.project.hmyu.user;

import com.project.hmyu.type.Type;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@Table(name = "USER")
public class User {
    @Id
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    @Column
    private String userNm;

    @Column(columnDefinition = "enum('G', 'A')")
    @Enumerated(EnumType.STRING)
    private Type userType;
}