package com.sid.hibernate.entity;

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
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.TABLE)
//    @SequenceGenerator(name = "user_gen", sequenceName = "users_id_seq", allocationSize = 1)
    @TableGenerator(name = "user_gen", table = "all_sequence", allocationSize = 1, pkColumnName = "table_name", valueColumnName = "pk_value")
    private Long id;

    @Column(unique = true)
    private String username;

    @Embedded
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonBinaryType") //
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;



}
