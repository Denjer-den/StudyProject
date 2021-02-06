package SocialMedia.dao;

import SocialMedia.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileDAO {

    private static SessionFactory factory = null;
    private static Session session = null;

    public List index() {
        List usersProfile;
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Profile.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            usersProfile = session.createQuery("from Profile").getResultList();
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
        return usersProfile;
    }

    public Profile show(int id) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Profile.class)
                .buildSessionFactory();
        Profile profile;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            profile = session.get(Profile.class, id);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
        return profile;
    }

    public void save(Profile profile) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Profile.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(profile);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }

    public void update(int id, Profile updateProfile) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Profile.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Profile profile = session.get(Profile.class,id);
            profile.setName(updateProfile.getName());
            profile.setAge(updateProfile.getAge());
            profile.setEmail(updateProfile.getEmail());
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }

    public void delete(int id) {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Profile.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Profile profile = session.get(Profile.class, id);
            session.delete(profile);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}

