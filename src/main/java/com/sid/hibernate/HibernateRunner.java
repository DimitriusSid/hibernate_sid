package com.sid.hibernate;

import com.sid.hibernate.converter.BirthdayConverter;
import com.sid.hibernate.entity.Birthday;
import com.sid.hibernate.entity.Role;
import com.sid.hibernate.entity.User;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;


public class HibernateRunner {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.registerTypeOverride(new JsonBinaryType());


        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = User.builder()
                    .username("ivan1@gmail.com")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .info("""
                            {
                            "name" : "Ivan", 
                            "id" : 25
                            }
                            """)
                    .birthDate(new Birthday(LocalDate.of(2000, 1, 19)))
                    .role(Role.ADMIN)
                    .build();


            session.save(user);
            session.getTransaction().commit();

        }
    }
}
