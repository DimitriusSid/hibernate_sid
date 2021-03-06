package com.sid.hibernate;

import com.sid.hibernate.entity.Birthday;
import com.sid.hibernate.entity.PersonalInfo;
import com.sid.hibernate.entity.User;
import com.sid.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {

        User user = User.builder()
                .username("petr2@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .lastname("Petrov")
                        .firstname("Petr")
                        .birthDate(new Birthday(LocalDate.of(2000, 1, 2)))
                        .build())
                .build();

        log.info("User entity is in transient state, object: {}", user);

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session1 = sessionFactory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();
                log.trace("Transaction is created, {}", transaction);

                session1.saveOrUpdate(user);
                log.trace("User is in persistent state: {}, session {}", user, session1);

                session1.getTransaction().commit();
            }

            try (Session session = sessionFactory.openSession()) {
                PersonalInfo key = PersonalInfo.builder()
                        .lastname("Petrov")
                        .firstname("Petr")
                        .birthDate(new Birthday(LocalDate.of(2000, 1, 2)))
                        .build();
                User user2 = session.get(User.class, key);
                System.out.println();
            }


            log.warn("User is in detached state: {}, session is closed {}", user, session1);
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw e;
        }

    }
}
