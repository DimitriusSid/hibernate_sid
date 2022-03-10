package com.sid.hibernate.entity;

import com.sid.hibernate.converter.BirthdayConverter;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    private String username;

    @Embedded
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonBinaryType") //
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;



}
