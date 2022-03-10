package com.sid.hibernate.entity;

import com.sid.hibernate.converter.BirthdayConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PersonalInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 6833028243105652083L;

    private String firstname;
    private String lastname;

    @Convert(converter = BirthdayConverter.class)
    private Birthday birthDate;

}
